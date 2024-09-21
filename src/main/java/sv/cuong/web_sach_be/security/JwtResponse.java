package sv.cuong.web_sach_be.security;

public class JwtResponse {

    private final String jwt;

    public String getJwt() {
        return jwt;
    }

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }
}
