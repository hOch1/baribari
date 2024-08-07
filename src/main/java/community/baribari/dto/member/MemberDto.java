package community.baribari.dto.member;

import community.baribari.entity.member.Member;
import community.baribari.entity.member.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private AccountSettingDto accountSetting;
    private Role role;

    public static MemberDto toDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .name(member.getName())
                .email(member.getEmail())
                .role(member.getRole())
                .accountSetting(AccountSettingDto.toDto(member.getAccountSetting()))
                .build();
    }
}
