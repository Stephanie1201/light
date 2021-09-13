package edu.episen.si.ing1.pds.backend.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.episen.si.ing1.pds.backend.server.socket.ResquestSocket;


import java.io.PrintWriter;
import java.sql.Connection;

public class RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class.getName());

    public static void sendResponse(ResquestSocket request, PrintWriter writer, Connection connection) throws Exception {
        String requestName = request.getRequest();

    }
}
