package hms.ctap.simulator;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import hms.ctap.simulator.ui.SmsUiFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NblServerSimulator {


    private static final Queue<String> messageQueue = new ConcurrentLinkedQueue<String>();

    private static SmsUiFactory smsUiFactory;


    public NblServerSimulator(SmsUiFactory smsUiFactory) throws IOException {
        NblServerSimulator.smsUiFactory = smsUiFactory;

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(7000), 0);
            server.createContext("/sms/send", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            String request = CharStreams.toString(new InputStreamReader(t.getRequestBody()));

            Gson gson = new Gson();
            SmsMtRequestMessage smsMtRequestMessage = gson.fromJson(request, SmsMtRequestMessage.class);
            try {
                smsUiFactory.createMsgReceivedUI(smsMtRequestMessage.getMessage());
            } catch (Exception e) {
                System.out.println("Error while setting the ui");
                e.printStackTrace();
            }

            String response = "{\n" +
                    "  \"statusCode\": \"S1000\",\n" +
                    "  \"requestId\": \"101307311109540017\",\n" +
                    "  \"statusDetail\": \"Request was successfully processed\",\n" +
                    "  \"destinationResponses\": [\n" +
                    "    {\n" +
                    "      \"statusCode\": \"S1000\",\n" +
                    "      \"timeStamp\": \"20130731110954\",\n" +
                    "      \"address\": \"tel:947712345678\",\n" +
                    "      \"statusDetail\": \"Request was successfully processed\",\n" +
                    "      \"messageId\": \"101307311109540017\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"version\": \"1.0\"\n" +
                    "}";
            t.setAttribute("Content-Type", "application/json");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public String removeMessage() {
        return messageQueue.poll();
    }
}