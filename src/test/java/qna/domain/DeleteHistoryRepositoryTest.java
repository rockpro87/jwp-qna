package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class DeleteHistoryRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    DeleteHistoryRepository deleteHistoryRepository;

    @Test
    void save() {
        User testDeletedBy = userRepository.save(UserTest.ROCKPRO87);
        Question testQuestion = questionRepository.save(QuestionTest.Q1.writeBy(testDeletedBy));
        DeleteHistory expected = new DeleteHistory(
                ContentType.QUESTION,
                testQuestion.getId(),
                testDeletedBy,
                null);
        DeleteHistory actual = deleteHistoryRepository.save(expected);
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getContentId()).isEqualTo(expected.getContentId())
        );
    }
}
