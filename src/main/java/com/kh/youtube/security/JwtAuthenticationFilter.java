package com.kh.youtube.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { // 한 번만 인증하는 필터




    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청에서 토큰 가져오기
        String token = parseBearerToken(request);

        // 토큰 검사
        if(token!=null && !token.equalsIgnoreCase("null")){ //equalsIgnoreCase() 메소드는 대소문자 구분없이 문자열 비교
            // Member -> id
            String id = tokenProvider.validateAndGetUserId(token);
            
            // 인증 완료후 SecurityContextHolder에 등록
            AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    id, // 인증된 사용자 정보
                    null,
                    AuthorityUtils.NO_AUTHORITIES
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 내용이 비어있는 SecurityContext 생성
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); 
            
            // securityContext안에 위에서 정보를 담은 authentication 넘기기
            securityContext.setAuthentication(authentication);

            // authentication 정보를 가지고 있는 securityContext를 SecurityContextHolder에 등록
            SecurityContextHolder.setContext(securityContext);
        }

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request){
        // Http 요청의 헤더를 파싱해 Bearer 토큰을 리턴한다.
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
