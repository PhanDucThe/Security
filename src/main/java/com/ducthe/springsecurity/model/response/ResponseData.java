package com.ducthe.springsecurity.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData <T>{
    private int status;
    private String message;
    private T data;
}
