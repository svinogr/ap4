package ap.services;

public interface TokenService {
    String createToken(String login);
    String loginUserByToken(String token);
    void deleteToken(String token);

}
