package community.baribari.repository.member;

import community.baribari.entity.member.AccountSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountSettingRepository extends JpaRepository<AccountSetting, Long> {

    Optional<AccountSetting> findByMemberId(Long memberId);
}
