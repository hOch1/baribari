package community.baribari.service.board.extend;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BariRecruitDto;
import community.baribari.entity.board.BariRecruit;
import community.baribari.repository.board.BoardRepository;
import community.baribari.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BariRecruitService extends BoardService<BariRecruit, BariRecruitDto> {

    public BariRecruitService(BoardRepository<BariRecruit> boardRepository) {
        super(boardRepository);
    }

    @Override
    protected BariRecruit toEntity(BariRecruitDto dto, PrincipalDetail principalDetail) {
        return BariRecruit.toEntity(dto, principalDetail);
    }

    @Override
    protected void updateBoard(BariRecruit board, BariRecruitDto dto) {
        board.update(dto);
    }

    @Override
    protected BariRecruitDto toDto(BariRecruit entity) {
        return BariRecruitDto.toDto(entity);
    }

    @Override
    protected String getBoardTypeName() {
        return "바리 모집";
    }
}
