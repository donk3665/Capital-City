package Network;

import GUI.Screens.Screen;
import Logic.GameNode;
import Logic.GeneralGameNode;
import Logic.InitialNodeLogic.InitialGameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic.AuctionTreeNode;
import Logic.NodeNames;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class InitialInterpreter extends NetworkInterpreter{

    @Override
    public void interpretData(SocketChannel channel) {
        String[] splitMessages = getSplitMessages(channel);
        if (splitMessages == null){
            return;
        }

        for (String message: splitMessages) {
            interpretMessage(message);
        }
    }
    @Override
    public void interpretMessage(String message){
        String[] splitMessage = message.split("\s+");
        switch (splitMessage[0]) {
            case "LOBBY", "SENDING_LOBBIES" -> {
                getDisplayInteractor().getScreenFactory().getNode(NodeNames.SELECT_GAME_CLIENT).handleAsynchronousInput(message);
            }
            case "CLIENT_INFO" ->{
                getDisplayInteractor().getScreenFactory().getNode(NodeNames.HOST_GAME).handleAsynchronousInput("UNLOCK_CHAT " + splitMessage[3]);
                Screen.setTempIndex(Integer.parseInt(splitMessage[2]));
                Screen.setTempName(splitMessage[1]);
            }
            case "RECEIVE_TEXT" ->{
                splitMessage = message.split("#1839673858#");
                Screen.getRecentChatBox().addExternalMessage(splitMessage[1], Integer.parseInt(splitMessage[2]), splitMessage[3]);
            }
            case "CURRENT_LOBBY" ->{
                getDisplayInteractor().getScreenFactory().getNode(NodeNames.HOST_GAME).handleAsynchronousInput(message);
            }
            case "DISCONNECT" ->{
                GameNode.setMultiplayer(false);
                getPresenterDisplay().stopListener();
            }
            case "KICK" ->{
                getPresenterDisplay().stopListener();
                getPresenterDisplay().exitToMenu();
            }
            case "STATES" ->{
                InitialGameNode.loadStatesNetwork(message);
            }
            case "LOAD_DATA" ->{
                String [] doubleSplit = message.split("#1839673858#");
                getUseCaseInteractor().getSaveAccess().addTempLine(doubleSplit[1] + "\n");
            }
            case "INITIALIZE_GAME" ->{
                GameNode.setMultiplayer(true);
                if (Boolean.parseBoolean(splitMessage[1])){
                    try {
                        getUseCaseInteractor().getSaveAccess().writeTempFile();
                        InitialGameNode.loadBoardAndStates("temp.txt");
                    }
                    catch (IOException e){
                        System.err.println("Could not write or load file");
                    }
                }
                else {
                    InitialGameNode.createNewBoard();
                }
                InitialGameNode.createGame();
                getUseCaseInteractor().getListener().write("READY");
            }
            case "BEGIN" ->{
                getUseCaseInteractor().setClient(Screen.getTempIndex());
                if (GeneralGameNode.getCurrentPlayer() != GeneralGameNode.getClientPlayer()){
                    MainGameNode.setStatesNotCurrentPlayer();
                }
                getPresenterDisplay().forceNodeSwitch(NodeNames.MAIN_PARENT);
            }
            case "UNLOCK_CHATS" ->{
                getDisplayInteractor().getScreenFactory().getNode(NodeNames.MAIN_PARENT).handleAsynchronousInput("UNLOCK_CHAT");
                getDisplayInteractor().getScreenFactory().getNode(NodeNames.AUCTION_PARENT).handleAsynchronousInput("UNLOCK_CHAT");
                getDisplayInteractor().getScreenFactory().getNode(NodeNames.FINISH_GAME).handleAsynchronousInput("UNLOCK_CHAT");
            }
            case "CHANGE_PLAYER" ->{
                GeneralGameNode.setCurrentPlayer(Integer.parseInt(splitMessage[1]));
                getPresenterDisplay().forceNodeSwitch(getUseCaseInteractor().getLogicInteractor().getCurrentState().getId());
            }
            case "BEGIN_SEQUENCE" ->{
                getUseCaseInteractor().beginSequence();
            }
            case "NOT_READY" ->{
                getDisplayInteractor().getScreenFactory().getNode(NodeNames.HOST_GAME).handleAsynchronousInput("NOT_READY");
            }
            case "INFO:" ->{
                String[] newMessage = new String[splitMessage.length-1];
                for (int i = 1; i< splitMessage.length; i++){
                    newMessage[i-1] = splitMessage[i];
                }
                getUseCaseInteractor().interpretInnerMessages(newMessage);
                getDisplayInteractor().refreshTurnChangeOptions();
            }
            case "TRADE", "CANCEL_TRADE" ->{
                getDisplayInteractor().getScreenFactory().getNode(NodeNames.MAIN_PARENT).handleAsynchronousInput(message);
            }
            case "TRADE_ACCEPT" ->{
                getPresenterDisplay().forceNodeSwitch(NodeNames.ACCEPT_TRADE);
            }
            case "TRADE_DECLINE", "TRADE_CANCELLED" ->{
                getPresenterDisplay().forceNodeSwitch(NodeNames.DECLINE_TRADE);
            }
            case "SWAP" ->{
                String [] propertyMessages = message.split("#1839673858#");
                GeneralGameNode.swapProperty(propertyMessages[1], propertyMessages[2]);
            }
            case "INIT_AUCTION" ->{
                AuctionTreeNode.initialize();
                AuctionTreeNode.setAuctionProperty();
                AuctionTreeNode.setReturnPlayerIndex(Integer.parseInt(splitMessage[1]));
                getPresenterDisplay().forceNodeSwitch(NodeNames.AUCTION_PARENT);
            }
            case "AUCTION" ->{
                if (AuctionTreeNode.interpretMessage(message)){
                    Screen.getSoundController().playClip(1);
                }

                if (splitMessage[1].equals("WON")){
                    getPresenterDisplay().forceNodeSwitch(NodeNames.AUCTION_COMPLETE);
                }
                else if(splitMessage[1].equals("DONE")){
                    GeneralGameNode.changePlayerBack();
                    getPresenterDisplay().forceNodeSwitch(NodeNames.MAIN_PARENT);
                }
                getDisplayInteractor().refreshTurnChangeOptions();

            }
            case "BUILD" ->{
                String [] propertyMessages = message.split("#1839673858#");
                GeneralGameNode.buildProperty(propertyMessages[1]);
                getDisplayInteractor().refreshTurnChangeOptions();
            }
            case "MORTGAGE" ->{
                String [] propertyMessages = message.split("#1839673858#");
                GeneralGameNode.mortgageProperty(propertyMessages[1]);
                getDisplayInteractor().refreshTurnChangeOptions();
            }
            case "UN-MORTGAGE" ->{
                String [] propertyMessages = message.split("#1839673858#");
                GeneralGameNode.unMortgageProperty(propertyMessages[1]);
                getDisplayInteractor().refreshTurnChangeOptions();
            }
            case "COMPLETE" ->{
                getPresenterDisplay().forceNodeSwitch(NodeNames.FINISH_GAME);
            }
            case "BANKRUPTCY" ->{
                GeneralGameNode.bankrupt(Integer.parseInt(splitMessage[1]));
            }
            case "CONNECTED" ->{
                getPresenterDisplay().switchIfConnected();
            }
            case "FAIL" ->{
                getDisplayInteractor().getScreenFactory().getNode(NodeNames.MULTIPLAYER_STALL).handleAsynchronousInput(message);
            }
            case "STEAL" ->{
                GeneralGameNode.steal(Integer.parseInt(splitMessage[1]), Boolean.parseBoolean(splitMessage[2]), Boolean.parseBoolean(splitMessage[3]));
                getDisplayInteractor().refreshTurnChangeOptions();
            }
        }
    }
}
