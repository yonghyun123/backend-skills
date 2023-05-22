package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userName;

    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    protected Member() {

    }

    public Member(String userName) {
        this.userName = userName;
    }
}
