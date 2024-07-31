package community.baribari.repository.note;

import community.baribari.entity.note.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Page<Note> findAllByReceiveIdAndSendId(Long id, Long id1, Pageable pageable);

    Page<Note> findAllByReceiveIdAndSendIdAndIsReadFalse(Long id, Long id1, Pageable pageable);

    Page<Note> findAllByReceiveId(Long id, Pageable pageable);

    Page<Note> findAllBySendId(Long id, Pageable pageable);
}
