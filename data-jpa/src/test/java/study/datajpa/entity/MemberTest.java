package study.datajpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamA);
        Member member3 = new Member("member3", 10, teamB);
        Member member4 = new Member("member4", 10, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("memberTeam = " + member.getTeam());
        }
    }

    @Test
    public void auditing() throws Exception {
        Member member = new Member("member1", 10);
        memberRepository.save(member);

        Thread.sleep(1000);

        member.setUserName("test");

        em.flush();
        em.clear();

        Member member1 = memberRepository.findById(member.getId()).get();
        System.out.println("member1 = " + member1.getCreatedDate());
        System.out.println("member1 = " + member1.getLastModifiedDate());
        System.out.println("member1 = " + member1.getCreatedBy());
        System.out.println("member1 = " + member1.getLastModifiedBy());

    }
}