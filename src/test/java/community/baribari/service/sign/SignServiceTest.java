package community.baribari.service.sign;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.sign.SignUpDto;
import community.baribari.entity.member.Member;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SignService signService;

    @Test
    @DisplayName("회원가입")
    void signup() {
        SignUpDto signUpDto = createSignUpDto();
        Member member = signUpDto.toEntity(passwordEncoder);

        when(memberRepository.existsByEmail(signUpDto.getEmail())).thenReturn(false);
        when(memberRepository.existsByNickname(signUpDto.getNickname())).thenReturn(false);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        signService.signup(signUpDto);

        verify(memberRepository).existsByEmail(signUpDto.getEmail());
        verify(memberRepository).existsByNickname(signUpDto.getNickname());
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    @DisplayName("회원가입_이메일_중복")
    void signup_email_duplicate() {
        SignUpDto signUpDto = createSignUpDto();

        when(memberRepository.existsByEmail(signUpDto.getEmail())).thenReturn(true);

        CustomException customException = assertThrows(CustomException.class, () -> signService.signup(signUpDto));

        assertEquals(customException.getErrorCode(), ErrorCode.ALREADY_EXIST_EMAIL);
        verify(memberRepository).existsByEmail(signUpDto.getEmail());
    }

    @Test
    @DisplayName("회원가입_닉네임_중복")
    void signup_nickname_duplicate() {
        SignUpDto signUpDto = createSignUpDto();

        when(memberRepository.existsByEmail(signUpDto.getEmail())).thenReturn(false);
        when(memberRepository.existsByNickname(signUpDto.getNickname())).thenReturn(true);

        CustomException customException = assertThrows(CustomException.class, () -> signService.signup(signUpDto));

        assertEquals(customException.getErrorCode(), ErrorCode.ALREADY_EXIST_NICKNAME);
        verify(memberRepository).existsByEmail(signUpDto.getEmail());
        verify(memberRepository).existsByNickname(signUpDto.getNickname());
    }

    @Test
    @DisplayName("닉네임 설정")
    void setNickname() {
        Member member = Member.builder().build();
        PrincipalDetail principalDetail = new PrincipalDetail(member);

        when(memberRepository.existsByNickname("test")).thenReturn(false);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        signService.setNickname("test", principalDetail);

        verify(memberRepository).existsByNickname("test");
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    @DisplayName("닉네임_설정_중복")
    void setNickname_duplicate() {
        Member member = Member.builder().build();
        PrincipalDetail principalDetail = new PrincipalDetail(member);

        when(memberRepository.existsByNickname("test")).thenReturn(true);

        CustomException customException = assertThrows(CustomException.class, () -> signService.setNickname("test", principalDetail));

        assertEquals(customException.getErrorCode(), ErrorCode.ALREADY_EXIST_NICKNAME);
        verify(memberRepository).existsByNickname("test");
    }

    private SignUpDto createSignUpDto() {
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setEmail("test@test.com");
        signUpDto.setNickname("test");
        signUpDto.setName("test");
        signUpDto.setPassword("test12345");
        return signUpDto;
    }
}