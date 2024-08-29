package com.heimdallauth.auth.bifrost.services;

import com.heimdallauth.auth.bifrost.clients.UserProfileClient;
import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import com.heimdallauth.auth.bifrost.constants.MailType;
import com.heimdallauth.auth.bifrost.dao.EmailDocument;
import com.heimdallauth.auth.bifrost.dto.UserInformationDTO;
import com.heimdallauth.auth.bifrost.mailer.BifrostMail;
import com.heimdallauth.auth.bifrost.records.EmailRequestDTO;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserProfileService {
    private final UserProfileClient userProfileClient;
    private final BifrostMail bifrostMail;
    private final EmailService emailService;

    @Autowired
    public UserProfileService(UserProfileClient userProfileClient, BifrostMail bifrostMail, EmailService emailService) {
        this.userProfileClient = userProfileClient;
        this.bifrostMail = bifrostMail;
        this.emailService = emailService;
    }

    public DeliveryStatus processEmailSendRequest(EmailRequestDTO emailRequestDTO) throws MessagingException {
        switch (emailRequestDTO.mailType()) {
            case PROFILE_VERIFICATION -> {
                return sendUserProfileVerificationEmail(emailRequestDTO.profileId());
            }
            default -> {
                log.error("Invalid mail type: {}", emailRequestDTO.mailType());
                return DeliveryStatus.FAILED;
            }
        }
    }

    private DeliveryStatus sendUserProfileVerificationEmail(String username) throws MessagingException {
        UserInformationDTO userInformationDTO = userProfileClient.getUserInformation(username);
        log.info("Sending verification email to user: {} with email: {}", userInformationDTO.username(), userInformationDTO.email());
        String bodyContent = "Sample Email Message";
        EmailDocument savedEmailDocument = emailService.createEmailDeliveryRecord(userInformationDTO.email(), userInformationDTO.username(), MailType.PROFILE_VERIFICATION);
        log.info("Created Email Document in DB with id {} for user: {}", savedEmailDocument.getId(), userInformationDTO.username());
        DeliveryStatus deliveryStatus = bifrostMail.sendMail(userInformationDTO.email(), MailType.PROFILE_VERIFICATION.subject, bodyContent);
        emailService.updateEmailDeliveryStatus(savedEmailDocument.getId(), deliveryStatus);
        return deliveryStatus;
    }
}
