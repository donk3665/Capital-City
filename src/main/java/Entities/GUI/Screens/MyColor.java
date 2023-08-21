package Entities.GUI.Screens;

import java.awt.*;

public class MyColor {
    private static Color[] colorArray = new Color[]{
            new Color(0xF639F6),
            new Color(0xFFFF00),
            new Color(0xFF0000),
            new Color(0x06C200),
            new Color(0x8B4513),
            new Color(0xFF6200),
            new Color(0x8F008F),
            new Color(0x2EF1F1),
            new Color(0x0C27CE),
    };
    private static Color color;
    public MyColor(){
        color = Color.WHITE;
    }

    /**
     * Get the color from a string name
     *
     * @param col name of the color
     * @return White if no color is given, otherwise the Color object
     */
    static Color getColor(String col) {
        switch (col.toLowerCase()) {
            case "brown":
                color = colorArray[4];
                break;
            case "light_blue":
                color = colorArray[7];
                break;
            case "dark_blue":
                color = colorArray[8];
                break;
            case "pink":
                color = colorArray[0];
                break;
            case "orange":
                color = colorArray[5];
                break;
            case "red":
                color = colorArray[2];
                break;
            case "yellow":
                color = colorArray[1];
                break;
            case "green":
                color = colorArray[3];
                break;
        }
        return color;
    }
    static Color getColor(int num) {
        return colorArray[num];
    }
}
