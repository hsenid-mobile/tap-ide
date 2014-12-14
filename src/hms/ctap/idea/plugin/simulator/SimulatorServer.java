package hms.ctap.idea.plugin.simulator;

import java.io.IOException;

import java.util.logging.Logger;




import com.sun.jersey.simple.container.SimpleServerFactory;
import com.sun.net.httpserver.HttpServer;


public class SimulatorServer {

    private static final Logger LOGGER = Logger.getLogger(SimulatorServer.class.getName());

    private HttpServer httpServer;

    public void start() {
        java.io.Closeable server = null;
        try {
            server = SimpleServerFactory.create("http://localhost:7000");
            System.in.read();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                server.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


}
