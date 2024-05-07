package faddy.backend.user.domain;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class PasswordEncoderTest {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void testPasswordEncoding() {
        List<PasswordPair> passwordPairs = new ArrayList<>();

        // 임의의 비밀번호 10개 생성
        String[] plainPasswords = {
                "password123", "qwerty456", "abc123def", "mysecretkey",
                "letmein123", "changeme456", "admin123", "user12345",
                "password!@#", "qwertyuiop"
        };

        for (String plainPassword : plainPasswords) {
            String encodedPassword = passwordEncoder.encode(plainPassword);
            passwordPairs.add(new PasswordPair(plainPassword, encodedPassword));
        }

        // 비밀번호 쌍 출력
        for (PasswordPair pair : passwordPairs) {
            System.out.println("평문 비밀번호: " + pair.getPlainPassword());
            System.out.println("암호화된 비밀번호: " + pair.getEncodedPassword());
            System.out.println();
        }
    }

    private static class PasswordPair {
        private final String plainPassword;
        private final String encodedPassword;

        public PasswordPair(String plainPassword, String encodedPassword) {
            this.plainPassword = plainPassword;
            this.encodedPassword = encodedPassword;
        }

        public String getPlainPassword() {
            return plainPassword;
        }

        public String getEncodedPassword() {
            return encodedPassword;
        }
    }
}