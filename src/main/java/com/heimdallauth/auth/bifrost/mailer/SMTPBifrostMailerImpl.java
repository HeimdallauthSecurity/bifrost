package com.heimdallauth.auth.bifrost.mailer;

import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SMTPBifrostMailerImpl implements BifrostMail{
    private final JavaMailSender javaMailSender;

    public SMTPBifrostMailerImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public DeliveryStatus sendMail(String destinationEmail, String subject, String body) throws MessagingException {
        DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;
        InternetAddress destinationAddress = new InternetAddress("mayank.soni@mayanksini.tech");
        InternetAddress fromAddress = new InternetAddress("noreply@heimdallauth.com");
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        mailMessage.addRecipient(Message.RecipientType.TO, destinationAddress);
        mailMessage.addFrom(new InternetAddress[]{fromAddress});
        mailMessage.setSubject(subject);
        mailMessage.setContent(body, "text/html");
        javaMailSender.send(mailMessage);
        deliveryStatus = DeliveryStatus.SENT;
        return deliveryStatus;
    }

    @Override
    public Map<String, DeliveryStatus> executeBulkMail(String[] destinationEmails, String subject, String body) {
        return Map.of();
    }
}
