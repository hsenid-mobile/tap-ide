#if (${IJ_BASE_PACKAGE} && ${IJ_BASE_PACKAGE} != "")package ${IJ_BASE_PACKAGE};#end

/**
 * Created by ${USER} on ${DATE}.
 */

import com.google.common.base.Optional;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties applicationProperties = new Properties();
    private static final Properties messageProperties = new Properties();
    static {
        try {
            applicationProperties.load(new FileReader(ConfigUtil.class.getResource("/resources/application.properties").getFile()));
            messageProperties.load(new FileReader(ConfigUtil.class.getResource("/resources/messages.properties").getFile()));	
	} catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getApplicationConfig(String key) {
        return applicationProperties.getProperty(key);
    }

    public static Optional<String> getDisplayMessage(String key) {
        return Optional.fromNullable(messageProperties.getProperty(key));
    }
}

