package com.heimdallauth.auth.bifrost.mailer;

import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SMTPBifrostMailerImpl implements BifrostMail{
    @Override
    public DeliveryStatus sendMail(String destinationEmail, String subject, String body) {
        return null;
    }

    @Override
    public Map<String, DeliveryStatus> executeBulkMail(String[] destinationEmails, String subject, String body) {
        return Map.of();
    }
}
