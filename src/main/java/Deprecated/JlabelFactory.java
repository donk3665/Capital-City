//package Entities.GUI.Screens;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.Objects;
//
//public class JlabelFactory {
//    JLabel bg;
//    public JlabelFactory(){
//        String imageLink = "/backgrounds/bg1.jpg";
//        ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource(imageLink)));
//        Image temp = image.getImage();
//        Image temp2 = temp.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
//        image = new ImageIcon(temp2);
//        //       ImageIcon im = new ImageIcon(image);
//        bg = new JLabel(image);
//        bg.setBounds(0, 0, 1920, 1080);
//    }
//
//    public JLabel getBg() {
//        return bg;
//    }
//}
