package community.baribari.dto.sign;

import community.baribari.entity.member.AccountSetting;
import community.baribari.entity.member.Member;
import community.baribari.entity.member.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class SignUpDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2, message = "이름은 2자 이상 입력해주세요.")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 입력 가능합니다.")
    private String name;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, message = "닉네임은 2자 이상 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, message = "비밀번호는 8자 이상 입력해주세요.")
    @Pattern(
            regexp = "(?=.*[a-z])(?=.*\\d)[a-z\\d]{8,}$",
            message = "비밀번호는 최소 8자 이상이며, 소문자, 숫자를 포함해야 합니다."
    )
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
