package community.baribari.repository.board;

import community.baribari.entity.bari.BariReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BariReviewRepository extends JpaRepository<BariReview, Long> {

    List<BariReview> findTop3ByOrderByCreatedAtDesc();

    Page<BariReview> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
