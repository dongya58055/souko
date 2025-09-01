package com.souko.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    // 生成签名密钥（长度至少 32 字节）
    private static final SecretKey key = Keys.hmacShaKeyFor("mysecretmysecretmysecretmysecret".getBytes());

    // token 默认过期时间（毫秒），这里设置 1 小时
    private static final long EXPIRATION = 3600 * 1000;

    /**
     * 生成 JWT
     * @param claims 自定义 payload 内容
     * @return token
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 JWT
     * @param token JWT 字符串
     * @return Claims（负载）
     * @throws JwtException token 无效或过期
     */
    public static Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断 token 是否有效（未过期且签名正确）
     * @param token JWT
     * @return true 有效，false 无效
     */
    public static boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * 获取 token 的过期时间
     * @param token JWT
     * @return 过期时间
     */
    public static Date getExpiration(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration();
    }
}
