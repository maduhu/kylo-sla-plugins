package com.thinkbiganalytics.metadata.sla;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

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
        System.out.println("send it to grid through "+emailConfiguration.getSendGridAPIKey());
        System.out.println("send it from "+emailConfiguration.getFrom()+" to "+to);
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}
