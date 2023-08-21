package Logic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;

public interface NodeInterface {
    State create_state();

    NodeInterface performInput(InputInformation input);

}
