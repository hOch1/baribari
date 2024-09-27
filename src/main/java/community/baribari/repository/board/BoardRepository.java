package community.baribari.repository.board;

import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import community.baribari.entity.member.Member;
import community.baribari.repository.board.querydsl.BoardRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository<T extends Board> extends JpaRepository<T, Long>, BoardRepositoryCustom<T> {

    // 메인 리스트
    List<T> findTop3ByCategoryAndDeletedFalseOrderByCreatedAtDesc(Category category);

    // 카테고리별 리스트
    Page<T> findByCategoryAndDeletedFalseOrderByCreatedAtDesc(Category category, Pageable pageable);

    // 작성 글 리스트
    Page<T> findByMember(Category category, Member member, Pageable pageable);

    // 검색
    // QueryDsl
    Page<T> boardSearch(Category category, String keyword, Pageable pageable);
}
