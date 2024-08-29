package com.heimdallauth.auth.bifrost.records;

import com.heimdallauth.auth.bifrost.constants.MailType;

public record EmailRequestDTO(
        String profileId,
        MailType mailType
) {
}
