package com.thinkbiganalytics.metadata.sla;

import com.thinkbiganalytics.alerts.api.Alert;
import com.thinkbiganalytics.classnameregistry.ClassNameChange;
import com.thinkbiganalytics.metadata.sla.alerts.ServiceLevelAssessmentAlertUtil;
import com.thinkbiganalytics.metadata.sla.api.ServiceLevelAgreementAction;
import com.thinkbiganalytics.metadata.sla.api.ServiceLevelAgreementActionValidation;
import com.thinkbiganalytics.metadata.sla.api.ServiceLevelAssessment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by mi186020 on 2017/09/23.
 */
@ClassNameChange(classNames = {"com.thinkbiganalytics.metadata.sla.alerts.SendGridEmailServiceLevelAgreementAction"})
public class SendGridEmailServiceLevelAgreementAction implements ServiceLevelAgreementAction<SendGridEmailServiceLevelAgreementActionConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(SendGridEmailServiceLevelAgreementAction.class);
    @Inject
    private SendGridEmailService emailService;

    public boolean respond(SendGridEmailServiceLevelAgreementActionConfiguration actionConfiguration, ServiceLevelAssessment assessment, Alert a) {
        log.info("Responding to SLA violation.");
        String description = ServiceLevelAssessmentAlertUtil.getDescription(assessment);
        String slaName = assessment.getAgreement().getName();
        String emails = actionConfiguration.getEmailAddresses();
        try {
            sendToAddresses(emails, slaName, description);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Validate to ensure there is a configuration setup for the email
     *
     * @return a validation object containing information if the configuration is valid
     */
    public ServiceLevelAgreementActionValidation validateConfiguration() {
        return ServiceLevelAgreementActionValidation.VALID;
    }

    void sendToAddresses(String emails, String slaName, String description) throws IOException {
        String[] addresses = emails.split(",");
        for(String address: addresses) sendToAddress(address, slaName,description);
    }

    private void sendToAddress(String address, String slaName, String description) throws IOException {
        log.info("Responding to SLA violation.  About to send an email for SLA {} ", slaName);
        emailService.sendMail(address, "SLA Violated: " + slaName,description);
    }

    public void setEmailService(SendGridEmailService emailService) {
        this.emailService = emailService;
    }


}
