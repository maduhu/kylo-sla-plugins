package com.thinkbiganalytics.metadata.sla;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by mi186020 on 2017/09/23.
 */
public class SendGridEmailService {

    @Inject
    @Qualifier("slaSendGridEmailSender")
    private SendGrid mailSender;

    @Inject
    @Qualifier("slaSendGridEmailConfiguration")
    private SendGridEmailConfiguration emailConfiguration;

    /**
     * Send an email
     *
     * @param to      the user(s) to send the email to
     * @param subject the subject of the email
     * @param body    the email body
     */
    public void sendMail(String to, String subject, String body) throws IOException {
        Email fromEmail = new Email(emailConfiguration.getFrom());
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(fromEmail, subject, toEmail, content);

        SendGrid sg = new SendGrid(emailConfiguration.getSendGridAPIKey());
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
//        Response response = sg.api(request);
        sg.api(request);
    }

    /**
     * @return {@code true} if the configuration is setup, {@code false} if not configured
     */
    public boolean isConfigured() {
        return emailConfiguration.isConfigured();
    }

}
