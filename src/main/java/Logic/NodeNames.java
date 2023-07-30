package Logic;

public enum NodeNames {
    //Initial Node Names
    INITIAL_PARENT,
    SETTINGS_INITIAL,
    CREDITS,
    QUIT_INITIAL,

    SELECT_GAME_TYPE,
    SELECT_NUMBER_OF_PLAYERS,
    CONFIRM_LOADED_GAME,
    CONFIRM_NEW_GAME,
    SELECT_SAVE,
    SELECT_GAME_MODE,
    NUMBER_OF_ROUNDS,
    NO_SAVES,

    // Main Node Names
    MAIN_PARENT,

    // Bankruptcy nodes
    BANKRUPTCY,

    // End turn nodes
    END_TURN,
    FINISH_GAME,

    // Manage property nodes
    BUILD_PROPERTY,
    MORTGAGE,
    NO_PROPERTIES,
    SELECT_PROPERTY,
    SELECT_ACTION_PROPERTY,
    UN_MORTGAGE,

    // Settings nodes
    EXIT_GAME,
    SAVE_GAME,
    SETTINGS,

    // Steal nodes

    PERFORM_STEAL,
    STEAL,

    // Trade nodes
    SEND_TRADE,
    NOTHING_TO_TRADE,
    PICK_ITEM_OPPONENT,
    PICK_ITEM_SELF,
    PICK_PLAYER,
    TRADE, // SAME AS PICK_PLAYER, IS NOT ASSIGNED TO NODE

    // Trade opponent nodes
    ACCEPT_TRADE,
    DECLINE_TRADE,
    TRADE_OPPONENT_PARENT,

    // Roll nodes
    ROLL,
    CALL_ACTION,
    ALREADY_ROLLED,

    // Empty property nodes
    BUY_PROPERTY,
    EMPTY_PROPERTY,

    // Auction tree nodes
    AUCTION_ENTRY,
    AUCTION_PARENT,
    FOLD,
    HIGH_OPTION,
    LOW_OPTION,
    MEDIUM_OPTION,
    AUCTION_COMPLETE

}
