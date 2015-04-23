package hms.ctap.idea.plugin.action.snippet;

import hms.ctap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.ctap.idea.plugin.util.IconUtil;
import hms.ctap.idea.plugin.util.MessageUtil;

/**
 * Created by isuru on 4/19/15.
 */
public class SubscriptionFieldGeneratorAction extends CodeSnippetGeneratorAction {

    public SubscriptionFieldGeneratorAction() {
        super(MessageUtil.message("ctap.snippet.subscription.client.generate.text"), MessageUtil.message("ctap.snippet.subscription.client.generate.description"), IconUtil.ServiceClassIcon.SUBSCRIPTION_NOTIFY);
    }

    @Override
    public CodeSnippet codeSnippet() {
        return CodeSnippet.SUBSCRIPTION;
    }
}
