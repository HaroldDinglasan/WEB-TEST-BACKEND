package com.rocs.osd.utils.security.jwt.filter.authentication.forbidden;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocs.osd.domain.http.response.HttpResponse;
import com.rocs.osd.utils.security.constant.SecurityConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Triggers when authentication fails.
 * */
@Component
public class AuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        if (exception instanceof UsernameNotFoundException) {
            HttpResponse httpResponse = new HttpResponse(HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.getReasonPhrase().toUpperCase(),
                    "Username not found.");
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            OutputStream outputStream = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputStream, httpResponse);
            outputStream.flush();
        } else {
            HttpResponse httpResponse = new HttpResponse(HttpStatus.FORBIDDEN.value(),
                    HttpStatus.FORBIDDEN,
                    HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(),
                    SecurityConstant.FORBIDDEN_MESSAGE);
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            OutputStream outputStream = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputStream, httpResponse);
            outputStream.flush();
        }
    }
}
