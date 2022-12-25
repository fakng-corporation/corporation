package com.corporation.service.email;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.MessageRejectedException;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.corporation.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Bleschunov Dmitry
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${email.from}")
    private String from;

    private final AmazonSimpleEmailService amazonSes;

    public void sendEmail(Email email) {
        try {
            SendEmailRequest request = createRequest(email);

            if (email.getTextBody() != null) {
                addTextBody(request, email);
            }

            if (email.getHtmlBody() != null) {
                addHtmlBody(request, email);
            }

            amazonSes.sendEmail(request);
        } catch (MessageRejectedException e) {
            String message = "SES crashed trying to send email";
            log.error(message, e);
            throw new BusinessException("SES crashed trying to send email");
        }
    }

    private SendEmailRequest createRequest(Email email) {
        return new SendEmailRequest()
                .withDestination(
                        new Destination().withToAddresses(email.getTo()))
                .withSource(from);
    }

    private void addTextBody(SendEmailRequest request, Email email) {
        request.withMessage(new Message()
                .withBody(new Body()
                        .withText(new Content()
                                .withCharset("UTF-8").withData(email.getTextBody())))
                .withSubject(new Content()
                        .withCharset("UTF-8").withData(email.getSubject())));
    }

    private void addHtmlBody(SendEmailRequest request, Email email) {
        request.withMessage(new Message()
                .withBody(new Body()
                        .withHtml(new Content()
                                .withCharset("UTF-8").withData(email.getHtmlBody())))
                .withSubject(new Content()
                        .withCharset("UTF-8").withData(email.getSubject())));
    }
}
