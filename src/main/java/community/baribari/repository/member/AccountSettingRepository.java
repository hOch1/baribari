package community.baribari.repository.member;

import community.baribari.entity.member.AccountSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountSettingRepository extends JpaRepository<AccountSetting, Long> {
}
