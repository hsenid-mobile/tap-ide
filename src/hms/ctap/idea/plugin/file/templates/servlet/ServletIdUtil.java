package hms.ctap.idea.plugin.file.templates.servlet;

import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 4/19/15.
 */
public class ServletIdUtil {

    public static List<String> getServletNames(XmlFile webXmlFile, String level1, String level2) {
        List<String> servletNames = new ArrayList<String>();
        XmlTag[] subTags = webXmlFile.getRootTag().getSubTags();
        for (XmlTag subTag : subTags) {
            if(subTag.getName().equals(level1)){
                for (XmlTag xmlTag : subTag.getSubTags()) {
                    if(xmlTag.getName().equals(level2)) {
                        servletNames.add(xmlTag.getValue().getText());
                    }
                }

            }
        }
        return servletNames;
    }

    public static String getAvailableServletName(XmlFile webXmlFile, String idPrefix) {
        return newIdentifier(getServletNames(webXmlFile, "servlet", "servlet-name"), idPrefix);
    }


    public static String getAvailableServletPath(XmlFile webXmlFile, String idPrefix) {
        return newIdentifier(getServletNames(webXmlFile, "servlet-mapping", "url-pattern"), idPrefix);
    }

    public static String newIdentifier(List<String> currentIds, String idPrefix) {

        ArrayList<String> startsWithMethodName = new ArrayList<String>();

        for (String id : currentIds) {
            if(idPrefix.startsWith(id)) {
                startsWithMethodName.add(id);
            }
        }

        int i = 1;
        String newMethodName = idPrefix;
        while (true) {
            if(!startsWithMethodName.contains(newMethodName)) {
                break;
            }
            newMethodName = idPrefix + i++;
        }
        return newMethodName;


    }
}
