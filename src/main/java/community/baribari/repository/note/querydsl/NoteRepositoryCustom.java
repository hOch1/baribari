package community.baribari.repository.note.querydsl;

import community.baribari.entity.note.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteRepositoryCustom {

    Page<Note> findByReceiveIdOrSendIdAndTitleOrContentContainingOrderByCreatedAtDesc(Long id, String keyword, Pageable pageable);
}
