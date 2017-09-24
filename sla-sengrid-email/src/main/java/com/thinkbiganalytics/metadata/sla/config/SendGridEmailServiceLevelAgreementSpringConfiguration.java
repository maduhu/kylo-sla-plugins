package com.thinkbiganalytics.metadata.sla.config;

import com.thinkbiganalytics.metadata.sla.SendGridEmailConfiguration;
import com.thinkbiganalytics.metadata.sla.SendGridEmailServiceLevelAgreementAction;
import com.thinkbiganalytics.metadata.sla.SendGridEmailService;
import com.sendgrid.SendGrid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by mi186020 on 2017/09/23.
 */
@Configuration
@PropertySource(value = "classpath:sla.sendgrid.email.properties", ignoreResourceNotFound = true)
public class SendGridEmailServiceLevelAgreementSpringConfiguration {


    @Bean
    public SendGridEmailServiceLevelAgreementAction emailServiceLevelAgreementAction() {
        return new SendGridEmailServiceLevelAgreementAction();
    }

    @Bean(name = "slaSendGridEmailConfiguration")
    @ConfigurationProperties(prefix = "sla.mail")
    public SendGridEmailConfiguration emailConfiguration() {
        return new SendGridEmailConfiguration();
    }

    @Bean(name = "slaSendGridEmailSender")
    public SendGrid sendGridMailSender(@Qualifier("slaSendGridEmailConfiguration") SendGridEmailConfiguration emailConfiguration) {
        return new SendGrid(emailConfiguration.getSendGridAPIKey());
    }

    @Bean
    public SendGridEmailService slaSendGridEmailService() {
        return new SendGridEmailService();
    }


}