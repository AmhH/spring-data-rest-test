package com.example.springdataresttest.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class CorsConfigTest {

    @InjectMocks
    private com.example.springdataresttest.config.CorsConfig corsConfig;

    @Test
    public void doFilter() throws IOException, ServletException {

        corsConfig.doFilter(Mockito.mock(HttpServletRequest.class), Mockito.mock(HttpServletResponse.class), Mockito.mock(FilterChain.class));
    }

    @Test
    public void initTest() {
        corsConfig.init(Mockito.mock(FilterConfig.class));
    }

    @Test
    public void destroyTest() {
        corsConfig.destroy();
    }
}