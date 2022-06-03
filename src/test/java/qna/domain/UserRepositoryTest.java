package qna.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qna.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void clean(){
        userRepository.deleteAll();
    }

    @Test
    void save() {
        User expected = UserTest.ROCKPRO87;
        User actual = userRepository.save(expected);
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getUserId()).isEqualTo(expected.getUserId())
        );
    }

    @Test
    void findByUserId() {
        userRepository.save(UserTest.JAVAJIGI);
        String expected = UserTest.JAVAJIGI.getUserId();
        User actual = userRepository.findByUserId(expected).orElseThrow(NotFoundException::new);
        assertThat(actual.getUserId()).isEqualTo(expected);
    }

}
