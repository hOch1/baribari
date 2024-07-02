package community.baribari.dto.sign;

import community.baribari.entity.member.Member;
import community.baribari.entity.member.Role;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class SignUpDto {

    private String email;
    private String name;
    private String nickname;
    private String password;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_MEMBER)
                .build();
    }
}
