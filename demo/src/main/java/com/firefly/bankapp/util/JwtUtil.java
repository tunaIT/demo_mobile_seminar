package com.firefly.bankapp.util;

import com.firefly.bankapp.entity.UserEntity;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {


    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "4509e1e738867146f0abda72624724a2dc84560753ffecfaf66bc35e50988f15";
    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 31556926000L;

    @Setter
    @Getter
    private String jwt;

    // tạo ra jwt từ thông tin người dùng
    public String generateToken(UserEntity userEntity){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // tạo chuỗi json web token từ id của user
        //Bắt đầu tạo chuỗi JWT bằng cách sử dụng Jwts.builder().
        jwt = Jwts.builder()
                // Thiết lập subject của token là email của người dùng,
                .setSubject(userEntity.getEmail())
                //Thiết lập thời gian phát hành token.
                .setIssuedAt(now)
                //Thiết lập thời gian hết hạn của token.
                .setExpiration(expiryDate)
                //Ký token bằng thuật toán HS256 và bí mật được lấy từ phương thức getSignKey().
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                //Hoàn tất quá trình xây dựng và trả về chuỗi JWT.
                .compact();
        return jwt;
    }
    // Lấy thông tin user từ jwt
    public String getEmailFromJwt(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid token");
        }
    }
    public boolean validateToken(String authToken, UserEntity userEntity) {
        final String email = getEmailFromJwt(authToken);
        return email.equals(userEntity.getEmail());
    }

    //Phương thức này lấy khóa ký để sử dụng cho việc ký và xác thực token.
    private Key getSignKey(){
        //Giải mã chuỗi bí mật từ định dạng BASE64 thành mảng byte.
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        //Tạo và trả về khóa HMAC SHA cho việc ký token.
        return Keys.hmacShaKeyFor(keyBytes);
    }
}