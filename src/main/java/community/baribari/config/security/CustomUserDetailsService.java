package community.baribari.config.security;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.member.Member;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.MEMBER_NOT_FOUND.getMessage()));

        if (member.isDeleted())
            throw new CustomException(ErrorCode.DELETED_MEMBER);

        log.info("{}님이 로그인 하였습니다. ID : {}",member.getName(), member.getId());
        return new PrincipalDetail(member);
    }
}
