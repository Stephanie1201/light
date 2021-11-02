package edu.episen.si.ing1.pds.client.view;


        import edu.episen.si.ing1.pds.client.model.Company;
        import edu.episen.si.ing1.pds.client.socket.RequestSocket;
        import edu.episen.si.ing1.pds.client.socket.ResponseSocket;
        import edu.episen.si.ing1.pds.client.socket.SocketUtility;
        import edu.episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;
        import edu.episen.si.ing1.pds.client.view.SpaceRental.FirstPageRentCriteria;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.Vector;

public class HomePageView extends WelcomeFrame implements ActionListener {
    private JPanel panel;
    private JLabel j1,j2,j3,j4;
    private JButton b1,b2,b3;
    private JComboBox jcb1;
    private SocketUtility socketUtility = new SocketUtility();

    public HomePageView(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Light");
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);


        j1 = new JLabel("Bienvenue dans Light");
        j1.setBounds(30,360,240,20);
        j1.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(j1);


        j2 = new JLabel("Selectionnez votre entreprise");
        j2.setBounds(390,80,260,20);
        j2.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(j2);

        RequestSocket requestSocket = new RequestSocket();
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

        jcb1.addItemListener(new ItemListener () {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int company_id = (Integer) item.get("company_id");
                    String company_name = (String) item.get("company_name");
                    Company.setCompany_id (company_id);
                    Company.setCompany_name (company_name);

                }
            }
        });



        j4 = new JLabel("Je visualise mes espaces");
        j4.setBounds(410,230,200,20);
        j4.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(j4);

        b2 = new JButton("Voir mes espaces");
        b2.setBounds(440,280,140,20);
        b2.addActionListener(this);
        panel.add(b2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == b2) {
            this.dispose();
            RentedSpacesView r = new RentedSpacesView();
            r.setVisible(true);

        }
    }

    public static void main(String[] args) {
        HomePageView hpm = new HomePageView();
        hpm.setVisible(true);
    }

}

