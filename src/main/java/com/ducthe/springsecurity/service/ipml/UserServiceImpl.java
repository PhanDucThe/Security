package com.ducthe.springsecurity.service.ipml;

import com.ducthe.springsecurity.model.entity.UserEntity;
import com.ducthe.springsecurity.model.request.AuthenticationRequest;
import com.ducthe.springsecurity.model.request.VerifyTokenRequest;
import com.ducthe.springsecurity.model.response.AuthencicationResponse;
import com.ducthe.springsecurity.model.response.VerifyTokenResponse;
import com.ducthe.springsecurity.repository.UserRepository;
import com.ducthe.springsecurity.service.UserService;
import com.ducthe.springsecurity.util.CreateToken;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateToken createToken;

    @Value("${jwt.signerKey}")
    private String verify;

    @Override
    public AuthencicationResponse authenticate(AuthenticationRequest authenticationRequest) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

        // Lấy User Từ dưới db lên.
        UserEntity userEntity = userRepository.findByUserName(authenticationRequest.getUserName());

        // Tiếp theo là mình sẻ kiểm tra mật khẩu có khớp hay không.
        // Và đồng thời mình cũng viết một hàm tạo ra Token.
        String token = "";
        if (bCryptPasswordEncoder.matches(authenticationRequest.getPassword(), userEntity.getPassword())) {
            token = createToken.generateToken(authenticationRequest.getUserName());
        }


        return AuthencicationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }

    @Override
    public VerifyTokenResponse verifyToken(VerifyTokenRequest verifyTokenRequest) throws JOSEException, ParseException {
        String token = verifyTokenRequest.getToken(); // Lấy ra token.

        // Tạo ra đối tượng JWSVerifier để check token. thực chất là kiểm tra cái sign của mình có đúng khonông.

            JWSVerifier jwsVerifier = new MACVerifier(verify.getBytes()); // chú ý phải giống với lúc mình kí.

        // Để mã hoá đc token. mình tạo một đối tượng. SignedJWT
            SignedJWT signedJWT = SignedJWT.parse(token); // Chuyển về dạng chữ kí của mình để kiểm tra.

        // Kiểm tra token có hết hạn hay chưa
        Date expri = signedJWT.getJWTClaimsSet().getExpirationTime();

        // Kiểm tra trả về true or false.
        boolean verified = signedJWT.verify(jwsVerifier);
        return VerifyTokenResponse.builder()
                .valid(verified && expri.after(new Date()))
                .build();
    }
}
