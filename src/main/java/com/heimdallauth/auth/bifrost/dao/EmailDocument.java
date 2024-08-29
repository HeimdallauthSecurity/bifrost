package com.heimdallauth.auth.bifrost.dao;

import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import com.heimdallauth.auth.bifrost.constants.MailType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "email-collection")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDocument {
    @Id
    private String id;
    @Indexed(unique = false)
    private String username;
    @Indexed(unique = false)
    private String emailAddress;
    private String emailBody;
    private MailType mailType;
    private DeliveryStatus deliveryStatus;
    private Instant requestCreatedOn;
    private Instant deliveryStatusUpdatedOn;
}
