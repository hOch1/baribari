package community.baribari.repository.note.querydsl;

import community.baribari.entity.note.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteRepositoryCustom {

    Page<Note> findByReceiveIdOrSendIdOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findByReceiveIdAndIsReadFalseOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findByReceiveIdOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findBySendIdOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findByReceiveIdOrSendIdAndTitleOrContentContainingOrderByCreatedAtDesc(Long id, String keyword, Pageable pageable);
}
