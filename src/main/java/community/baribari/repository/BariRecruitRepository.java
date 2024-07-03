package community.baribari.repository;

import community.baribari.entity.bari.BariRecruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BariRecruitRepository extends JpaRepository<BariRecruit, Long> {

    Page<BariRecruit> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
