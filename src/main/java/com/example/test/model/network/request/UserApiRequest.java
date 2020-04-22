package com.example.test.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {
    private Long id;
    private String account;
    private String password;
    private String status;
    private String email;
    private String phoneNumber;

    public LocalDateTime registeredAt;
    public LocalDateTime unregisteredAt;

}
