package com.estate.back.handler;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.estate.back.common.object.CustomOAuth2User;
import com.estate.back.provider.JwtProvider;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

@Component
@RequiredArgsConstructor
// SimpleUrlAuthenticationSuccessHandler 안에서 onAuthenticationSuccess 가져오기
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Value("${front.host}")
    private String FRONT_HOST;

    @Override
	public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
		Authentication authentication
    ) throws IOException, ServletException {
        // 접근주체에 대한 정보를 가져옴
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal(); // object로 생성되어서 강제로 형변환 해야함
        String userId = oAuth2User.getName();

        String token = jwtProvider.create(userId);

        response.sendRedirect(FRONT_HOST + "/sns/" + token + "/36000");
    }
}
