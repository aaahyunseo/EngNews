package org.mjulikelion.engnews.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.mjulikelion.engnews.authentication.AuthenticationExtractor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@AllArgsConstructor
public class CookieService {

    public void setCookie(HttpServletResponse response, String accessToken) {
        ResponseCookie cookie = ResponseCookie.from(AuthenticationExtractor.TOKEN_COOKIE_NAME, accessToken)
                .maxAge(Duration.ofMillis(1800000*4))
                .path("/")
                .httpOnly(true)
                .sameSite("None").secure(true)
                .build();

        response.addHeader("set-cookie", cookie.toString());
    }

}