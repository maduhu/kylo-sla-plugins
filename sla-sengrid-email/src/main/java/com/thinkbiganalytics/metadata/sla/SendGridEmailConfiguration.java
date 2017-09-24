package com.thinkbiganalytics.metadata.sla;

import com.sendgrid.*;
import java.io.IOException;

/**
 * Created by mi186020 on 2017/09/23.
 */
public class SendGridEmailConfiguration {

    private String sendGridAPIKey;
    private String from;

    public String getSendGridAPIKey() {
        return sendGridAPIKey;
    }

    public void setSendGridAPIKey(String sendGridAPIKey) {
        this.sendGridAPIKey = sendGridAPIKey;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}
