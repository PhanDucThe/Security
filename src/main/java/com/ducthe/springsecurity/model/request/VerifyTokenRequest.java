package com.ducthe.springsecurity.model.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyTokenRequest {
    private String token;
}
