package kr.jay.springsecurityprac.security.token;

import static io.jsonwebtoken.SignatureAlgorithm.*;
import static org.springframework.security.config.Elements.*;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JwtTokenProvider
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 14.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {

	/**
	 * Token Claim 키 값
	 */
	private static final String TOKEN_CLAIM_USER_KEY = "username";

	/**
	 * Token 속성
	 */
	private final JwtTokenProviderProperties properties;

	/**
	 * 암호화 키
	 */
	private Key key;

	@Override
	public void afterPropertiesSet() throws Exception {
		key = getSecretKey(properties.getSecret());
	}

	public TokenInfo generateToken(String username) {
		String refreshToken = generateRefreshToken();
		String accessToken = generateAccessToken(username);
		return new TokenInfo(accessToken, refreshToken);
	}

	/**
	 * JWT Payload 사용자 이메일 조회
	 */
	public String getUsername(String token)
		throws
		ExpiredJwtException,
		UnsupportedJwtException,
		MalformedJwtException,
		SignatureException, IllegalArgumentException {
		Claims claims = getClaims(token);
		return claims.get(TOKEN_CLAIM_USER_KEY, String.class);
	}

	/**
	 * JWT Payload Subject 조회
	 */
	public String getSubject(String token)
		throws
		ExpiredJwtException,
		UnsupportedJwtException,
		MalformedJwtException,
		SignatureException,
		IllegalArgumentException {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}

	/**
	 * AccessToken 생성 (사용자 이메일 정보 포함)
	 * @param username 사용자 이메일
	 */
	private String generateAccessToken(String username) {
		Date now = new Date();
		Date expiredDate = new Date(now.getTime() + properties.getAccessTokenValidityInSeconds() * 1000);

		return Jwts.builder()
			.setHeader(createHeader())
			.setSubject(username)
			.setIssuedAt(now)
			.setExpiration(expiredDate)
			.claim(TOKEN_CLAIM_USER_KEY, username)
			.signWith(key, HS256)
			.compact();
	}

	/**
	 * RefreshToken 생성 (사용자 정보 미포함)
	 */
	private String generateRefreshToken() {
		Date now = new Date();
		Date expiredDate = new Date(now.getTime() + properties.getRefreshTokenValidityInSeconds() * 1000);

		return Jwts.builder()
			.setHeader(createHeader())
			.setSubject(UUID.randomUUID().toString())
			.setIssuedAt(now)
			.setExpiration(expiredDate)
			.signWith(key, HS256)
			.compact();
	}

	/**
	 * 암호화 키 생성
	 * @param secretKey 암호키 값
	 * @return 암호화 키
	 */
	private SecretKey getSecretKey(String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	/**
	 * JWT Claim 조회
	 * @param token JWT
	 * @return Claim 정보
	 */
	private Claims getClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	/**
	 * JWT 헤더 설정
	 * @return JWT 헤더
	 */
	private Map<String, Object> createHeader() {
		Map<String, Object> header = new HashMap<>();
		header.put("alg", HS256.getValue());
		header.put("typ", JWT);
		return header;
	}

}
