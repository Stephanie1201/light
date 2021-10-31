package edu.episen.si.ing1.pds.client.view.Mapping;

import edu.episen.si.ing1.pds.client.model.Building;
import edu.episen.si.ing1.pds.client.model.Floor;
import edu.episen.si.ing1.pds.client.model.Position;
import edu.episen.si.ing1.pds.client.model.Space;
import edu.episen.si.ing1.pds.client.socket.RequestSocket;
import edu.episen.si.ing1.pds.client.socket.ResponseSocket;
import edu.episen.si.ing1.pds.client.socket.SocketUtility;
import edu.episen.si.ing1.pds.client.view.WelcomeFrame;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpaceView extends WelcomeFrame {
    private ImageIcon image;
    private JPanel jp1, jp2,jp3;
    private JLabel jl1,jl2,jl3;
    private JButton jb1, jb2;
    private GridBagLayout gbl;
    private Box box1, box2;
    private SpaceView spaceView;
    private int x1,x2;
    private String fileLocation;
    protected static boolean isPopUpActive = false;
    private final SocketUtility socketUtility = new SocketUtility();



    public SpaceView(String fileLocation, int x1, int x2){
        this.fileLocation = fileLocation;
        this.x1 = x1;
        this.x2 = x2;
        jb1 = new JButton("Retour");
        jb2 = new JButton("Rafraichir");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel() {
            Image img;
            {
                try {
                    img = ImageIO.read(new File(fileLocation));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setOpaque(false);
            }

            public void paintComponent(Graphics graphics) {
                graphics.drawImage(img, 0, 0, this);
                super.paintComponent(graphics);
            }
        };
        jp3.setLayout(null);


        RequestSocket request = new RequestSocket();
        request.setRequest("position");
        Map<String, Object> hm = new HashMap<>();
        hm.put("space_id", Space.getSpace_id());
        request.setData(hm);
        ResponseSocket response = socketUtility.sendRequest(request);
        List<Map> positionList = (List<Map>) response.getData();
        for (Map m : positionList){
            try{
                if ((boolean) m.get("available")){
                    Icon blue_icon = new ImageIcon(ImageIO.read(new File(System.getenv("IMG")+"\\blue_icon.png")));
                    JLabel blue_icon_label = new JLabel();
                    blue_icon_label.setIcon(blue_icon);
                    blue_icon_label.setBounds((int) m.get("x_position"),(int) m.get("y_position"),40,40);
                    spaceView = this;
                        blue_icon_label.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (!isPopUpActive) {
                                    Position.setPosition_id((int) m.get("position_id"));
                                    Position.setX_position((int) m.get("x_position"));
                                    Position.setY_position((int) m.get("y_position"));
                                    isPopUpActive = true;
                                    PlaceEquipmentView p = new PlaceEquipmentView(spaceView);
                                    p.setVisible(true);
                                }
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
                    jp3.add(blue_icon_label);
                }
                else{
                    RequestSocket request2 = new RequestSocket();
                    request2.setRequest("equipment_on_position");
                    Map<String, Object> hm2 = new HashMap<>();
                    hm2.put("position_id", m.get("position_id"));
                    request2.setData(hm2);
                    ResponseSocket response2 = socketUtility.sendRequest(request2);
                    List<Map> equipment_list = (List<Map>) response2.getData();
                    String file_img ="";
                    System.out.println (equipment_list);
                    if (equipment_list.size ()!=0)
                    {
                        if ((boolean) equipment_list.get(0).get("equipment_state")){
                            file_img = "red_icon_activated.png";
                        }

                        else{
                            file_img = "red_icon_desactivated.png";
                        }
                    } else if (file_img.equals ("")) {
                        file_img = "red_icon_desactivated.png";
                    }

                    Icon red_icon = new ImageIcon(ImageIO.read(new File(System.getenv("IMG")+"\\"+file_img)));
                    JLabel red_icon_label = new JLabel();
                    red_icon_label.setIcon(red_icon);
                    red_icon_label.setBounds((int) m.get("x_position"),(int) m.get("y_position"),40,40);
                    spaceView = this;
                    jp3.add(red_icon_label).addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Position.setPosition_id((int) m.get("position_id"));
                            RequestSocket request = new RequestSocket();
                            request.setRequest("equipment_is_uninstalled");
                            Map<String, Object> hm = new HashMap<>();
                            hm.put("position_id", Position.getPosition_id());
                            request.setData(hm);
                            ResponseSocket response = socketUtility.sendRequest(request);
                            Map<String, Object> isUninstalledMap = (Map) response.getData();
                            boolean isUninstalled = (boolean) isUninstalledMap.get("isUninstalled");
                            if (!isPopUpActive) {
                                if (!isUninstalled) {
                                    isPopUpActive = true;
                                    //EquipmentCheckView ec = new EquipmentCheckView(spaceView);
                                   // ec.setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(getContentPane(), "Position deja liberee !");
                                    dispose();
                                    SpaceView sp = new SpaceView(getFileLocation(),x1,x2);
                                    sp.setVisible(true);
                                }
                            }
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
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }


        jb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                RentedSpacesView r = new RentedSpacesView();
                r.setVisible(true);
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

        jb2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                SpaceView sp = new SpaceView(getFileLocation(), x1, x2);
                sp.setVisible(true);
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

        box1 = Box.createHorizontalBox();
        box2 = Box.createHorizontalBox();

        jl1 = new JLabel("Votre espace : " + Space.getSpace_name() + " situe dans l'etage " +  Floor.getFloor_number() + " du " + Building.getBuiling_name());

        jp1.setLayout(new BorderLayout());
        jp2.setPreferredSize(new Dimension(950,50));
        box1.setPreferredSize(new Dimension(950,50));
        box1.add(jb1);
        box1.add(Box.createHorizontalStrut(x1));
        box1.add(jl1);
        box1.add(Box.createHorizontalStrut(150));
        box1.add(jb2);

        //increase the argument of createHorizontalStrut to move the panel to the left or the right
        box2.add(Box.createHorizontalStrut(x2));
        box2.add(jp3);

        jp2.add(box1);
        this.add(jp1);
        jp1.add(jp2, BorderLayout.NORTH);
        jp1.add(box2);

    }

    protected int getX1() {
        return x1;
    }

    protected int getX2() {
        return x2;
    }

    protected String getFileLocation() {
        return fileLocation;
    }
}

