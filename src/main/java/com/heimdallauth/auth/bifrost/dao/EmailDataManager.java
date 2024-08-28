package com.heimdallauth.auth.bifrost.dao;

import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;

import java.util.List;

public interface EmailDataManager {
    EmailDocument saveEmail(EmailDocument emailDocument);

    EmailDocument getEmail(String id);

    List<EmailDocument> getEmailByEmailAddress(String emailAddress);

    List<EmailDocument> getEmailByUsername(String username);

    EmailDocument updateEmailDeliveryStatus(String emailDocumentId, DeliveryStatus deliveryStatus);
}
