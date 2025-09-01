package com.souko;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class SoukoApplicationTests {
    SecretKey key = Keys.hmacShaKeyFor("mysecretmysecretmysecretmysecret".getBytes());
    String token = "";
    @BeforeEach
    public  void testJwt(){
        Map<String, Object> map = new HashMap();
        map.put("id",1);
        map.put("name","tom");

        String token = Jwts.builder()
                .setClaims(map)//自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        System.out.println(token);
        this.token = token;
    }

    @Test
    public void ParseJwt(){
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(this.token)
                .getBody();
        System.out.println(body);
    }
}
