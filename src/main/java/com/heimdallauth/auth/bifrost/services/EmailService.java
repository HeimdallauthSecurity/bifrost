package com.heimdallauth.auth.bifrost.services;

import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import com.heimdallauth.auth.bifrost.constants.MailType;
import com.heimdallauth.auth.bifrost.dao.EmailDataManager;
import com.heimdallauth.auth.bifrost.dao.EmailDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
    private final EmailDataManager emailDataManager;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(EmailDataManager emailDataManager, TemplateEngine templateEngine) {
        this.emailDataManager = emailDataManager;
        this.templateEngine = templateEngine;
    }
    public EmailDocument createEmailDeliveryRecord(String emailAddress, String profileId, MailType mailType){
        EmailDocument emailDocument = EmailDocument.builder()
                .emailAddress(emailAddress)
                .username(profileId)
                .mailType(mailType)
                .build();
        return emailDataManager.saveEmail(emailDocument);
    }
    public EmailDocument updateEmailDeliveryStatus(String emailDocumentId, DeliveryStatus deliveryStatus){
        return emailDataManager.updateEmailDeliveryStatus(emailDocumentId, deliveryStatus);
    }
    public String generateEmailBody(String username){
        Context context = new Context();
        context.setVariable("username", username);
        return templateEngine.process("email-template", context);
    }
}
