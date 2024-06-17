package community.baribari.service;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.sign.SignUpDto;
import community.baribari.entity.Member;
import community.baribari.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SignService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignUpDto signUpDto) {
        Member member = signUpDto.toEntity(passwordEncoder);
        Member singUpMember = memberRepository.save(member);

        log.info("{}님이 회원가입 했습니다", singUpMember.getName());
    }

    @Transactional
    public void setNickname(String nickname, PrincipalDetail principalDetail) {
        if (memberRepository.existsByNickname(nickname))
            throw new IllegalArgumentException("닉네임이 중복 됩니다.");


        Member member = principalDetail.getMember().updateNickname(nickname);
        memberRepository.save(member);
        log.info("{}님이 닉네임을 {}으로 설정했습니다.", member.getName(), nickname);
    }
}
