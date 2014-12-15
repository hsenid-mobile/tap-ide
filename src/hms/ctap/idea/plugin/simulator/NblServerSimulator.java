package hms.ctap.idea.plugin.simulator;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import hms.ctap.idea.plugin.ui.SmsUiFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NblServerSimulator {


    private static final Queue<String> messageQueue = new ConcurrentLinkedQueue<String>();

    private SmsUiFactory smsUiFactory;

    public NblServerSimulator(SmsUiFactory smsUiFactory) throws IOException {
        this.smsUiFactory = smsUiFactory;
        HttpServer server = HttpServer.create(new InetSocketAddress(7000), 0);
        server.createContext("/sms/send", new MyHandler(smsUiFactory));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        private SmsUiFactory smsUiFactory;

        public MyHandler(SmsUiFactory smsUiFactory) {
            this.smsUiFactory = smsUiFactory;
        }

        public void handle(HttpExchange t) throws IOException {
            String request = CharStreams.toString(new InputStreamReader(t.getRequestBody()));

            Gson gson = new Gson();
            SmsMtRequestMessage smsMtRequestMessage = gson.fromJson(request, SmsMtRequestMessage.class);
            smsUiFactory.createMsgReceivedUI(smsMtRequestMessage.getMessage());

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