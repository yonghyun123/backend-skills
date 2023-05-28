package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

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

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> aaa = memberRepository.findByUserNameAndAgeGreaterThan("AAA", 15);
        Assertions.assertThat(aaa.size()).isEqualTo(1);

    }

    @Test
    public void findByUsername() {
        Member aaa = new Member("AAA", 10);
        Member bbb = new Member("BBB", 10);
        memberRepository.save(aaa);
        memberRepository.save(bbb);

        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);

        Assertions.assertThat(findMember).isEqualTo(aaa);
    }

    @Test
    public void findUser() {
        Member aaa = new Member("AAA", 10);
        memberRepository.save(aaa);

        List<Member> result = memberRepository.findUser("AAA", 10);
        Member findMember = result.get(0);

        Assertions.assertThat(findMember).isEqualTo(aaa);
    }
    @Test
    public void findUsernameList() {
        Member aaa = new Member("AAA", 10);
        Member bbb = new Member("BBB", 10);
        memberRepository.save(aaa);
        memberRepository.save(bbb);

        List<String> username = memberRepository.findUsername();
        for (String s : username) {
            System.out.println("s = " + s);
        }
    }
    @Test
    public void findMemberDto() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member aaa = new Member("AAA", 10);
        aaa.setTeam(team);
        memberRepository.save(aaa);


        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void findByNames() {
        Member aaa = new Member("AAA", 10);
        Member bbb = new Member("BBB", 10);
        memberRepository.save(aaa);
        memberRepository.save(bbb);

        List<Member> byNames = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member byName : byNames) {
            System.out.println("byName = " + byName);
        }
    }
    @Test
    public void returnType() {
        Member aaa = new Member("AAA", 10);
        Member bbb = new Member("BBB", 10);
        memberRepository.save(aaa);
        memberRepository.save(bbb);

        List<Member> byNames = memberRepository.findListByUserName("AAA");
        Member member = memberRepository.findMemberByUserName("AAA");
        Optional<Member> optional = memberRepository.findOptionalByUserName("AAA");


        List<Member> result = memberRepository.findListByUserName("asdfasdfasdf");//없는 리스트 반환
        //result 는 null 아님!!!
        Member findMember = memberRepository.findMemberByUserName("asdfasfsadf");
        //findMember 는 null !!!
        Optional<Member> optionalMember = memberRepository.findOptionalByUserName("asdfasdfsadf");
        //대부분 이걸로 처리함

//        for (Member byName : byNames) {
//            System.out.println("byName = " + byName);
//        }
    }

    @Test
    public void paging() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));
        memberRepository.save(new Member("member6", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.DEFAULT_DIRECTION.DESC, "userName"));

        Page<Member> byAge = memberRepository.findByAge(age, pageRequest);

        List<Member> content = byAge.getContent();
        for (Member member : content) {
            System.out.println("member = " + member);
        }

        long totalElements = byAge.getTotalElements();
        System.out.println("totalElements = " + totalElements);

        Page<MemberDto> toMap = byAge.map(v -> new MemberDto(v.getId(), v.getUserName(), null));


        Assertions.assertThat(content.size()).isEqualTo(3);
        Assertions.assertThat(byAge.getTotalElements()).isEqualTo(6);
        Assertions.assertThat(byAge.getNumber()).isEqualTo(0);
        Assertions.assertThat(byAge.getTotalPages()).isEqualTo(2);
        Assertions.assertThat(byAge.isFirst()).isTrue();
        Assertions.assertThat(byAge.hasNext()).isTrue();


    }
}