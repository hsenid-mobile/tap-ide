package hms.tap.idea.plugin.runner.conf;

import hms.tap.idea.plugin.util.IconUtil;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Jetty Runner Configuration Type
 * @see com.intellij.execution.configurations.ConfigurationType
 * @author Gui Keller
 */
public class TapRunnerConfigurationType implements ConfigurationType {

    public TapRunnerConfigurationType(){
        super();
    }

    public String getDisplayName() {
        return "TAP Runner";
    }

    public String getConfigurationTypeDescription() {
        return "IntelliJ IDEA TAP Runner";
    }

    public Icon getIcon() {
        return IconUtil.getInstance().getIcon();
    }

    @NotNull
    public String getId() {
        return "TAPRunner-By-hms";
    }

    public ConfigurationFactory[] getConfigurationFactories() {
        TapRunnerConfigurationFactory factory = new TapRunnerConfigurationFactory(this);
        return new ConfigurationFactory[]{factory};
    }
}
