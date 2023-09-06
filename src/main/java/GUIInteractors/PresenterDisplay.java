package GUIInteractors;
import GUI.Screens.Screen;
import Network.ServerListener;
import Logic.NodeNames;
import Persistence.LoadFile;
import Persistence.SaveFile;

import UseCases.UseCaseInteractor;

import javax.swing.*;
import java.io.File;

/**
 * GUI.PresenterDisplay is a class that runs the game loop and presents the options of each turn to each
 * player in the game.
 **/
public class PresenterDisplay implements GameLoop{

    OutputInteractor outputControl;
    GameDisplayInteractor gameFrame;
    InputInteractor inputControl;
    UseCaseInteractor interactor;
    ServerListener listener;
    Thread listenerThread;
    final Object syncObject;

    final Object waitingObject;
    private boolean waiting = false;

    public PresenterDisplay(){
        syncObject = new Object();
        waitingObject = new Object();
    }
    /**
     * Function that runs the game loop by getting game data from the OutputInteractor and presenting that to the
     * user as their options on each turn through Conversion in the GameDisplayInteractor,
     *  and then getting the user's input and sending that back to the InputInteractor
     * to further handle state changes based on their option choice.
     **/
    public void playGame(File file) {

        interactor = new UseCaseInteractor(new LoadFile(file), new SaveFile(file));
        inputControl = new InputInteractor(interactor);
        outputControl = new OutputInteractor(interactor);
        gameFrame = new GameDisplayInteractor(this);
        listener = new ServerListener(interactor, gameFrame, this, syncObject);
        listenerThread = new Thread(listener);
        listenerThread.start();


        Screen.initializeScreen();
        Screen.setPresenterDisplay(this);

        outputControl.updateStateOptions();
        gameFrame.setOutputs(outputControl.getCurrentState(), outputControl.getOutputMessage());

        gameLoop();
        SwingUtilities.invokeLater(() -> gameFrame.displayScreen());


    }
    public void gameLoop(){
        boolean didInput;
        didInput = gameFrame.waitForInput();
        if (didInput) {
            inputControl.getChoice(gameFrame.getInput());
            outputControl.updateState(inputControl.getUpdatedState());
            if (outputControl.getStartThread()){
                startListener();
            }
            gameFrame.refreshScreen();

            outputControl.updateStateOptions();
            gameFrame.setOutputs(outputControl.getCurrentState(), outputControl.getOutputMessage());        }

    }
    public String saveGame(){
        return interactor.saveGame();
    }
    public void exitToMenu(){
        interactor.exitToMenu();
        inputControl.setCurrentStateFromInteractor();

        outputControl.updateState(inputControl.getCurrentState());
        gameFrame.refreshScreen();

        outputControl.updateStateOptions();
        gameFrame.setOutputs(outputControl.getCurrentState(), outputControl.getOutputMessage());
    }
    public void forceNodeSwitch(NodeNames node){
        interactor.forceNodeSwitch(node);
        inputControl.setCurrentStateFromInteractor();

        outputControl.updateState(inputControl.getCurrentState());
        gameFrame.refreshScreen();

        outputControl.updateStateOptions();
        gameFrame.setOutputs(outputControl.getCurrentState(), outputControl.getOutputMessage());
    }
    public void startListener(){
        if (!listener.isActive()){
            synchronized (syncObject){
                syncObject.notify();
            }
        }
    }
    public void stopListener(){
        listener.setActive(false);
    }
    public void switchIfConnected(){
        if (inputControl.getCurrentState().getId() == NodeNames.MULTIPLAYER_STALL){
            waiting = true;
            synchronized (waitingObject){
                try {
                waitingObject.wait();
                } catch (InterruptedException e) {
                // Happens if someone interrupts your thread.
                }
            }
            waiting = false;
            forceNodeSwitch(NodeNames.MULTIPLAYER_LOBBY);
        }
    }
    public boolean isWaiting() {
        return waiting;
    }
    public Object getWaitingObject(){
        return waitingObject;
    }

}
