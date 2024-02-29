package com.rf.labrex.security;

import com.rf.labrex.entity.BaseUser;
import com.rf.labrex.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class Filter extends OncePerRequestFilter {


    public Filter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=getToken(request);
        if(token!=null){
            BaseUser user=tokenService.verifyToken(token);
            if(user!=null){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }
        filterChain.doFilter(request,response);
    }
    private String getToken(HttpServletRequest request){
        var cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cook : cookies){
                if(cook.getName().equals("auth-token") && cook.getValue()!=null && !cook.getValue().isEmpty()){
                    return cook.getValue();
                }
            }
        }
        return null;
    }
}
