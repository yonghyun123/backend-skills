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
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testMember() {
        Member memberA = new Member("memberA");
        Member savedMember = memberRepository.save(memberA);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(memberA.getId());
        Assertions.assertThat(findMember.getUserName()).isEqualTo(memberA.getUserName());
        Assertions.assertThat(findMember).isEqualTo(memberA);
    }
    @Test
    public void basicCrud() {
        Member member1 = new Member("Member1");
        Member member2 = new Member("Member2");

        memberRepository.save(member1);
        memberRepository.save(member2);


        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        Assertions.assertThat(member1).isEqualTo(findMember1);
        Assertions.assertThat(member2).isEqualTo(findMember2);

        List<Member> all = memberRepository.findAll();

        Assertions.assertThat(all.size()).isEqualTo(2);

        long count = memberRepository.count();

        Assertions.assertThat(count).isEqualTo(2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);


    }
}