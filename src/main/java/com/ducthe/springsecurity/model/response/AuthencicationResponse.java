package com.ducthe.springsecurity.model.response;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthencicationResponse {

    private String token;
    private boolean isAuthenticated;
}
