package community.baribari.service.member;

import community.baribari.dto.board.BoardDto;
import community.baribari.entity.board.Board;
import community.baribari.repository.MemberRepository;
import community.baribari.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;



}
