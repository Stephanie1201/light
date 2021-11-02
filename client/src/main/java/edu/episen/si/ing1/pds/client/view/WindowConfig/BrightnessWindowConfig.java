package edu.episen.si.ing1.pds.client.view.WindowConfig;

import edu.episen.si.ing1.pds.client.socket.RequestSocket;
import edu.episen.si.ing1.pds.client.socket.ResponseSocket;
import edu.episen.si.ing1.pds.client.socket.SocketUtility;
import edu.episen.si.ing1.pds.client.view.WelcomeFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BrightnessWindowConfig extends WelcomeFrame implements ActionListener {
//declaration of variable
    private JButton bconfiguration, bvalider, bsuivant, bretour;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTextField luminterne,valeuraugmente, pourcentageteinte,valeuraugmenteteintepourcentage;
    private JLabel  labelluminterne,labelsituationteinte,labelpourcentageteinte, labeluniteaugmenteteintepourcentage,
            labelaugmenteteintepourcentage,labelaugmente,labelluminterneecoute,labeluniteaugmente,pourcentageteinteunite;


    public BrightnessWindowConfig() {
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);

//label textfiel for insde brightness
       /* JRadioButton r1 = new JRadioButton("maintenir le volet fermer à un certain pourcentage", true);
        r1.setActionCommand("choix1");
        panel.add(r1);*/
        labelsituationteinte = new JLabel("Teinte Automatique par rapport au niveau d'ensoleillement");
        labelsituationteinte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelsituationteinte.setBounds(258, 100, 700, 29);
        labelsituationteinte.setFont(labelsituationteinte.getFont().deriveFont(20.0f));
        labelsituationteinte.setForeground(new Color(155,0,0));
        panel.add(labelsituationteinte);

        labelluminterne = new JLabel("Valeur a partir de laquelle la vitre commence a se teinter (entier entre 0 et 50)");
        labelluminterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelluminterne.setBounds(58, 150, 700, 29);
        panel.add(labelluminterne);

        luminterne = new JTextField();
        luminterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        luminterne.setBounds(720, 150, 70, 40);
        panel.add(luminterne);
        luminterne.setColumns(10);

        labelluminterneecoute = new JLabel("lux");
        labelluminterneecoute.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelluminterneecoute.setBounds(800, 150, 210, 29);
        panel.add(labelluminterneecoute);

        labelpourcentageteinte = new JLabel("A combien de pourcent elle doit se teinte (entre 0 et 100)");
        labelpourcentageteinte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpourcentageteinte.setBounds(58, 200, 600, 29);
        panel.add(labelpourcentageteinte);

        pourcentageteinte= new JTextField();
        pourcentageteinte.setFont(new Font("Tahoma", Font.PLAIN, 32));
        pourcentageteinte.setBounds(620, 200, 70, 40);
        panel.add(pourcentageteinte);
        pourcentageteinte.setColumns(10);

        pourcentageteinteunite = new JLabel("%");
        pourcentageteinteunite.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pourcentageteinteunite.setBounds(700, 200, 210, 29);
        panel.add(pourcentageteinteunite);

        labelaugmente= new JLabel("Lorsque cette valeur va augmenter ou diminue de (entre 0 et 20)");
        labelaugmente.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelaugmente.setBounds(58, 250, 600, 29);
        panel.add(labelaugmente);

        valeuraugmente = new JTextField();
        valeuraugmente.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valeuraugmente.setBounds(620, 250, 70, 40);
        panel.add(valeuraugmente);
        valeuraugmente.setColumns(10);

        labeluniteaugmente= new JLabel("lux");
        labeluniteaugmente.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labeluniteaugmente.setBounds(700, 250, 210, 29);
        panel.add(labeluniteaugmente);


        labelaugmenteteintepourcentage= new JLabel("On augmente ou diminue aussi le pourcentage de la teinte de (entre 0 et 20)");
        labelaugmenteteintepourcentage.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelaugmenteteintepourcentage.setBounds(58, 300, 650, 29);
        panel.add(labelaugmenteteintepourcentage);

        valeuraugmenteteintepourcentage = new JTextField();
        valeuraugmenteteintepourcentage.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valeuraugmenteteintepourcentage.setBounds(700, 300, 70, 40);
        panel.add(valeuraugmenteteintepourcentage);
        valeuraugmenteteintepourcentage.setColumns(10);

        labeluniteaugmenteteintepourcentage= new JLabel("%");
        labeluniteaugmenteteintepourcentage.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labeluniteaugmenteteintepourcentage.setBounds(800, 300, 210, 29);
        panel.add( labeluniteaugmenteteintepourcentage);



        bconfiguration = new JButton("CONFIGURATION DE LA TEINTE DE LA FENETRE");
        bconfiguration.setBounds(250,20,650,50);
        bconfiguration.setBackground(new Color(223,175,44));
        bconfiguration.setFont(bconfiguration.getFont().deriveFont(15.0f));
        panel.add(bconfiguration);

//declaration of button
        bvalider = new JButton("Valider");
        bvalider.setBounds(800,380,92,25);
        panel.add(bvalider);
        bvalider.addActionListener(this);

        bsuivant = new JButton("Suivant");
        bsuivant.setBounds(800,450,92,25);
        panel.add(bsuivant);
        bsuivant.addActionListener(this);

        bretour = new JButton("Retour");
        bretour.setBounds(10,20,110,25);
        panel.add(bretour);
        bretour.addActionListener(this);

    }
        public void actionPerformed(ActionEvent eb) {
            Object source = eb.getSource();
            if(source == bsuivant){

              //  AlgoWindow algo = new AlgoWindow(clientCore, h);


                this.dispose();
                PageOfConfigWindow pc = new PageOfConfigWindow();
                pc.setVisible(true);

            }
            if(source == bretour){
                this.dispose();
                blindConfig bc = new blindConfig();
                bc.setVisible(true);
            }
            if(source == bvalider){

                String vd = luminterne.getText();
                String pd = pourcentageteinte.getText();

                String va = valeuraugmente.getText();
                String pa = valeuraugmenteteintepourcentage.getText();

                int vd_pars,pd_pars,va_pars,pa_pars ;

                if(!isInteger(vd) ||!isInteger(pd)||!isInteger(va) ||!isInteger(pa) ){
                    JOptionPane.showMessageDialog(luminterne,"Saisir un entier !", "ERREUR", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    vd_pars = Integer.parseInt(vd);
                    if( vd_pars > 50|| vd_pars < 0)
                    {
                        JOptionPane.showMessageDialog(luminterne,"La Valeur de debut doit etre comprise entre 0 et 50 lux", "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }

                    pd_pars = Integer.parseInt(pd);
                    if( pd_pars> 100 || pd_pars < 0)
                    {
                        JOptionPane.showMessageDialog(pourcentageteinte,"La pourcentage de debut doit etre comprise entre 0 et 100 %", "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }

                    va_pars = Integer.parseInt(va);
                    if( va_pars > 20|| va_pars < 0)
                    {
                        JOptionPane.showMessageDialog(valeuraugmente,"L'augmentation de la valeur doit etre comprise entre 0 et 20 lux", "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }

                    pa_pars = Integer.parseInt(pa);
                    if( pa_pars> 20 || pa_pars < 0)
                    {
                        JOptionPane.showMessageDialog(valeuraugmenteteintepourcentage,"L'augmentation du pourcentage doit etre comprise entre 0 et 30 %", "ERREUR", JOptionPane.ERROR_MESSAGE);

                    }

                    if(( vd_pars <=50  && vd_pars >= 0) &&( pd_pars <= 100 && pd_pars >= 0) && ( va_pars <=20  && va_pars >= 0) &&( pa_pars <= 20 && pa_pars >= 0)) {
                        RequestSocket request = new RequestSocket();
                        request.setRequest("lum");
                        Map<String, Object> data = new HashMap<>();
                        data.put("valeur_debut", vd_pars);
                        data.put("pourcentage_debut", pd_pars);
                        data.put("valeur_avance", va_pars);
                        data.put("pourcentage_avance", pa_pars);

                        System.out.println(data);
                        request.setData(data);
                        System.out.println(data);

                        JOptionPane.showMessageDialog(luminterne, "configuration prise en compte", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);


                        ResponseSocket response2 = socketUtility.sendRequest(request);
                    }
            }
        }
            }


    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main (String[]args){
        BrightnessWindowConfig bw = new BrightnessWindowConfig();
        bw.setVisible(true);
    }



}
