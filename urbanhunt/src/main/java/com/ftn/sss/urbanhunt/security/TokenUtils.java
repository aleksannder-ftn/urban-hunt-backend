package com.ftn.sss.urbanhunt.security;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.model.enums.Role;
import com.ftn.sss.urbanhunt.service.interfaces.AgentService;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import com.ftn.sss.urbanhunt.service.interfaces.OwnerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtils {

    @Value("spring-security-urban-hunt")
    private String APP_NAME;

    @Value("2z8T$@Lp3!vE9FmQn*A5hXg$uBpKwYrZ")
    public String SECRET;

    @Value("1800000")
    private int EXPIRES_IN;

    @Value("Authorization")
    private String AUTH_HEADER;

    private static final String AUDIENCE_WEB = "web";

    @Autowired
    private GuestService guestService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private OwnerService ownerService;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateToken(Long id, Role role) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(id.toString())
                .claim("role", role)
                .setAudience(generateAudience())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    private String generateAudience() {
        return AUDIENCE_WEB;
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + EXPIRES_IN);
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public Long getUsernameFromToken(String token) {
        Long userId;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            userId = Long.parseLong(claims.getSubject());
        } catch (ExpiredJwtException ex) {
            userId = null;
        } catch (Exception e) {
            userId = null;
        }

        return userId;
    }

    public Role getRoleFromToken(String token) {
        Role role;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            role = Role.valueOf((String) claims.get("role"));
        } catch (ExpiredJwtException ex) {
            role = null;
        } catch (Exception e) {
            role = null;
        }

        return role;
    }
    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (ExpiredJwtException ex) {
            issueAt = null;
        } catch (Exception e) {
            issueAt = null;
        }

        return issueAt;
        }

    public String getAudienceFromToken(String token) {
        String audience;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (ExpiredJwtException ex) {
            audience = null;
        } catch (Exception e) {
            audience = null;
        }

        return audience;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }

        return expiration;
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            claims = null;
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    public Boolean validateToken(String token, User userDetails) {
        User user = userDetails;
        final Long userId = getUsernameFromToken(token);

        return (userId != null
                && userId.equals(user.getId()));
    }

    public int getExpiredIn() { return EXPIRES_IN;}

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    public boolean validate(String token) {
        Long userId = getUsernameFromToken(token);
        Role userRole = getRoleFromToken(token);

        switch (userRole) {
            case GUEST:
                return (userId != null && guestService.getGuestById(userId) != null);
            case AGENT:
                return (userId != null && agentService.getAgentById(userId) != null);
            case OWNER:
                return (userId != null && ownerService.getOwnerById(userId) != null);
            case ADMINISTRATOR:
                // sredi ovoo
        }

        return false;
    }
}

