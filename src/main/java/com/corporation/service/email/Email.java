package com.corporation.service.email;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Bleschunov Dmitry
 */
@Builder
@Getter
public class Email {
    private String from;
    private String to;
    private String subject;
    private String htmlBody;
    private String textBody;
}
