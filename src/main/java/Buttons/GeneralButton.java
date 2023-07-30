package Buttons;

public class GeneralButton extends ButtonMapping{
    private final String returnString;

    public GeneralButton(String returnString){
        this.returnString = returnString;
    }
    @Override
    public String pressedButton() {
        return returnString;
    }
}
