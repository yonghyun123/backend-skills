package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntityManager em;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long id = memberService.join(member);
        //then
        em.flush();
        Assertions.assertThat(member).isEqualTo(memberRepository.findMember(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 중복회원예외() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim1");
        Member member2 = new Member();
        member2.setName("kim1");

        memberService.join(member);
        memberService.join(member2);// exception case

    }

}