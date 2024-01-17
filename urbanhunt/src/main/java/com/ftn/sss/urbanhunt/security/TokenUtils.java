package com.ftn.sss.urbanhunt.security;


import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.model.enums.Role;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
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

    private final UserService userService;

    @Autowired
    public TokenUtils(UserService userService) {
        this.userService = userService;
    }

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateToken(Long id) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(id.toString())
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
        } catch (Exception e) {
            userId = null;
        }

        return userId;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
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
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    public Boolean validateToken(String token, User userDetails) {
        final Long userId = getUsernameFromToken(token);

        return (userId != null
                && userId.equals(userDetails.getId()));
    }

    public int getExpiredIn() { return EXPIRES_IN;}

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    public boolean validate(String token) {
        Long userId = getUsernameFromToken(token);

        return (userId != null && userService.getUserById(userId) != null);
    }
}

