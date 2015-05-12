package hms.tap.idea.plugin.action.snippet;

import hms.tap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.tap.idea.plugin.util.IconUtil;
import hms.tap.idea.plugin.util.MessageUtil;
import hms.tap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.tap.idea.plugin.util.MessageUtil;

/**
 * Created by isuru on 4/19/15.
 */
public class SubscriptionFieldGeneratorAction extends CodeSnippetGeneratorAction {

    public SubscriptionFieldGeneratorAction() {
        super(MessageUtil.message("tap.snippet.subscription.client.generate.text"), MessageUtil.message("tap.snippet.subscription.client.generate.description"), IconUtil.ServiceClassIcon.SUBSCRIPTION_NOTIFY);
    }

    @Override
    public CodeSnippet codeSnippet() {
        return CodeSnippet.SUBSCRIPTION;
    }
}
