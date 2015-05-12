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
public class CtapRunnerConfigurationType implements ConfigurationType {

    public CtapRunnerConfigurationType(){
        super();
    }

    public String getDisplayName() {
        return "Ctap Runner";
    }

    public String getConfigurationTypeDescription() {
        return "IntelliJ IDEA Ctap Runner";
    }

    public Icon getIcon() {
        return IconUtil.getInstance().getIcon();
    }

    @NotNull
    public String getId() {
        return "CtapRunner-By-hms";
    }

    public ConfigurationFactory[] getConfigurationFactories() {
        CtapRunnerConfigurationFactory factory = new CtapRunnerConfigurationFactory(this);
        return new ConfigurationFactory[]{factory};
    }
}
