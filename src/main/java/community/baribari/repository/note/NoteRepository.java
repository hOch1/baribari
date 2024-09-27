package community.baribari.repository.note;

import community.baribari.entity.note.Note;
import community.baribari.repository.note.querydsl.NoteRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long>, NoteRepositoryCustom {
    Page<Note> findByReceiveIdOrSendIdOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findByReceiveIdAndIsReadFalseOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findByReceiveIdOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findBySendIdOrderByCreatedAtDesc(Long id, Pageable pageable);

}
