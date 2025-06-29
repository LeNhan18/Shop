package com.project.shopapp.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JWTUtils {

    @Value("${jwt.experation}")
    private int experation; //Luu vao mot cai bien moi truong

    @Value("${jwt.secretKey}")
    private String secretKey ;

    public String generateToken(com.project.shopapp.MODELS.User user){
        //properties
        Map<String,Object> claims = new HashMap<>();
        claims.put("phoneNumber",user.getPhoneNumber());
        try{
             String token = Jwts.builder()
                     .setClaims(claims)//Bên trong claim chứa những cái gì
                     .setSubject(user.getPhoneNumber())
                     .setExpiration(new Date(System.currentTimeMillis()+experation *1000L))
                     .signWith(getSignKey(), SignatureAlgorithm.HS256)
                     .compact();
             return token;
         }catch(Exception e){
            //Bạn có thể sử dụng Logger ,instead System.out.println
            System.err.println("Cannot create jwt token error : "+e.getMessage());
        return null;
        }
    }
    private Key getSignKey(){
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
               .setSigningKey(getSignKey())
               .build()
               .parseClaimsJws(token)
               .getBody();
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsTFunction)
    {
        final Claims claims  = this.extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    //check experation
    private boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }


}
