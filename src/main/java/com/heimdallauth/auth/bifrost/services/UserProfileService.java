package com.heimdallauth.auth.bifrost.services;

import com.heimdallauth.auth.bifrost.clients.UserProfileClient;
import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import com.heimdallauth.auth.bifrost.constants.MailType;
import com.heimdallauth.auth.bifrost.dto.UserInformationDTO;
import com.heimdallauth.auth.bifrost.mailer.BifrostMail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserProfileService {
    private final UserProfileClient userProfileClient;
    private final BifrostMail bifrostMail;

    @Autowired
    public UserProfileService(UserProfileClient userProfileClient, BifrostMail bifrostMail) {
        this.userProfileClient = userProfileClient;
        this.bifrostMail = bifrostMail;
    }
    private DeliveryStatus sendUserProfileVerificationEmail(String username) {
        UserInformationDTO userInformationDTO = userProfileClient.getUserInformation(username);
        log.info("Sending verification email to user: {} with email: {}", userInformationDTO.username(), userInformationDTO.email());
        String bodyContent = StringUtils.EMPTY;
        return bifrostMail.sendMail(userInformationDTO.email(), MailType.PROFILE_VERIFICATION.subject, bodyContent);
    }
}
