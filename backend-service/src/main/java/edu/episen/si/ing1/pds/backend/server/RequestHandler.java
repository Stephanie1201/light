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

        else if (requestName.equals("select")) {  //name of request
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


        }
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


    }
}
