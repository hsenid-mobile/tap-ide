package hms.tap.idea.plugin.util;

import com.intellij.AbstractBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

/**
 * Created by isuru on 4/16/15.
 */

public class ConfigUtil extends AbstractBundle {

    public static String config(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, @NotNull Object... params) {
        return ourInstance.getMessage(key, params);
    }

    @NonNls
    private static final String BUNDLE = "hms.tap.idea.plugin.configuration.application";
    private static final ConfigUtil ourInstance = new ConfigUtil();

    private ConfigUtil() {
        super(BUNDLE);
    }
}
