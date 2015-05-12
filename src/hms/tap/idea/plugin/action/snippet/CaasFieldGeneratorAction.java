package hms.tap.idea.plugin.action.snippet;

import hms.tap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.tap.idea.plugin.util.IconUtil;
import hms.tap.idea.plugin.util.MessageUtil;
import hms.tap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.tap.idea.plugin.util.IconUtil;
import hms.tap.idea.plugin.util.MessageUtil;

/**
 * Created by isuru on 4/19/15.
 */
public class CaasFieldGeneratorAction extends CodeSnippetGeneratorAction {

    public CaasFieldGeneratorAction() {
        super(MessageUtil.message("tap.snippet.caas.client.generate.text"), MessageUtil.message("tap.snippet.caas.client.generate.description"), IconUtil.ServiceClassIcon.CAS_SERVICE);
    }

    @Override
    public CodeSnippet codeSnippet() {
        return CodeSnippet.CAS;
    }
}
