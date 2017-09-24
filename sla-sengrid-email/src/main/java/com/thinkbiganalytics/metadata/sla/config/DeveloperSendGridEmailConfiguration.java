package com.thinkbiganalytics.metadata.sla.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by mi186020 on 2017/09/23.
 */
@Configuration
@Profile("developer.email")
@PropertySource("classpath:/conf/sla.sendgrid.email.properties")
public class DeveloperSendGridEmailConfiguration {

}
