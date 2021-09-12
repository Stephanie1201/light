package edu.episen.si.ing1.pds.client.view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class HomePageView extends WelcomeFrame implements ActionListener {
    private JPanel panel;
    private JLabel labelbienvenue,labelselect,j3,j4;
    private JButton bselect,b2,b3;
    private JComboBox jcb1;

    public HomePageView(){

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Light");
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);


        labelbienvenue = new JLabel("Bienvenue dans Lightcompany");
        labelbienvenue.setBounds(400,10,340,20);
        labelbienvenue.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(labelbienvenue);


        labelselect = new JLabel("Selectionnez votre entreprise");
        labelselect.setBounds(390,200,260,20);
        labelselect.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(labelselect);

        bselect = new JButton("List des entreprises");
        bselect.setBounds(410,230,160,20);
        bselect.addActionListener(this);
        panel.add(bselect);

       /* RequestSocket requestSocket = new RequestSocket();
        requestSocket.setRequest("company_list");
        Map<String, Object> data = new HashMap<> ();
        requestSocket.setData(data);


        ResponseSocket response = socketUtility.sendRequest(requestSocket);
        java.util.List<Map> companyList = (List<Map>) response.getData();

        jcb1 = new JComboBox(new Vector (companyList));
        jcb1.setBounds(410,130,200,20);
        panel.add(jcb1);

        jcb1.setSelectedIndex(-1);
        jcb1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("company_name").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Selectionner votre entreprise");

                return this;
            }
        });
*/
   /*
        jcb1.addItemListener(new ItemListener () {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int company_id = (Integer) item.get("company_id");
                    String company_name = (String) item.get("company_name");
                    //Company.setCompany_id (company_id);
                    //Company.setCompany_name (company_name);

                }
            }
        });
*/
        /*
        j3 = new JLabel("Premiere fois ?");
        j3.setBounds(410,210,200,20);
        j3.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(j3);

        b1 = new JButton("Je loue");
        b1.setBounds(440,250,140,20);
        b1.addActionListener(this);
        panel.add(b1);


        j4 = new JLabel("Je visualise mes espaces");
        j4.setBounds(410,330,200,20);
        j4.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(j4);

        b2 = new JButton("Voir mes espaces");
        b2.setBounds(440,380,140,20);
        b2.addActionListener(this);
        panel.add(b2);
*/

    }
/*
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == b1) {

            RequestSocket requestSocket = new RequestSocket();
            requestSocket.setRequest("Insert_Rental");
            Map<String, Object> data = new HashMap<>();
            data.put("company_id", Company.getCompany_id());
            requestSocket.setData(data);
            ResponseSocket responseRental = socketUtility.sendRequest(requestSocket);
            dispose();
            FirstPageRentCriteria f = new FirstPageRentCriteria();
            f.setVisible(true);


        } else if (source == b2) {
            this.dispose();
            RentedSpacesView r = new RentedSpacesView();
            r.setVisible(true);
        }*/


    public static void main(String[] args) {
        HomePageView hpm = new HomePageView();
        hpm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
