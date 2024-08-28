package com.heimdallauth.auth.bifrost.services;

import com.heimdallauth.auth.bifrost.constants.MailType;
import com.heimdallauth.auth.bifrost.dao.EmailDataManager;
import com.heimdallauth.auth.bifrost.dao.EmailDocument;
import com.heimdallauth.auth.bifrost.mailer.BifrostMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailDataManager emailDataManager;

    @Autowired
    public EmailService(EmailDataManager emailDataManager) {
        this.emailDataManager = emailDataManager;
    }
    public EmailDocument createEmailDeliveryRecord(String emailAddress, String profileId, MailType mailType){
        EmailDocument emailDocument = EmailDocument.builder()
                .emailAddress(emailAddress)
                .username(profileId)
                .mailType(mailType)
                .build();
        return emailDataManager.saveEmail(emailDocument);
    }
}
