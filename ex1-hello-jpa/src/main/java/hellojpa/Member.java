package hellojpa;

import javax.persistence.*;


@Entity
//@Table(name = "USER")
public class Member {

    @Id
    private String id;

    @Column(name = "user", nullable = false)
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Member() {
    }
}
