package community.baribari.service.sign;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.sign.SignUpDto;
import community.baribari.entity.member.AccountSetting;
import community.baribari.entity.member.Member;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.member.AccountSettingRepository;
import community.baribari.repository.member.MemberRepository;
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
    private final AccountSettingRepository accountSettingRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignUpDto signUpDto) {
        if (memberRepository.existsByEmail(signUpDto.getEmail()))
            throw new CustomException(ErrorCode.ALREADY_EXIST_EMAIL);

        if (memberRepository.existsByNickname(signUpDto.getNickname()))
            throw new CustomException(ErrorCode.ALREADY_EXIST_NICKNAME);

        Member member = signUpDto.toEntity(passwordEncoder);
        Member singUpMember = memberRepository.save(member);

        log.info("{}님이 회원가입 했습니다", singUpMember.getName());
    }

    @Transactional
    public void setNickname(String nickname, PrincipalDetail principalDetail) {
        if (memberRepository.existsByNickname(nickname))
            throw new CustomException(ErrorCode.ALREADY_EXIST_NICKNAME);

        Member member = principalDetail.getMember().updateNickname(nickname);
        memberRepository.save(member);

        log.info("{}님이 닉네임을 {}으로 설정했습니다.", member.getName(), nickname);
    }
}