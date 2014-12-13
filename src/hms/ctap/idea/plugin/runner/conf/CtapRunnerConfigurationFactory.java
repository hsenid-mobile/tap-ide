package hms.ctap.idea.plugin.runner.conf;

import hms.ctap.idea.plugin.runner.model.CtapRunnerConfiguration;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Jetty Runner Configuration Factory
 * @see com.intellij.execution.configurations.ConfigurationFactory
 * @author Gui Keller
 */
public class CtapRunnerConfigurationFactory extends ConfigurationFactory {

    public CtapRunnerConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
    }

    @Override
    public RunConfiguration createTemplateConfiguration(Project project) {
        return new CtapRunnerConfiguration(project, this, "Jetty Runner");
    }

}
