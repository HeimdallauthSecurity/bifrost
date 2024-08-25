package com.heimdallauth.auth.bifrost.controller.v1;

import com.heimdallauth.auth.bifrost.constants.DeliveryStatus;
import com.heimdallauth.auth.bifrost.records.EmailRequestDTO;
import com.heimdallauth.auth.bifrost.services.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/email")
public class EmailController {
    private final UserProfileService userProfileService;

    @Autowired
    public EmailController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Operation(summary = "Send an email", description = "Send an email based on the request", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully sent email"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @PostMapping("/send")
    public DeliveryStatus sendEmail(@RequestBody EmailRequestDTO emailRequestDTO){
        return userProfileService.processEmailSendRequest(emailRequestDTO);
    }
}