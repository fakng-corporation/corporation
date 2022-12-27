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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Bleschunov Dmitry
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String ENCODING = "UTF-8";

    @Value("${aws.ses.from}")
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
            throw new BusinessException(message);
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
                                .withCharset(ENCODING).withData(email.getTextBody())))
                .withSubject(new Content()
                        .withCharset(ENCODING).withData(email.getSubject())));
    }

    private void addHtmlBody(SendEmailRequest request, Email email) {
        request.withMessage(new Message()
                .withBody(new Body()
                        .withHtml(new Content()
                                .withCharset(ENCODING).withData(email.getHtmlBody())))
                .withSubject(new Content()
                        .withCharset(ENCODING).withData(email.getSubject())));
    }
}
