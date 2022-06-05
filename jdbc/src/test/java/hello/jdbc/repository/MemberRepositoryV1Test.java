package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
        //기본 DriverManager를 이용한 커넥션 획득
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
//        repository = new MemberRepositoryV1(dataSource);

        //커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new MemberRepositoryV1(dataSource);
    }


    @Test
    void crud() throws SQLException {
        Member memberV0 = new Member("memberV0", 10000);
//        repository.save(memberV0);

        //findById
        Member findMember = repository.findById(memberV0.getMemberId());
        log.info("findMember = {} ", findMember);
        Assertions.assertThat(findMember).isEqualTo(memberV0);

        //update 10000 -> 20000
        repository.update(memberV0.getMemberId(), 20000);
        Member updatedMember = repository.findById(memberV0.getMemberId());
        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(20000);

        //delete 검증
        Assertions.assertThatThrownBy(() -> repository.findById("memberV1"))
                .isInstanceOf(NoSuchElementException.class);

    }

}