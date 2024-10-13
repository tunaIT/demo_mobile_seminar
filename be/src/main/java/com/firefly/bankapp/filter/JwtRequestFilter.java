//package com.firefly.bankapp.filter;
//
//import com.firefly.bankapp.entity.UserEntity;
//import com.firefly.bankapp.service.UserService;
//import com.firefly.bankapp.util.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@Component
//@RequiredArgsConstructor
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    private final UserService userService;
//    private final JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        if (!request.getRequestURI().contains("/auth")) {
//            final String authorizationHeader = request.getHeader("Authorization");
//            String email = null;
//            String jwt = null;
//            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//                jwt = authorizationHeader.substring(7);
//                email = jwtUtil.getEmailFromJwt(jwt);
//                jwtUtil.setJwt(jwt);
//            }
//
//            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserEntity userEntity = this.userService.getUserByEmail(email);
//
//                if (jwtUtil.validateToken(jwt, userEntity)) {
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                            userEntity, null, new ArrayList<>());
//                    usernamePasswordAuthenticationToken
//                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                }
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}
