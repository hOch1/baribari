package community.baribari.repository.note;

import community.baribari.entity.note.Note;
import community.baribari.repository.note.querydsl.NoteRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends JpaRepository<Note, Long>, NoteRepositoryCustom {
    @Query("SELECT n FROM Note n WHERE n.receive.id = :id OR n.send.id = :id ORDER BY n.createdAt DESC")
    Page<Note> findByReceiveIdOrSendIdOrderByCreatedAtDesc(@Param("id") Long id, Pageable pageable);

    Page<Note> findByReceiveIdAndIsReadFalseOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findByReceiveIdOrderByCreatedAtDesc(Long id, Pageable pageable);

    Page<Note> findBySendIdOrderByCreatedAtDesc(Long id, Pageable pageable);

}
