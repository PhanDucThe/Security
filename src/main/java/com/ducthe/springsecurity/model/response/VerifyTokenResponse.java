package com.ducthe.springsecurity.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyTokenResponse {
    private boolean valid;
}
