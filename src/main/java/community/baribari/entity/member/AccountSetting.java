package community.baribari.entity.member;

import community.baribari.dto.member.AccountSettingDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AccountSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private boolean postVisibility = true;

    @Builder.Default
    private boolean commentVisibility = true;

    @Builder.Default
    private boolean profileVisibility = true;

    @Builder.Default
    private boolean noteBlock = false;

    @OneToOne(mappedBy = "accountSetting")
    private Member member;

    public AccountSetting update(AccountSettingDto accountSettingDto) {
        this.profileVisibility = accountSettingDto.isProfileVisibility();
        this.commentVisibility = accountSettingDto.isCommentVisibility();
        this.postVisibility = accountSettingDto.isPostVisibility();
        this.noteBlock = accountSettingDto.isNoteBlock();
        return this;
    }
}