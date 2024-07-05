package community.baribari.repository.board;

import community.baribari.entity.bari.BariRecruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BariRecruitRepository extends JpaRepository<BariRecruit, Long> {

    List<BariRecruit> findTop3ByOrderByCreatedAtDesc();

    Page<BariRecruit> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
