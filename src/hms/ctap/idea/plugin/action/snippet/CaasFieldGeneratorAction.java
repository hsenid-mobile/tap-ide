package hms.ctap.idea.plugin.action.snippet;

import hms.ctap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.ctap.idea.plugin.util.IconUtil;
import hms.ctap.idea.plugin.util.MessageUtil;

/**
 * Created by isuru on 4/19/15.
 */
public class CaasFieldGeneratorAction extends CodeSnippetGeneratorAction {

    public CaasFieldGeneratorAction() {
        super(MessageUtil.message("ctap.snippet.caas.client.generate.text"), MessageUtil.message("ctap.snippet.caas.client.generate.description"), IconUtil.ServiceClassIcon.CAS_SERVICE);
    }

    @Override
    public CodeSnippet codeSnippet() {
        return CodeSnippet.CAS;
    }
}
