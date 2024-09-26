package community.baribari.repository.note.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import community.baribari.entity.note.Note;
import community.baribari.entity.note.QNote;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoteRepositoryCustomImpl implements NoteRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Note> findByReceiveIdOrSendIdAndTitleOrContentContainingOrderByCreatedAtDesc(Long id, String keyword, Pageable pageable) {
        List<Note> fetch = jpaQueryFactory.selectFrom(QNote.note)
                .where(QNote.note.receive.id.eq(id).or(QNote.note.send.id.eq(id))
                        .and(QNote.note.title.contains(keyword).or(QNote.note.content.contains(keyword))))
                .orderBy(QNote.note.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(fetch, pageable, fetch.size());
    }
}
