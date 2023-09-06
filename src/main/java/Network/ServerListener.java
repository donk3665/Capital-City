package Network;

import Network.InitialInterpreter;
import Network.NetworkInterpreter;
import GUIInteractors.GameDisplayInteractor;
import GUIInteractors.PresenterDisplay;
import UseCases.UseCaseInteractor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerListener implements Runnable{
    private SocketChannel socket;
    private Selector selector;
    private NetworkInterpreter interpreter = new InitialInterpreter();
    private boolean active;
    public boolean isActive() {
        return active;
    }
    private final Object syncObject;

    public void setActive(boolean active) {
        this.active = active;
    }
    public ServerListener (UseCaseInteractor useCaseInteractor, GameDisplayInteractor displayInteractor, PresenterDisplay display, Object syncObject){
        this.syncObject = syncObject;
        NetworkInterpreter.setUseCaseInteractor(useCaseInteractor);
        NetworkInterpreter.setDisplayInteractor(displayInteractor);
        NetworkInterpreter.setPresenterDisplay(display);

        useCaseInteractor.setListener(this);
        displayInteractor.setScreenListener(this);
    }
    public void init(){
        active = true;
        try {
            selector = Selector.open();
            socket = SocketChannel.open();

            try {
                //this line is for local testing of the online multiplayer
                socket.socket().connect((new InetSocketAddress("127.0.0.1", 60000)));

                //this line connects to the (not always running) server hosted on aws
                //socket.socket().connect((new InetSocketAddress("18.191.135.21", 60000)));
            }
            catch (IOException e){
                System.out.println("Server is not up!");
                interpreter.interpretMessage("FAIL");
                active=false;
                return;
            }
            socket.configureBlocking(false);
            socket.register(selector, SelectionKey.OP_READ);
            SocketChannel finalSocket = socket;
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    finalSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        }
        catch (IOException e){
            System.err.println("IO exception called on sockets");
            System.exit(1);
        }
    }
    public void write(String message){
        try {
            System.out.println("Wrote message: (" + message + ") to server");
            socket.write(ByteBuffer.wrap((message+"\r\n").getBytes()));
        }
        catch (IOException e){
            System.err.println("Could not write to socket");
        }
    }
    public void writeIfMultiplayer(String message){
        if (active){
            write(message);
        }
    }
    public NetworkInterpreter getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(NetworkInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        while(true) {
            setActive(false);
            synchronized (syncObject) {
                try {
                    // Calling wait() will block this thread until another thread
                    // calls notify() on the object.
                    syncObject.wait();
                } catch (InterruptedException e) {
                    // Happens if someone interrupts your thread.
                }
            }
            init();
            while (active) {
                try {
                    selector.select();
                } catch (IOException e) {
                    System.err.println("Selector failed");
                }
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isReadable()) {
                        handleReadableKeys(key);
                    }
                    iter.remove();
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void handleReadableKeys(SelectionKey key){
        interpreter.interpretData((SocketChannel)key.channel());
    }
}
