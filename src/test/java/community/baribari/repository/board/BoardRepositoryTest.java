package community.baribari.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import community.baribari.entity.board.Category;
import community.baribari.entity.board.FreeBoard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository<FreeBoard> boardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory;

    @BeforeEach
    void setUp() {
        queryFactory = new JPAQueryFactory(entityManager);

        for (int i =0; i<4; i++){
            FreeBoard freeBoard = FreeBoard.builder()
                    .title("Title " + i)
                    .content("Content " + i)
                    .category(Category.FREE)
                    .build();
            boardRepository.save(freeBoard);
        }

    }

    @Test
    void mainList() {

        List<FreeBoard> freeBoards = boardRepository.mainList(Category.FREE);
        for (FreeBoard freeBoard : freeBoards) {
            System.out.println("freeBoard = " + freeBoard.getTitle());
        }

    }
}