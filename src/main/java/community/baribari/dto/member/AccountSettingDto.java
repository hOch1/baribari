package community.baribari.dto.member;


import community.baribari.entity.member.AccountSetting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountSettingDto {
    private boolean postVisibility;
    private boolean commentVisibility;
    private boolean profileVisibility;
    private boolean noteBlock;

    public static AccountSettingDto toDto(AccountSetting accountSetting) {
        return AccountSettingDto.builder()
                .postVisibility(accountSetting.isPostVisibility())
                .commentVisibility(accountSetting.isCommentVisibility())
                .profileVisibility(accountSetting.isProfileVisibility())
                .build();
    }

}
