package edu.episen.si.ing1.pds.client.view.WindowConfig;

import edu.episen.si.ing1.pds.client.socket.RequestSocket;
import edu.episen.si.ing1.pds.client.socket.ResponseSocket;
import edu.episen.si.ing1.pds.client.socket.SocketUtility;
import edu.episen.si.ing1.pds.client.view.HomePageView;
import edu.episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;
import edu.episen.si.ing1.pds.client.view.WelcomeFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListOfWindow extends WelcomeFrame implements ActionListener {
    //declaration of variable
    private JButton bconfiguration, bconfiguration4, blist, bretour;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JLabel labelsituationstore, Msgerror;
    private String spaceName;
    private int floorNumber;
    private String nameBuilding;

    public ListOfWindow() {

        spaceName = (String) ((Map) RentedSpacesView.getJc3().getSelectedItem()).get("space_name");
        floorNumber = (int) ((Map) RentedSpacesView.getJc2().getSelectedItem()).get("floor_number");
        nameBuilding = (String) ((Map) RentedSpacesView.getJc1().getSelectedItem()).get("building_name");

        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);

//label textfiel for insde brightness

        labelsituationstore = new JLabel("vous configurez : " + nameBuilding + ", Etage " + floorNumber + "," + spaceName);
        labelsituationstore.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelsituationstore.setBounds(158, 70, 500, 29);
        panel.add(labelsituationstore);

        bretour = new JButton("Retour");
        bretour.setBounds(10, 20, 110, 25);
        panel.add(bretour);
        bretour.addActionListener(this);


        blist = new JButton("Liste des fenêtres mapper");
        blist.setBounds(250, 100, 200, 30);
        panel.add(blist);
        blist.addActionListener(this);

    }

    public void actionPerformed(ActionEvent eb) {
        Object source = eb.getSource();
        if (source == bretour) {
            this.dispose();
            RentedSpacesView rs = new RentedSpacesView();
            rs.setVisible(true);
        } else if (source == blist) {
            RequestSocket request = new RequestSocket();
            request.setRequest("ListFenetreMapper");

            Map<String, Object> data = new HashMap<>();
            data.put("spaceName", spaceName);
            data.put("floorNumber", floorNumber);
            data.put("nameBuilding", nameBuilding);

            request.setData(data);

            //receive response
            ResponseSocket response = socketUtility.sendRequest(request);
            Map<Integer,Map<String, Integer>> ListFenetre = (Map<Integer,Map<String, Integer>>)response.getData();

            if (ListFenetre != null) {
                for (Map.Entry mapentry : ListFenetre.entrySet()) {

                    Map<String, Integer> map = (Map<String, Integer>) mapentry.getValue();
                    System.out.println(map);
                    bconfiguration = new JButton("Fenetre electro-chromatique id " + map.get("equipment_id"));
                    bconfiguration.setBounds(158, 200, 300, 50);
                    bconfiguration.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            dispose();
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
                }
                panel.add(bconfiguration);
            }

        } else {
            Msgerror = new JLabel("Aucune fenetre n'a été placé ici");
            Msgerror.setFont(new Font("Tahoma", Font.PLAIN, 12));
            Msgerror.setBounds(358, 70, 500, 29);
            panel.add(Msgerror);
        }
    }


    public static void main(String[] args) {
        ListOfWindow bc = new ListOfWindow();
        bc.setVisible(true);
    }


}

