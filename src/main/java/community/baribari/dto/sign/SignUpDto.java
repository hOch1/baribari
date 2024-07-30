package community.baribari.dto.sign;

import community.baribari.entity.member.AccountSetting;
import community.baribari.entity.member.Member;
import community.baribari.entity.member.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class SignUpDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2, message = "이름은 2자 이상 입력해주세요.")
    private String name;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, message = "닉네임은 2자 이상 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, message = "비밀번호는 8자 이상 입력해주세요.")
    private String password;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .name(name)
                .email(email)
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_MEMBER)
                .accountSetting(AccountSetting.builder()
                        .postVisibility(true)
                        .commentVisibility(true)
                        .profileVisibility(true)
                        .build()
                )
                .build();
    }
}
