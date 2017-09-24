package com.thinkbiganalytics.metadata.sla;

import com.thinkbiganalytics.classnameregistry.ClassNameChange;
import com.thinkbiganalytics.metadata.sla.alerts.BaseServiceLevelAgreementActionConfiguration;
import com.thinkbiganalytics.metadata.sla.api.ServiceLevelAgreementActionConfig;
import com.thinkbiganalytics.policy.PolicyProperty;
import com.thinkbiganalytics.policy.PolicyPropertyRef;
import com.thinkbiganalytics.policy.PolicyPropertyTypes;

/**
 * Created by mi186020 on 2017/09/23.
 */
@ServiceLevelAgreementActionConfig(
    name = "SendGridEmail", description = "Email user(s) via SendGrid when the SLA is violated", actionClasses = {SendGridEmailServiceLevelAgreementAction.class}
)

@ClassNameChange(classNames = {"com.thinkbiganalytics.metadata.sla.alerts.SendGridEmailServiceLevelAgreementActionConfiguration"})
public class SendGridEmailServiceLevelAgreementActionConfiguration extends BaseServiceLevelAgreementActionConfiguration{

    @PolicyProperty(name = "EmailAddresses", displayName = "Email addresses", hint = "comma separated email addresses", required = true, type = PolicyPropertyTypes.PROPERTY_TYPE.emails)
    private String emailAddresses;

    public SendGridEmailServiceLevelAgreementActionConfiguration() {

    }

    public SendGridEmailServiceLevelAgreementActionConfiguration(@PolicyPropertyRef(name = "EmailAddresses") String emailAddresses) {
        this.emailAddresses = emailAddresses;
    }


    public String getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(String emailAddresses) {
        this.emailAddresses = emailAddresses;
    }
}
