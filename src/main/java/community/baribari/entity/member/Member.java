package community.baribari.entity.member;

import community.baribari.dto.member.AccountSettingDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nickname;
    private String email;
    private String password;

    @Builder.Default
    private boolean deleted = false;

    @Builder.Default
    private boolean isSocial = false;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_setting_id")
    private AccountSetting accountSetting;

    public Member update(String name){
        this.name = name;
        return this;
    }



    public Member updateNickname(String nickname){
        this.nickname = nickname;
        return this;
    }

    public Member updateAccountSetting(AccountSettingDto accountSettingDto){
        AccountSetting accountSetting = AccountSetting.builder()
                .profileVisibility(accountSettingDto.isProfileVisibility())
                .commentVisibility(accountSettingDto.isCommentVisibility())
                .postVisibility(accountSettingDto.isPostVisibility())
                .noteBlock(accountSettingDto.isNoteBlock())
                .build();

        this.accountSetting = accountSetting;
        return this;
    }
}
