package study.datajpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        return memberRepository.findById(id).get().getUserName();
    }
    @GetMapping("/members2/{id}")
    public String findMembe2r(@PathVariable("id") Member member) {
        return member.getUserName();
    }

    @PostConstruct
    public void init() {
        memberRepository.save(new Member("userA"));
    }
}
