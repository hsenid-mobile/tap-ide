package hms.ctap.idea.plugin.util;

import javax.swing.*;
import java.net.URL;

/**
 * IconUtil - Loads the jetty icon
 * @author Gui Keller
 */
public class IconUtil {

    private static final IconUtil INSTANCE = new IconUtil();

    private IconUtil(){
        super();
    }

    public static IconUtil getInstance(){
        return INSTANCE;
    }

    public Icon getIcon(){
        return new ImageIcon(getClass().getResource("/resources/ctap-runner/images/"+"runner-icon.png"));
    }

}
