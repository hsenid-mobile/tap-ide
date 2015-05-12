package hms.tap.idea.plugin.runner.conf;

import hms.tap.idea.plugin.runner.model.TapRunnerConfiguration;
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
public class TapRunnerConfigurationFactory extends ConfigurationFactory {

    public TapRunnerConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
    }

    @Override
    public RunConfiguration createTemplateConfiguration(Project project) {
        return new TapRunnerConfiguration(project, this, "Jetty Runner");
    }

}
