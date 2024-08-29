package com.heimdallauth.auth.bifrost.services;

import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import com.heimdallauth.auth.bifrost.constants.MailType;
import com.heimdallauth.auth.bifrost.dao.EmailDataManager;
import com.heimdallauth.auth.bifrost.dao.EmailDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.Instant;

@Service
public class EmailService {
    private final EmailDataManager emailDataManager;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(EmailDataManager emailDataManager, TemplateEngine templateEngine) {
        this.emailDataManager = emailDataManager;
        this.templateEngine = templateEngine;
    }
    public EmailDocument createEmailDeliveryRecord(String emailAddress, String profileId,String bodyContent,  MailType mailType){
        EmailDocument emailDocument = EmailDocument.builder()
                .emailAddress(emailAddress)
                .username(profileId)
                .emailBody(bodyContent)
                .mailType(mailType)
                .requestCreatedOn(Instant.now())
                .deliveryStatus(DeliveryStatus.PENDING)
                .deliveryStatusUpdatedOn(Instant.now())
                .build();
        return emailDataManager.saveEmail(emailDocument);
    }
    public EmailDocument updateEmailDeliveryStatus(String emailDocumentId, DeliveryStatus deliveryStatus){
        return emailDataManager.updateEmailDeliveryStatus(emailDocumentId, deliveryStatus);
    }
    public String generateEmailBody(String username, MailType mailType){
        Context context = new Context();
        context.setVariable("username", username);
        return templateEngine.process(mailType.templateName, context);
    }
}
