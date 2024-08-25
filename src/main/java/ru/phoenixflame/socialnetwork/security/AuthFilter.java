package ru.phoenixflame.socialnetwork.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.phoenixflame.socialnetwork.entity.AuthEntity;
import ru.phoenixflame.socialnetwork.repository.AuthRepository;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthFilter implements Filter {
    private final AuthRepository authRepository;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();

        if("/login".equals(uri) || "/user/register".equals(uri) || "/user/search".equals(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        if (req.getHeader("Authorization") != null) {
            String token = req.getHeader("Authorization");
            String userId = req.getHeader("user_id");
            AuthEntity authEntity = authRepository.findByUserIdAndToken(userId, token);
            if(Objects.nonNull(authEntity)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse res = (HttpServletResponse) servletResponse;
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        } else {
            HttpServletResponse res = (HttpServletResponse) servletResponse;
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}