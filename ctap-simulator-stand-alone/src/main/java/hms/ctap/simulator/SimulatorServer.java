package hms.ctap.simulator;

import com.sun.jersey.simple.container.SimpleServerFactory;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class SimulatorServer {

    private static final Logger LOGGER = Logger.getLogger(SimulatorServer.class.getName());

    private static final List<NotifyUI> NOTIFY_UIS = new ArrayList<NotifyUI>();

    private HttpServer httpServer;

    public void start() {
     /*  Runnable runnable = new Runnable() {
         @Override
          public void run() {*/
                java.io.Closeable server = null;
                try {

                    server = SimpleServerFactory.create("http://127.0.0.1:7000");
                    System.in.read();
                } catch (Exception e) {
                    LOGGER.info("Error occurred " +e);
                }finally{
                    try {
                        server.close();
                    } catch (IOException e) {
                        LOGGER.info("Error occurred " + e);
                    }
                }

            }
      /*  };

        Thread thread = new Thread(runnable);
        thread.start();
    }*/

    public static List<NotifyUI> getNotifyUis(){
        return  NOTIFY_UIS;
    }



}
