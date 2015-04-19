package hms.ctap.idea.plugin.action.snippet;

import hms.ctap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.ctap.idea.plugin.util.IconUtil;
import hms.ctap.idea.plugin.util.MessageUtil;

/**
 * Created by isuru on 4/19/15.
 */
public class LbsFieldGeneratorAction extends CodeSnippetGeneratorAction {

    public LbsFieldGeneratorAction() {
        super(MessageUtil.message("ctap.snippet.lbs.client.generate.text"), MessageUtil.message("ctap.snippet.lbs.client.generate.description"), IconUtil.ServiceClassIcon.LBS);
    }

    @Override
    public CodeSnippet codeSnippet() {
        return CodeSnippet.LBS;
    }
}
