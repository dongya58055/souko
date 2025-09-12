package com.souko;

import com.souko.config.JwtUtil;
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
        map.put("roleId",1);
        map.put("user","admin");
        String token1 = JwtUtil.generateToken(map);
        System.out.println(token1);
        this.token = token1;

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
