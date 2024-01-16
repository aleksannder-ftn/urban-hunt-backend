package com.ftn.sss.urbanhunt.security;

import com.ftn.sss.urbanhunt.model.enums.Role;
import com.ftn.sss.urbanhunt.service.interfaces.AgentService;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import com.ftn.sss.urbanhunt.service.interfaces.OwnerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenUtils jwtTokenUtil;

    private final GuestService guestService;

    private final AgentService agentService;

    private final OwnerService ownerService;

    @Autowired
    public JwtTokenFilter(TokenUtils jwtTokenUtil, GuestService guestService, AgentService agentService, OwnerService ownerService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.guestService = guestService;
        this.agentService = agentService;
        this.ownerService = ownerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();

        if (!jwtTokenUtil.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenStr = jwtTokenUtil.getToken(request);
        Role role = jwtTokenUtil.getRoleFromToken(tokenStr);

        switch (role) {
            case GUEST:
                UserDetails guestDetails = guestService.getGuestById(jwtTokenUtil.getUsernameFromToken(tokenStr));

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        guestDetails, null,
                        guestDetails == null ?
                                List.of() : guestDetails.getAuthorities()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                break;
            case AGENT:
                UserDetails agentDetails = agentService.getAgentById(jwtTokenUtil.getUsernameFromToken(tokenStr));

                authentication = new UsernamePasswordAuthenticationToken(
                        agentDetails, null,
                        agentDetails == null ?
                                List.of() : agentDetails.getAuthorities()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                break;
            case OWNER:
                UserDetails ownerDetails = ownerService.getOwnerById(jwtTokenUtil.getUsernameFromToken(tokenStr));

                authentication = new UsernamePasswordAuthenticationToken(
                        ownerDetails, null,
                        ownerDetails == null ?
                                List.of() : ownerDetails.getAuthorities()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                break;
        }
    }
}
