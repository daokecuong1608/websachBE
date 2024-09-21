package sv.cuong.web_sach_be.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SERECT = "1232435677hjhg6sdlkfjslfjhdskjfhskjfhalsdkhfjaksfgdhhshiflhweoirfhjkksdjhfalkshskdhfasdkfh7775465768";

    //Tạo JWT dựa trên đăng nhập
    public String generateToken(String tenDangNhap) {
        Map<String, Object> claims = new HashMap<>();
         claims.put("isAdmin" , true);
        claims.put("x" , true);
        return createToken(claims, tenDangNhap);
    }

    //Tạo JWt với các claim đã chọn
    private String createToken(Map<String, Object> claims, String tenDangNhap) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(tenDangNhap)
                .setIssuedAt(new Date(System.currentTimeMillis()))//lấy thời gian phát hành JWT
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) //JWt tự động hết hạn
                .signWith(SignatureAlgorithm.HS512, getSigneKey())
                .compact();
    }

    //lấy serect key
    private Key getSigneKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SERECT);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Trich xuat thong tin
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigneKey()).parseClaimsJwt(token).getBody();
    }

    //trich xuất thông tin cho 1 claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    //kiểm tra thời gian hết hạn từ jwt
    public Date extractExpriration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //kiểm tra tài khoản đã đăng nhập hay chưa
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //kiem tra token da het han
    private Boolean isTokenExpired(String token) {
        return extractExpriration(token).before(new Date());
    }

    //kiem tra tinh hop le
    public Boolean validateToken(String token , UserDetails userDetails){
        final String tenDangNhap = extractUsername(token);
        System.out.println(tenDangNhap);
        return  (tenDangNhap.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

}
