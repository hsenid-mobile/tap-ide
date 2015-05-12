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
public class LbsFieldGeneratorAction extends CodeSnippetGeneratorAction {

    public LbsFieldGeneratorAction() {
        super(MessageUtil.message("tap.snippet.lbs.client.generate.text"), MessageUtil.message("tap.snippet.lbs.client.generate.description"), IconUtil.ServiceClassIcon.LBS_SERVICE);
    }

    @Override
    public CodeSnippet codeSnippet() {
        return CodeSnippet.LBS;
    }
}
