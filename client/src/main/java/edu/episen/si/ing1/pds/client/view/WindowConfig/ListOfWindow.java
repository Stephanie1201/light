package edu.episen.si.ing1.pds.client.view.WindowConfig;

import edu.episen.si.ing1.pds.client.socket.SocketUtility;
import edu.episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;
import edu.episen.si.ing1.pds.client.view.WelcomeFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListOfWindow extends WelcomeFrame implements ActionListener {
    //declaration of variable
    private JButton bconfiguration,bconfiguration4, bvalider, bsuivant , bretour;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTextField debutstore,valeuraugmente, pourcentagestore,valeuraugmentestorepourcentage;
    private JLabel  labeldebutstore,labelsituationstore,labelpourcentagestore, labeluniteaugmentestorepourcentage,
            labelaugmentestorepourcentage,labelaugmente,labeldebutstoreecoute,labeluniteaugmente,pourcentagestoreunite;


    public ListOfWindow() {
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);

//label textfiel for insde brightness

        labelsituationstore = new JLabel("vous configurez : Open Space 1, Etage3, Batiment Condorcet  ");
        labelsituationstore.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelsituationstore.setBounds(158, 100, 500, 29);
        panel.add(labelsituationstore);

        bretour = new JButton("Retour");
        bretour.setBounds(10,20,110,25);
        panel.add(bretour);
        bretour.addActionListener(this);


        bconfiguration = new JButton("Fenetre electro-chromatique 1");
        bconfiguration.setBounds(250,150,250,50);
        panel.add(bconfiguration);
        bconfiguration.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blindConfig bc = new blindConfig();
                bc.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        bconfiguration4 = new JButton("Fenetre electro-chromatique 2");
        bconfiguration4.setBounds(250,250,250,50);

        panel.add(bconfiguration4);

//declaration of button


    }
    public void actionPerformed(ActionEvent eb) {
        Object source = eb.getSource();
        /*if(source == bconfiguration ){
            this.dispose();
            blindConfig bc = new blindConfig();
            bc.setVisible(true);
        }*/
        if(source == bretour){
            this.dispose();
            RentedSpacesView rs = new RentedSpacesView();
            rs.setVisible(true);
        }

    }

    public static void main (String[]args){
        ListOfWindow bc = new ListOfWindow();
        bc.setVisible(true);
    }



}

