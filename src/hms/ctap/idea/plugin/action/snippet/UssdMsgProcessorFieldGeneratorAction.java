package hms.ctap.idea.plugin.action.snippet;

import hms.ctap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.ctap.idea.plugin.util.IconUtil;
import hms.ctap.idea.plugin.util.MessageUtil;

/**
 * Created by isuru on 4/19/15.
 */
public class UssdMsgProcessorFieldGeneratorAction extends CodeSnippetGeneratorAction {

    public UssdMsgProcessorFieldGeneratorAction() {
        super(MessageUtil.message("ctap.snippet.ussd.msg.processor.generate.text"), MessageUtil.message("ctap.snippet.ussd.msg.processor.generate.description"), IconUtil.ServiceClassIcon.USSD_MSG_PROCESSOR);
    }

    @Override
    public CodeSnippet codeSnippet() {
        return CodeSnippet.USSD_SESSION_PROCESSOR;
    }
}
