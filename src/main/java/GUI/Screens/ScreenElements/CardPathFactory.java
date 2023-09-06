package GUI.Screens.ScreenElements;

import java.util.HashMap;

public class CardPathFactory {

    public String getCard(int index){
        HashMap<Integer, Integer> map = new HashMap<>(){
            {
                put(0,33);
                put(1,1);
                put(2,32);
                put(3,2);
                put(4,29);
                put(5,25);
                put(6,3);
                put(7,31);
                put(8,4);
                put(9,5);
                put(10,34);
                put(11,6);
                put(12,24);
                put(13,7);
                put(14,8);
                put(15,26);
                put(16,9);
                put(17,32);
                put(18,10);
                put(19,11);
                put(20,35);
                put(21,12);
                put(22,31);
                put(23,13);
                put(24,14);
                put(25,27);
                put(26,15);
                put(27,16);
                put(28,23);
                put(29,17);
                put(30,36);
                put(31,18);
                put(32,19);
                put(33,32);
                put(34,20);
                put(35,28);
                put(36,31);
                put(37,21);
                put(38,30);
                put(39,22);
            }
        };
        return "/Images/InGameAssets/Cards/Picture"+map.get(index)+".png";
    }

}
