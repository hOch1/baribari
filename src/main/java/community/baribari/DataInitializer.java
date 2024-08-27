package community.baribari;

import community.baribari.entity.member.AccountSetting;
import community.baribari.entity.member.Member;
import community.baribari.entity.member.Role;
import community.baribari.repository.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {

        Member admin = Member.builder()
                .email("admin@admin")
                .password(passwordEncoder.encode("admin"))
                .nickname("admin")
                .role(Role.ROLE_ADMIN)
                .accountSetting(AccountSetting.builder().build())
                .build();

        Member member = Member.builder()
                .email("user@user")
                .password(passwordEncoder.encode("user"))
                .nickname("user")
                .role(Role.ROLE_MEMBER)
                .accountSetting(AccountSetting.builder().build())
                .build();

        memberRepository.save(admin);
        memberRepository.save(member);
    }

}
