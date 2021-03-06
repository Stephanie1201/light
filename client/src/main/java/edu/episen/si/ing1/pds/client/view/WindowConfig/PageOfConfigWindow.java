package edu.episen.si.ing1.pds.client.view.WindowConfig;

import edu.episen.si.ing1.pds.client.socket.RequestSocket;
import edu.episen.si.ing1.pds.client.socket.ResponseSocket;
import edu.episen.si.ing1.pds.client.socket.SocketUtility;
import edu.episen.si.ing1.pds.client.view.WelcomeFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PageOfConfigWindow extends WelcomeFrame implements ActionListener {
    //initialization of variable
    private JButton bconf,betat, braf, bs, br;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel p;
    private JLabel labeltempextfiel,labelunite,labelfenetre,labelinstructionR,labeltempintfiel,labelpStore,labelluminterne,labellumiexterne,labelpteinte,labelinstruction;
    private static final Logger logger = LoggerFactory.getLogger(PageOfConfigWindow.class.getName());
    private String equipment_id;

    public PageOfConfigWindow(String equipment_id){
      this.equipment_id=equipment_id;
        p = new JPanel();
        this.add(p);
        p.setLayout(null);

        labelfenetre= new JLabel("Fenetre fermee");
        labelfenetre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelfenetre.setBounds(58, 100, 210, 29);
        p.add(labelfenetre);

        labeltempextfiel = new JLabel("Temperature exterieure");
        labeltempextfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempextfiel.setBounds(58, 200, 210, 29);
        p.add(labeltempextfiel);

        labelunite = new JLabel("degre");
        labelunite.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelunite.setBounds(400, 200, 210, 29);
        p.add(labelunite);

        labelluminterne = new JLabel("Niveau d'ensoleillement");
        labelluminterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelluminterne.setBounds(58, 250, 210, 29);
        p.add(labelluminterne);

        labeltempextfiel = new JLabel("0");
        labeltempextfiel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labeltempextfiel.setBounds(358, 200, 210, 29);
        p.add(labeltempextfiel);


        labelluminterne = new JLabel("0");
        labelluminterne.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelluminterne.setBounds(358, 250, 210, 29);
        p.add(labelluminterne);

        labelunite = new JLabel("lux");
        labelunite.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelunite.setBounds(400, 250, 210, 29);
        p.add(labelunite);


        labelpStore = new JLabel("Store ferme a ");
        labelpStore.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpStore.setBounds(58, 300, 210, 29);
        labelpStore.setFont(labelpStore.getFont().deriveFont(20.0f));
        p.add(labelpStore);


        labelpStore = new JLabel("storeOuvert");
        labelpStore.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labelpStore.setBounds(358, 300, 210, 29);
        labelpStore.setFont(labelpStore.getFont().deriveFont(22.0f));
        p.add(labelpStore);

        labelunite = new JLabel("%");
        labelunite.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelunite.setBounds(400, 300, 210, 29);
        p.add(labelunite);


        labelpteinte = new JLabel("Vitre teinte a ");
        labelpteinte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpteinte.setBounds(58, 350, 210, 29);
        labelpteinte.setFont(labelpStore.getFont().deriveFont(20.0f));
        p.add(labelpteinte);

        labelpteinte = new JLabel("pas_teinter");
        labelpteinte.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labelpteinte.setBounds(358, 350, 210, 29);
        labelpteinte.setFont(labelpStore.getFont().deriveFont(20.0f));
        p.add(labelpteinte);

        labelunite = new JLabel("%");
        labelunite.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelunite.setBounds(400, 350, 210, 29);
        p.add(labelunite);

        bconf = new JButton("CONFIGURATION DE LA FENETRE");
        bconf.setBounds(250,20,650,30);
        bconf.setBackground(new Color(111,164,143));
        bconf.setFont(bconf.getFont().deriveFont(19.0f));
        p.add(bconf);

        betat = new JButton("Etat actuel");
        betat.setBounds(450,100,150,40);
        betat.setBackground(new Color(111,255,130));
        betat.setFont(betat.getFont().deriveFont(18.0f));
        p.add(betat);

        braf = new JButton("Rafraichir");
        braf .setBounds(800,380,92,25);
        braf.setBackground(Color.GREEN);
        p.add(braf );
        braf .addActionListener(this);

        bs = new JButton("Sortir");
        bs.setBounds(800,450,92,25);
        p.add(bs);
        bs.addActionListener(this);

        br = new JButton("Retour");
        br.setBounds(10,20,110,25);
        p.add(br);
        br.addActionListener(this);
    }

    public static void main(String[] args) {
        PageOfConfigWindow c = new PageOfConfigWindow("equipment_id");
        c.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if(source == bs){
            this.dispose();
            ListOfWindow bc = new ListOfWindow();
            bc.setVisible(true);
        }
        if(source == br){

            BrightnessWindowConfig tc = new BrightnessWindowConfig (equipment_id);
            this.dispose();
            tc.setVisible(true);
        }
        if(source == braf){
            // creation of the request
            RequestSocket request = new RequestSocket();
            request.setRequest("EtatActuel");
            Map<String, Object> data = new HashMap<>();
            data.put("equipment_id",equipment_id);
            logger.info("Id equipement envoy??:::::::::::::::::::::::: " + data);
            request.setData(data);


            //receive response
                 ResponseSocket response = socketUtility.sendRequest(request);
                    Map<String, Integer>  valeurActu = (Map<String,Integer>) response.getData();

            logger.info("Voici la r??ponse que le serveur ?? envoyer pour l'EtatActuel {} " + valeurActu);
            AlgoWindow algo = new AlgoWindow(valeurActu);
           ArrayList<String> listActu = algo.algoWindow();

            Integer level_sunlight = Integer.parseInt(listActu.get(0));
            Integer blind= Integer.parseInt(listActu.get(1));
            Integer bright = Integer.parseInt(listActu.get(2));
            Integer outside = valeurActu.get("outside_temperature");

            Integer n = level_sunlight;
            String str = n.toString();
            labelluminterne.setText(str);


            Integer n2 = bright;
            String str2 = n2.toString();
            labelpteinte.setText(str2);


            Integer n5 = outside;
            String str5 = n5.toString();
            labeltempextfiel.setText(str5);

            Integer n6 = blind;
            String str6 = n6.toString();
            labelpStore.setText(str6);

        }
    }
}




