package edu.episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.episen.si.ing1.pds.backend.server.socket.ResquestSocket;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class.getName());

    public static void sendResponse(ResquestSocket request, PrintWriter writer, Connection connection) throws Exception {

        String requestName = request.getRequest();
        if (requestName.equals("insert")) { //name of request
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            String firstname  = (String) dataLoaded.get("firstname");
            String lastname  = (String) dataLoaded.get("lastname");
            String query = "insert into student (firstname,lastname) VALUES ('"+firstname+"','"+lastname+"')";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("Response", "Inserted successfully!");

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg); //how?
            logger.info("Response submitted to client!");

        }

        /*else if (requestName.equals("select")) {  //name of request
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            int id  =  (Integer) dataLoaded.get("id"); //recupération of ID
            List<Map> select = new ArrayList<>();//Why a list ?
            String query = "select * from student where id=?" ;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("id", rs.getInt("id"));
                hm.put("firstname", rs.getString("firstname"));
                hm.put("lastname", rs.getString("lastname"));
                select.add(hm);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("Response", "select successfully!");
            response.put("data", select);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
            logger.info("Response submitted to client!");

        }

        else if (requestName.equals("delete")) { //name of request
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            int id  = (Integer) dataLoaded.get("id");
            String query = "DELETE from student Where id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int nb=statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("Response", "Deleted successfully"+nb);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
            logger.info("Response submitted to client!"+nb);

        }

        else if (requestName.equals("update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();

            int id=(Integer) dataLoaded.get("id");
            String firstname  = (String) dataLoaded.get("firstname");
            String lastname  = (String) dataLoaded.get("lastname");
            String newfirstname  = (String) dataLoaded.get("newfirstname");
            String newlastname  = (String) dataLoaded.get("newlastname");
            System.out.println(id +firstname + lastname +newfirstname+ newlastname);

           String query = "UPDATE student SET firstname=' "+newfirstname+" ',lastname= ' "+newlastname+"'  WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("Response", "Updated successfully!");

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg); //how?
            logger.info("Response submitted to client!");


        }*/
        else if (requestName.equals("company_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "select company_id, company_name from company";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("company_id", rs.getInt("company_id"));
                hm.put("company_name", rs.getString("company_name"));
                name.add(hm);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            System.out.println("here is "+responseMsg);
            writer.println(responseMsg);

        }
        else if (requestName.equals("position")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> position = new ArrayList<>();
            String sql = "select position_id, x_position, y_position, available from position_ where space_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("space_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("position_id", rs.getInt("position_id"));
                hm.put("x_position", rs.getInt("x_position"));
                hm.put("y_position", rs.getInt("y_position"));
                hm.put("available", rs.getBoolean("available"));
                position.add(hm);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", position);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("equipment_on_position")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> equipment = new ArrayList<>();
            String sql = "SELECT equipment_id, equipment_name, equipment_state from position_ NATURAL JoiN equipment where position_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("position_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("equipment_id", rs.getInt("equipment_id"));
                hm.put("equipment_name", rs.getString("equipment_name"));
                hm.put("equipment_state", rs.getBoolean("equipment_state"));
                equipment.add(hm);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", equipment);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        if (requestName.equals("building_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> building = new ArrayList<>();
            String sql = "Select distinct(building_id), building_name  from Building Natural Join Floor_ Natural Join Space Natural Join Rental Natural Join Maintenance_Department_Administrators Where company_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            // setInt permits to put value in sql variable.
            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("building_id", rs.getInt("building_id"));
                hm.put("building_name", rs.getString("building_name"));
                building.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", building);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Insert_Rental")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            int company_id  = (int) dataLoaded.get("company_id");
            String querys = " insert into rental (id_mda) SELECT Distinct (rental.id_mda)  from rental INNER JOIN maintenance_department_administrators  on rental.id_mda = maintenance_department_administrators.id_mda where company_id = " + company_id + " ";
            PreparedStatement statement = connection.prepareStatement(querys);
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }

        else if (requestName.equals("floor_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> floor = new ArrayList<>();
            String sql = "Select Distinct(floor_id), floor_number from Floor_ Natural Join Space Natural Join Rental Natural Join Maintenance_Department_Administrators Where company_id=? and building_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            statement.setInt(2, (Integer) dataLoaded.get("building_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("floor_id", rs.getInt("floor_id"));
                hm.put("floor_number", rs.getInt("floor_number"));
                floor.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", floor);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("space_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> spaces = new ArrayList<>();
            String sql = "Select Distinct(space_id), space_name, spacetype_id from Space Natural Join Rental Natural Join Maintenance_Department_Administrators Where company_id= ? and floor_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            statement.setInt(2, (Integer) dataLoaded.get("floor_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("space_id", rs.getInt("space_id"));
                hm.put("space_name", rs.getString("space_name"));
                hm.put("space_type", rs.getInt("spacetype_id"));
                spaces.add(hm);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", spaces);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("equipment_is_uninstalled")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> floor = new ArrayList<>();
            String sql = "select equipment_id from position_ where equipment_id is not null and position_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("position_id"));
            ResultSet rs = statement.executeQuery();
            boolean isUninstalled = true;
            while (rs.next()) {
                isUninstalled = false;
            }
            Map<String, Object> hm = new HashMap<>();
            hm.put("isUninstalled", isUninstalled);
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("equipment_on_position")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> equipment = new ArrayList<>();
            String sql = "SELECT equipment_id, equipment_name, equipment_state from position_ NATURAL JoiN equipment where position_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("position_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("equipment_id", rs.getInt("equipment_id"));
                hm.put("equipment_name", rs.getString("equipment_name"));
                hm.put("equipment_state", rs.getBoolean("equipment_state"));
                equipment.add(hm);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", equipment);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("equipment_is_used")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            String sql = "select equipment_id from equipment where equipment_id not in(select equipment_id from position_ where equipment_id is not null) and equipment_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, (Integer) dataLoaded.get("equipment_id"));
            ResultSet rs = statement.executeQuery();
            boolean isUsed = false;
            while (rs.next()) {
                isUsed = true;
            }
            Map<String, Object> hm = new HashMap<>();
            hm.put("isUsed", isUsed);
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);


        }

        else if(requestName.equals("mapwindow")){

            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();

            String building = (String) dataLoaded.get("building");
            String floor = (String) dataLoaded.get("floor");
            String space = (String) dataLoaded.get("space");

            logger.info(String.valueOf(dataLoaded));
            /*String sql = "select count(*) as nb from equipment inner join position_ ON equipment.equipment_id = position_.equipment_id " +
                    "INNER JOIN space sp " +
                    "ON sp.space_id = position_.space_id " +
                    "INNER JOIn floor_ f  " +
                    "ON f.floor_id = sp.floor_id " +
                    "INNER JOIN building  " +
                    "ON building.building_id = f.building_id  " +
                    "WHERE building.building_name ='" + building +"' AND f.floor_number = '" + floor +"' AND sp.space_name = '" + space +"' AND equipment.equipment_name = 'Fenetre electro-chromatique'";

            */
            String sql = "select count(*) as nb from equipment inner join position_ ON equipment.equipment_id = position_.equipment_id " +
                    "INNER JOIN space sp " +
                    "ON sp.space_id = position_.space_id " +
                    "INNER JOIn floor_ f  " +
                    "ON f.floor_id = sp.floor_id " +
                    "INNER JOIN building  " +
                    "ON building.building_id = f.building_id  " +
                    "WHERE building.building_name = 'Batiment Condorcet' AND f.floor_number = '3' AND sp.space_name = 'Open Space 1' AND equipment.equipment_name = 'Fenetre electro-chromatique' ";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = statement.executeQuery();
            String sql2 = "select equipment_name From equipment WHERE equipment_id = 8";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            ResultSet rs2 = statement2.executeQuery();

            Map<String, Object> hm = new HashMap<>();
            Map<String, Object> hm2 = new HashMap<>();
            while (rs.next()) {
                hm.put("nbwindow", rs.getInt("nb"));
                System.out.println(hm);

            }
            while (rs2.next()) {
                String equipment_name = rs2.getString("equipment_name");
                hm2.put("equipment_name",equipment_name);

                /*int outside_temperature = rs2.getInt("outside_temperature");
                hm2.put("outside_temperature",outside_temperature);*/
                System.out.println(hm2);
            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm2);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }

        else if (requestName.equals("EtatActuel")) {
            ObjectMapper mapper = new ObjectMapper();

            String sql = "SELECT blind_level_start,blind_percentage_start,blind_level_add, blind_percentage_add, opacity_level_start, opacity_percentage_start,opacity_level_add,opacity_percentage_add FRom CONFIGURATION";

            String sql2 = "Select level_sunlight,outside_temperature from sensor";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            PreparedStatement statement2 = connection.prepareStatement(sql2);
            ResultSet rs2 = statement2.executeQuery();

            Map<String, Object> hm = new HashMap<>();
            while (rs.next()) {
                int blind_level_start = rs.getInt("blind_level_start");
                int blind_percentage_start = rs.getInt("blind_percentage_start");
                int blind_level_add = rs.getInt("blind_level_add");
                int blind_percentage_add= rs.getInt("blind_percentage_add");

                int opacity_level_start= rs.getInt("opacity_level_start");
                int opacity_percentage_start = rs.getInt("opacity_percentage_start");
                int opacity_level_add = rs.getInt("opacity_level_add");
                int opacity_percentage_add = rs.getInt("opacity_percentage_add");



                hm.put("blind_level_start",blind_level_start);
                hm.put("blind_percentage_start", blind_percentage_start);
                hm.put("blind_level_add", blind_level_add);
                hm.put("blind_percentage_add",blind_percentage_add );
                hm.put("opacity_level_start",opacity_level_start);
                hm.put("opacity_percentage_start", opacity_percentage_start );
                hm.put("opacity_level_add",opacity_level_add);
                hm.put("opacity_percentage_add",opacity_percentage_add );



            }
            while (rs2.next()) {
                int level_sunlight = rs2.getInt("level_sunlight");
                hm.put("level_sunlight",level_sunlight);

                int outside_temperature = rs2.getInt("outside_temperature");
                hm.put("outside_temperature",outside_temperature);
            }

           logger.info("Voici les valeurs qui vont etre renvoyer a EtatActuel {} " + hm);

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);


        }

        else if (requestName.equals("ListFenetreMapper")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataloaded = (Map<String, Object>) request.getData();
            String spaceName= (String) dataloaded.get("spaceName");
            int floorNumber = (int) dataloaded.get("floorNumber");
            String nameBuilding = (String) dataloaded.get("nameBuilding");


            String sql2 = "Select equipment.equipment_id from building  " +
                    "inner join floor_ On building.building_id=floor_.building_id " +
                    "inner join space on floor_.floor_id=space.floor_id " +
                    "INNER join position_ on space.space_id=position_.space_id " +
                    "inner join equipment on position_.equipment_id=equipment.equipment_id " +
                    "Where building_name = '"+nameBuilding+"'"+
                    " and floor_number = '"+floorNumber+"'"+
                    " and space_name= '"+spaceName+"'"+
                    " and equipment_name like '%Fenetre%'; ";

            PreparedStatement statement2 = connection.prepareStatement(sql2);
            ResultSet rs2 = statement2.executeQuery();


            Map<Integer, Map<String, Integer>> hm = new HashMap<>();

            int c=0;
            while (rs2.next()) {
                int equipment_id = rs2.getInt("equipment_id");
                Map<String, Integer> map = new HashMap<>();
                map.put("equipment_id",equipment_id);
                hm.put(c,map);
                c++;
            }

           logger.info("Voici les fenetres selectionnees " + hm);
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);


        }
        else if (requestName.equals("store")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataloaded = (Map<String, Object>) request.getData();
            logger.info("Ceux ci sont les donnees des stores {}" + dataloaded);
            int vtemp_debut = (int) dataloaded.get("valeurtemp_debut");
            int ptemp = (int) dataloaded.get("pourcentagetemp_debut");
            int vtemp_augment = (int) dataloaded.get("valeurtemp_avance");
            int ptemp_augmente = (int) dataloaded.get("pourcentagetemp_avance");

            String sql = "UPDATE configuration SET blind_level_start = " + vtemp_debut+ ", blind_percentage_start = "+ ptemp + ", blind_level_add = " + vtemp_augment + ", blind_percentage_add = " + ptemp_augmente + " WHERE id = 1";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("lum")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataloaded1 = (Map<String, Object>) request.getData();
            int valeur_debut = (int) dataloaded1 .get("valeur_debut");
            int pourcentage_debut = (int) dataloaded1 .get("pourcentage_debut");
            int valeur_augment = (int) dataloaded1 .get("valeur_avance");
            int pourcentage_augmente = (int) dataloaded1 .get("pourcentage_avance");

            String sql = "UPDATE configuration SET opacity_level_start = " + valeur_debut+ ", opacity_percentage_start = "+ pourcentage_debut + ", opacity_level_add = " + valeur_augment + ", opacity_percentage_add = " + pourcentage_augmente + " WHERE id = 1";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);


        }


    }
}
