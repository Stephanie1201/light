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

    }
}
