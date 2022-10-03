package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "USER")
public class Member {
    @Id
    private Long id;
    @Column(name = "name")
    private String userName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;

    public Member() {
    }

}
