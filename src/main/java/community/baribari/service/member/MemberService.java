package community.baribari.service.member;

import community.baribari.dto.member.AccountSettingDto;
import community.baribari.dto.member.MemberDto;
import community.baribari.entity.member.AccountSetting;
import community.baribari.entity.member.Member;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.member.AccountSettingRepository;
import community.baribari.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final AccountSettingRepository accountSettingRepository;

    public MemberDto getMember(Long id){
        return MemberDto.toDto(memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)));
    }

    public AccountSettingDto getAccountSetting(Member member) {
        return AccountSettingDto.toDto(member.getAccountSetting());
    }

    @Transactional
    public void editAccountSetting(Member member, AccountSettingDto accountSettingDto) {
        memberRepository.save(member.updateAccountSetting(accountSettingDto));
        log.info("{}님이 계정 설정 변경하였습니다.", member.getNickname());
    }

}
