package Interactors;

import Entities.GUI.Network.InitialInterpreter;
import Entities.GUI.Network.NetworkInterpreter;
import GUI.GameDisplayInteractor;
import GUI.PresenterDisplay;
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

    public void setActive(boolean active) {
        this.active = active;
    }
    public ServerListener (UseCaseInteractor useCaseInteractor, GameDisplayInteractor displayInteractor, PresenterDisplay display){
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
            socket.socket().connect((new InetSocketAddress("127.0.0.1", 60000)));
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
        init();
        while (true) {
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
    }

    public void handleReadableKeys(SelectionKey key){
        interpreter.interpretData((SocketChannel)key.channel());
    }
}
