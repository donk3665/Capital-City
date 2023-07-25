package Entities.ExternalDataTransfer;

/**
 * Input Entity that stores all the input values
 */
public class Input {
    private String input;

    public Input(){
        this.input = "-1";
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return this.input;
    }
}
