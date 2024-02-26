package pl.koneckimarcin.triathlontrainingmanagement.strava.dto;

public class RefreshTokenResponseDto {

    private String token_type;
    private String access_token;
    private String expires_at;
    private String expires_in;
    private String refresh_token;

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getExpires_at() {
        return expires_at;
    }
}
