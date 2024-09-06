package community.baribari.service.member;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.member.AccountSettingDto;
import community.baribari.dto.member.MemberDto;
import community.baribari.entity.member.AccountSetting;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;


    @Test
    @DisplayName("회원_정보_조회")
    void getMember() {
        Optional<Member> member = Optional.of(Member.builder()
                .id(1L)
                .accountSetting(AccountSetting.builder().build())
                .build());

        when(memberRepository.findById(1L)).thenReturn(member);

        MemberDto memberDto = memberService.getMember(1L);


        assertNotNull(memberDto);
        assertEquals(memberDto.getId(), member.get().getId());
        verify(memberRepository).findById(1L);
    }

    @Test
    @DisplayName("회원_정보_조회_실패")
    void getMember_fail() {
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        CustomException customException = assertThrows(CustomException.class, () -> memberService.getMember(1L));

        assertEquals(customException.getErrorCode(), ErrorCode.MEMBER_NOT_FOUND);
        verify(memberRepository).findById(1L);
    }

    @Test
    @DisplayName("회원_계정_설정_조회")
    void getAccountSetting() {
        AccountSetting accountSetting = AccountSetting.builder().build();
        Member member = Member.builder()
                .accountSetting(accountSetting)
                .build();

        AccountSettingDto accountSettingDto = memberService.getAccountSetting(member);

        assertNotNull(accountSettingDto);
    }

    @Test
    @DisplayName("회원_계정_설정_변경")
    void editAccountSetting() {
        AccountSettingDto accountSettingDto = AccountSettingDto.builder().build();
        AccountSetting accountSetting = AccountSetting.builder().build();
        Member member = Member.builder()
                .accountSetting(accountSetting)
                .build();

        memberService.editAccountSetting(member, accountSettingDto);

        verify(memberRepository).save(member.updateAccountSetting(accountSettingDto));
    }

    @Test
    @DisplayName("회원_목록")
    void list() {
        Member member1 = Member.builder().id(1L).accountSetting(AccountSetting.builder().build()).build();
        Member member2 = Member.builder().id(2L).accountSetting(AccountSetting.builder().build()).build();
        List<Member> members = List.of(member1, member2);

        when(memberRepository.findAll()).thenReturn(members);

        List<MemberDto> memberDtos = memberService.list();

        assertNotNull(memberDtos);
        assertEquals(memberDtos.size(), members.size());
        assertEquals(memberDtos.get(0).getId(), members.get(0).getId());
        assertEquals(memberDtos.get(1).getId(), members.get(1).getId());
        verify(memberRepository).findAll();
    }

    @Test
    @DisplayName("회원_탈퇴")
    void deleteMember() {
        Member member = Member.builder()
                .id(1L)
                .accountSetting(AccountSetting.builder().build())
                .build();

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        memberService.deleteMember(1L, new PrincipalDetail(member));

        assertTrue(member.isDeleted());
        verify(memberRepository).save(member.delete());
    }

    @Test
    @DisplayName("회원_탈퇴_본인아님")
    void deleteMember_not_self() {
        Member member = Member.builder()
                .id(1L)
                .accountSetting(AccountSetting.builder().build())
                .build();

        CustomException customException = assertThrows(CustomException.class, () ->
                memberService.deleteMember(2L, new PrincipalDetail(member)));

        assertEquals(customException.getErrorCode(), ErrorCode.UNAUTHORIZED);
    }
}