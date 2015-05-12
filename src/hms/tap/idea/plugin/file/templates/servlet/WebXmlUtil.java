package hms.tap.idea.plugin.file.templates.servlet;

import com.intellij.ide.fileTemplates.impl.UrlUtil;

import java.io.IOException;

import static com.intellij.openapi.util.text.StringUtil.convertLineSeparators;

/**
 * Created by isuru on 4/19/15.
 */
public class WebXmlUtil {
    public static final String servletEntry() {
        try {
            return convertLineSeparators(UrlUtil.loadText(WebXmlUtil.class.getResource("/hms/tap/idea/plugin/file/templates/xml/servlet_entries.xml")));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
