package com.sp.fc.web.config;

import com.sp.fc.web.student.StudentAuthenticationToken;
import com.sp.fc.web.teacher.TeachertAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    public CustomLoginFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        username = (username != null) ? username : "";
        username = username.trim();
        String password = obtainPassword(request);
        password = (password != null) ? password : "";
        String type = request.getParameter("type");
        if (type == null || !type.equals("teacher")){
            StudentAuthenticationToken token = StudentAuthenticationToken.builder().
                    credentials(username).build();
            return this.getAuthenticationManager().authenticate(token);
        }
        else{
            TeachertAuthenticationToken token = TeachertAuthenticationToken.builder().credentials(username).build();
            return this.getAuthenticationManager().authenticate(token);
        }

    }
}
