package utfpr.cc66c.core.validators;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTValidator {
    private static final String TOKEN_KEY = "DISTRIBUIDOS";
    private static final Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY);
    private static final JWTVerifier verifier = JWT.require(algorithm).build();

    public static String generateToken(String id, String role) {
        return JWT.create()
                .withClaim("id", id)
                .withClaim("role", role)
                .sign(algorithm);
    }

    public static String getIdClaim(String token) {
        return verifier.verify(token).getClaim("id").asString();
    }

    public static String getRoleClaim(String token) {
        return verifier.verify(token).getClaim("role").asString();
    }
}
