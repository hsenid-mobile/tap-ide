package hms.tap.idea.plugin.util;

import com.intellij.AbstractBundle;
import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.ResourceBundle;

/**
 * Created by isuru on 4/16/15.
 */

public class MessageUtil extends AbstractBundle {

    public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, @NotNull Object... params) {
        return ourInstance.getMessage(key, params);
    }

    @NonNls
    private static final String BUNDLE = "hms.tap.idea.plugin.messages.messages";
    private static final MessageUtil ourInstance = new MessageUtil();

    private MessageUtil() {
        super(BUNDLE);
    }
}
