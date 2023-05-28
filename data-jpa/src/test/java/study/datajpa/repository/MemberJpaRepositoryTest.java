package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() {
        Member member = new Member("MemberA");
        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(savedMember.getId());

        Assertions.assertThat(findMember.getId()).isEqualTo(savedMember.getId());
        Assertions.assertThat(findMember.getUserName()).isEqualTo(savedMember.getUserName());
        Assertions.assertThat(findMember).isEqualTo(member); //같은 트랜잭션이라 성공
    }

    @Test
    public void basicCrud() {
        Member member1 = new Member("Member1");
        Member member2 = new Member("Member2");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);


        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();

        Assertions.assertThat(member1).isEqualTo(findMember1);
        Assertions.assertThat(member2).isEqualTo(findMember2);

        List<Member> all = memberJpaRepository.findAll();

        Assertions.assertThat(all.size()).isEqualTo(2);

        long count = memberJpaRepository.count();

        Assertions.assertThat(count).isEqualTo(2);

        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);


    }

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> aaa = memberJpaRepository.findByUsernameAndAgeGreaterThen("AAA", 15);
        Assertions.assertThat(aaa.size()).isEqualTo(1);

    }

    @Test
    public void findByUsername() {
        Member aaa = new Member("AAA", 10);
        Member bbb = new Member("BBB", 10);
        memberJpaRepository.save(aaa);
        memberJpaRepository.save(bbb);

        List<Member> result = memberJpaRepository.findByUsername("AAA");
        Member findMember = result.get(0);

        Assertions.assertThat(findMember).isEqualTo(aaa);
    }

    @Test
    public void paging() {
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 10));
        memberJpaRepository.save(new Member("member3", 10));
        memberJpaRepository.save(new Member("member4", 10));
        memberJpaRepository.save(new Member("member5", 10));
        memberJpaRepository.save(new Member("member6", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
        Long totalCount = memberJpaRepository.totalCount(age);

        Assertions.assertThat(members.size()).isEqualTo(3);
        Assertions.assertThat(totalCount).isEqualTo(6);
    }


}