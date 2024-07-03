package community.baribari.repository;

import community.baribari.entity.bari.BariReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BariReviewRepository extends JpaRepository<BariReview, Long> {

    Page<BariReview> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
