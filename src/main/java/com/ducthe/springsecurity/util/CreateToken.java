package com.ducthe.springsecurity.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateToken {

    @Value("${jwt.signerKey}")
    private String signerKey;

    public  String generateToken(String userName) {

        // B1: Tạo ra một đối tượng JWSObject, đối tượng này chính là một cái token,
        // Khi khởi tạo đối tượng này cần có 2 tham số truyền vào là. JWSHeader,

        // Cái này chính là JWSHeader.
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512); // JWSAlgorithm chính là thuật toán để mã hoá.

        // Còn về phần Payload mình sử dụng đối tượng JWTClaimsSet. để tạo ra các thông tin cần thiết cho payload sau đó add nó vào payload.
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                                    .subject(userName) // Chính là đối tượng use token.
                                    .issuer("ducthe.com") // Chính là token được tạo ra từ ai.
                                    .expirationTime(new Date(new Date().getTime() + 1000 * 60 * 60 * 24)) // thời gian hết hạn token.
                                    .issueTime(new Date()) // thời gian tạo ra token.
                                    .build();

        // Này chính là payload.
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        // Bước tiếp theo là một ra một signature cho riêng mình, thì mình dùng method sign của jwsObject.
        // MACSigner(). chính là cách kí và giải mã. trong đó là một chuỗi dài 32bytes

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }
}
