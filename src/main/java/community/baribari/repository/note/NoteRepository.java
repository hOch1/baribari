package community.baribari.repository.note;

import community.baribari.entity.note.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Page<Note> findByReceiveIdOrSendIdOrderByCreatedAtDesc(Long id, Long id1, Pageable pageable);

    Page<Note> findByReceiveIdAndIsReadFalseOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findByReceiveIdOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findBySendIdOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findByReceiveIdOrSendIdAndTitleOrContentContainingOrderByCreatedAtDesc(Long receiveId, Long sendId, String titleKeyword, String contentKeyword, Pageable pageable);
}
