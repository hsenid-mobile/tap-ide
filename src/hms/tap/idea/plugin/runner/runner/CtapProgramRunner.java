package hms.tap.idea.plugin.runner.runner;

import hms.tap.idea.plugin.runner.model.CtapRunnerConfiguration;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.DefaultProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.project.Project;
import hms.tap.idea.plugin.runner.model.CtapRunnerConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * Jetty Program Runner
 * @see com.intellij.execution.runners.DefaultProgramRunner
 * @author Gui Keller
 */
public class CtapProgramRunner extends DefaultProgramRunner {

    private static final String RUN = "Run";

    public CtapProgramRunner(){
        super();
    }

    @NotNull
    public String getRunnerId() {
        return "JettyRunner-By-GuiKeller";
    }

    public boolean canRun(@NotNull String value, @NotNull RunProfile runProfile) {
        if(!(runProfile instanceof CtapRunnerConfiguration)){
            return false;
        }
        // Values passed are: Run or Debug
        if(!RUN.equals(value)) {
            return false;
        }
        return true;
    }

    @Override
    protected RunContentDescriptor doExecute(@NotNull Project project,
                                             @NotNull RunProfileState state,
                                             RunContentDescriptor contentToReuse,
                                             @NotNull ExecutionEnvironment env) throws ExecutionException {
        return super.doExecute(project, state, contentToReuse, env);
    }
}
