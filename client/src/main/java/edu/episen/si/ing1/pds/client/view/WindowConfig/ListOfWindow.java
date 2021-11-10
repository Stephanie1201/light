package edu.episen.si.ing1.pds.client.view.WindowConfig;

import edu.episen.si.ing1.pds.backend.server.BackendService;
import edu.episen.si.ing1.pds.client.socket.RequestSocket;
import edu.episen.si.ing1.pds.client.socket.ResponseSocket;
import edu.episen.si.ing1.pds.client.socket.SocketUtility;
import edu.episen.si.ing1.pds.client.view.HomePageView;
import edu.episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;
import edu.episen.si.ing1.pds.client.view.WelcomeFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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

    private static final Logger logger = LoggerFactory.getLogger(ListOfWindow.class.getName());
    public ListOfWindow() {


        spaceName = (String) ((Map) RentedSpacesView.getJc3().getSelectedItem()).get("space_name");
        floorNumber = (int) ((Map) RentedSpacesView.getJc2().getSelectedItem()).get("floor_number");
        nameBuilding = (String) ((Map) RentedSpacesView.getJc1().getSelectedItem()).get("building_name");

        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);


        labelsituationstore = new JLabel("Vous configurez les fenetres du : " + nameBuilding + ", Etage " + floorNumber + "," + spaceName);
        labelsituationstore.setFont(new Font("Tahoma", Font.PLAIN, 10));
        labelsituationstore.setBounds(0, 50, 800, 50);
        labelsituationstore.setForeground(new Color(20,0,255));
        panel.add(labelsituationstore);

        bretour = new JButton("Retour");
        bretour.setBounds(10, 20, 110, 25);
        panel.add(bretour);
        bretour.addActionListener(this);


        blist = new JButton("Liste des fenetres a Configurer");
        blist.setBounds(0, 130, 300, 25);
        blist.setBackground(Color.ORANGE);
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
                int x=20;
                int y=0;

                setLayout(new GridLayout(1, 2));
                ArrayList<JButton> jButtons = new ArrayList<>();

                for (Map.Entry mapentry : ListFenetre.entrySet()) {
                    Map<String, Integer> map = (Map<String, Integer>) mapentry.getValue();
                    logger.info("Donnees Recuperees {} " + map);
                    bconfiguration = new JButton(" Fenêtre " + map.get("equipment_id"));

                    y+=200;
                    bconfiguration.setBounds(x, y, 300, 50);
                    jButtons.add(bconfiguration);
                    panel.add(bconfiguration);
                    panel.revalidate();
                }
                for(JButton button: jButtons){
                    button.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            dispose();
                            blindConfig bc = new blindConfig(button.getText());
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
            }

        } else {
            Msgerror = new JLabel("Aucune fenetre n'a ete place ici");
            Msgerror.setFont(new Font("Tahoma", Font.PLAIN, 12));
            Msgerror.setBounds(20, 70, 500, 29);
            panel.add(Msgerror);
        }
    }


    public static void main(String[] args) {
        ListOfWindow bc = new ListOfWindow();
        bc.setVisible(true);
    }


}

