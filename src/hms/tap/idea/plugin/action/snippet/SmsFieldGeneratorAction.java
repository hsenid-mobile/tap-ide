package hms.tap.idea.plugin.action.snippet;

import hms.tap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.tap.idea.plugin.util.IconUtil;
import hms.tap.idea.plugin.util.MessageUtil;
import hms.tap.idea.plugin.file.templates.snippet.CodeSnippet;

/**
 * Created by isuru on 4/19/15.
 */
public class SmsFieldGeneratorAction extends CodeSnippetGeneratorAction {

    public SmsFieldGeneratorAction() {
        super(MessageUtil.message("tap.snippet.sms.client.generate.text"), MessageUtil.message("tap.snippet.sms.client.generate.description"), IconUtil.ServiceClassIcon.SMS_MO);
    }

    @Override
    public CodeSnippet codeSnippet() {
        return CodeSnippet.SMS;
    }
}
