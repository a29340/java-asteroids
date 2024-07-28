package com.a29340;


import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        JFrame frame = new JFrame();

        SwingUtilities.invokeLater(() -> {
            frame.setTitle("Java Asteroids");
            MainPanel mainPanel = new MainPanel();
            frame.add(mainPanel);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.pack();
        });
    }


}