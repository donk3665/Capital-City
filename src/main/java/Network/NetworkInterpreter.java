package Network;

import GUIInteractors.GameDisplayInteractor;
import GUIInteractors.PresenterDisplay;
import UseCases.UseCaseInteractor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public abstract class NetworkInterpreter {
    private static UseCaseInteractor useCaseInteractor;
    private static GameDisplayInteractor displayInteractor;

    public static PresenterDisplay getPresenterDisplay() {
        return presenterDisplay;
    }

    public static void setPresenterDisplay(PresenterDisplay presenterDisplay) {
        NetworkInterpreter.presenterDisplay = presenterDisplay;
    }

    private static PresenterDisplay presenterDisplay;


    public static UseCaseInteractor getUseCaseInteractor() {
        return useCaseInteractor;
    }

    public static void setUseCaseInteractor(UseCaseInteractor useCaseInteractor) {
        NetworkInterpreter.useCaseInteractor = useCaseInteractor;
    }

    public static GameDisplayInteractor getDisplayInteractor() {
        return displayInteractor;
    }

    public static void setDisplayInteractor(GameDisplayInteractor displayInteractor) {
        NetworkInterpreter.displayInteractor = displayInteractor;
    }
    public abstract void interpretData(SocketChannel channel);
    public abstract void interpretMessage(String message);
    static ByteBuffer byteBuffer = ByteBuffer.allocate(256);
    public static String read(SocketChannel channel){

        try {
            int numRead = channel.read(byteBuffer);

            if (numRead == -1) {
                channel.close();
                System.out.println("Client Disconnected!");
                return null;
            } else {
                byteBuffer.flip();
                byte[] read;
                int maxCounter = 0;
                for (int i = 0; i<byteBuffer.limit()-1; i++) {
                    if (byteBuffer.get(i) == 13 &&byteBuffer.get(i+1) == 10){
                        maxCounter = i;
                    }
                }
                read = new byte[maxCounter+2];
                byteBuffer.get(read);
                byteBuffer.compact();

                if (new String(read).equals("")){
                    return null;
                }


                return new String(read).trim();
            }
        }
        catch (IOException e){
            System.err.println("IOException when trying to read from key");
            System.exit(1);
            return null;
        }
    }
    public String[] getSplitMessages(SocketChannel channel){
        String messages = read(channel);
        System.out.println("Received message: (" + messages + ") from the server" );
        if(messages == null){
            return null;
        }

        return messages.split("\r\n");

    }
}
