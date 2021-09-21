package edu.episen.si.ing1.pds.client.view;

import javax.swing.*;
import java.awt.*;


public class Spaces extends WelcomeFrame {

    public static void main(String [] args){

        JLabel label ;
        JPanel panel;
        JButton button;


        panel = new JPanel();
        //this.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.RED);

        label = new JLabel("Bonjour");
        label.setBounds(90,100,260,20);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);

        button = new JButton("Bonjour");
        panel.add(button);
        button.setBounds(410,230,160,20);

    }

    }

