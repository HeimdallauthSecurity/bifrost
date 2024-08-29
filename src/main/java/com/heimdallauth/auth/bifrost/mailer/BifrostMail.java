package com.heimdallauth.auth.bifrost.mailer;

import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface BifrostMail {
    DeliveryStatus sendMail(String destinationEmail,String subject,String body) throws MessagingException;
    Map<String, DeliveryStatus> executeBulkMail(String[] destinationEmails, String subject, String body);
}
