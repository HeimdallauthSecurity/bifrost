package com.heimdallauth.auth.bifrost.clients;

import com.heimdallauth.auth.bifrost.dto.UserInformationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-profile-service", url = "${heimdallauth.user-profile-service.url}")
public interface UserProfileClient {
    @GetMapping("/users/{username}")
    UserInformationDTO getUserInformation(@PathVariable String username);
}
