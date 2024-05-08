package assignment.server.security;

public class SecurityService {

  public boolean checkSecurity(String token) {
    return "123@123a".equals(token);
  }
}
