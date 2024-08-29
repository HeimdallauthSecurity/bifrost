package com.heimdallauth.auth.bifrost.dao;

import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EmailMongoDataManager implements EmailDataManager{
    private final MongoTemplate mongoTemplate;
    private final static String EMAIL_COLLECTION = "email-collections";

    @Autowired
    public EmailMongoDataManager(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public EmailDocument saveEmail(EmailDocument emailDocument) {
        return this.mongoTemplate.save(emailDocument, EMAIL_COLLECTION);
    }

    @Override
    public EmailDocument getEmail(String id) {
        return null;
    }

    @Override
    public List<EmailDocument> getEmailByEmailAddress(String emailAddress) {
        return List.of();
    }

    @Override
    public List<EmailDocument> getEmailByUsername(String username) {
        return List.of();
    }

    @Override
    public EmailDocument updateEmailDeliveryStatus(String emailDocumentId, DeliveryStatus deliveryStatus) {
        return null;
    }
}
