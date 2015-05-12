package hms.tap.idea.plugin.action.simulator;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.PathManager;
import hms.tap.idea.plugin.util.ConfigUtil;
import hms.tap.idea.plugin.util.IconUtil;
import hms.tap.idea.plugin.util.MessageUtil;
import hms.tap.idea.plugin.util.ConfigUtil;
import hms.tap.idea.plugin.util.MessageUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.*;

/**
 * Created by isuru on 4/19/15.
 */
public class TapSimulatorStartAction extends AnAction {


    private static final File pluginLibDirectory = new File(PathManager.getJarPathForClass(TapSimulatorStartAction.class)).getParentFile();
    private static final String jettyPluginPath = pluginLibDirectory.getAbsolutePath() + File.separator + "mortbay-jetty-runner-8.1.9.jar";
    private static final String simulatorPath = pluginLibDirectory.getAbsolutePath() + File.separator + "mchoice-sdp-sdk.war";
    private static final String simulatorPort = ConfigUtil.config("simulator.port");
    private static final String command = "java -jar" + " " + jettyPluginPath + " " + "--port" + " " + simulatorPort +  " " + simulatorPath;


    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public TapSimulatorStartAction() {
        super(MessageUtil.message("tap.simulator.start.text"), MessageUtil.message("tap.simulator.start.description"), IconUtil.ServiceClassIcon.SIMULATOR);
    }

    public Future<Boolean> checkPort(final int timeoutInSeconds) {
        Future<Boolean> result = executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                return checkConnectivity(timeoutInSeconds);
            }
        });

        return result;
    }

    private Boolean checkConnectivity(int timeoutInSeconds) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 10001), (int) TimeUnit.SECONDS.toMillis(timeoutInSeconds));
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void actionPerformed(final AnActionEvent anActionEvent) {
        try {
            boolean requireSimulatorStartup = !checkPort(5).get();
            if(requireSimulatorStartup) {
                enable(false, anActionEvent.getPresentation());
                Runtime.getRuntime().exec(command);
            }

            if(requireSimulatorStartup) {
                final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);
                scheduler.scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public void run() {
                        Boolean connectivity = checkConnectivity(1);
                        if(connectivity) {
                            enable(true, anActionEvent.getPresentation());
                            tryStartBrowser();
                            scheduler.shutdown();
                        }
                    }
                }, 2, 2, TimeUnit.SECONDS);
            } else {
                tryStartBrowser();
            }
        } catch (Exception e) {
        }
    }

    private void enable(boolean enable, Presentation presentation) {

        presentation.setEnabled(enable);

        if(enable) {
            presentation.setIcon(IconUtil.ServiceClassIcon.SIMULATOR);
        } else {
            presentation.setIcon(IconUtil.ServiceClassIcon.SIMULATOR_LOADING);
        }
    }

    private void tryStartBrowser() {
        if(Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(ConfigUtil.config("simulator.url")));
            } catch (IOException e) {
            } catch (URISyntaxException e) {
            }
        }
    }
}
