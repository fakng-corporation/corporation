package com.corporation.service.email;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Bleschunov Dmitry
 */
@Service
public class EmailService {

    @Value("${email.from}")
    private String from;

    public void sendEmail(Email email) {
        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.US_EAST_1).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(email.getTo()))
                    .withSource(from);

            if (email.getTextBody() != null) {
                request.withMessage(new Message()
                                .withBody(new Body()
                                        .withText(new Content()
                                                .withCharset("UTF-8").withData(email.getTextBody())))
                        .withSubject(new Content()
                                .withCharset("UTF-8").withData(email.getSubject())));
            }

            if (email.getHtmlBody() != null) {
                request.withMessage(new Message()
                        .withBody(new Body()
                                .withHtml(new Content()
                                        .withCharset("UTF-8").withData(email.getHtmlBody())))
                        .withSubject(new Content()
                                .withCharset("UTF-8").withData(email.getSubject())));
            }

            client.sendEmail(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
