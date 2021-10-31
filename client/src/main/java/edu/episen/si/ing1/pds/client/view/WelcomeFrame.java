package edu.episen.si.ing1.pds.client.view;

import javax.swing.*;
import java.awt.*;


public class WelcomeFrame extends JFrame {
    private JPanel panWest,pan2,pan3;
    private JLabel jlabel;

    public WelcomeFrame() {
        super("Light");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        BorderLayout bl = new BorderLayout();
        jlabel = new JLabel("Lightcompany");
        jlabel.setFont(jlabel.getFont().deriveFont(30.0f));


        panWest = new JPanel();
        panWest.setLayout(new BorderLayout());
        panWest.add(jlabel,BorderLayout.WEST);
        panWest.setBackground(new Color(201,200,113));
        panWest.setPreferredSize(new Dimension(500,70));
        this.add(panWest, BorderLayout.PAGE_START);

        pan2 = new JPanel();
        pan2.setBackground(new Color(201,200,113));
        pan2.setPreferredSize(new Dimension(500,70));
        this.add(pan2, BorderLayout.PAGE_END);

        pan3 = new JPanel();
        this.add(pan3, BorderLayout.CENTER);
        this.setSize(1000,700);

    }


    public static void main(String[] args) {
        WelcomeFrame wf = new WelcomeFrame();
        wf.setVisible(true);
    }
}

