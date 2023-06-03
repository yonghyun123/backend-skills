package study.datajpa.repository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

public class MemberSpec {
    public static Specification<Member> teamName(final String teamName) {
        return (Specification<Member>) (root, query, builder) -> {
            if (!StringUtils.hasLength(teamName)) {
                return null;
            }
            Join<Member, Team> t = root.join("team", JoinType.INNER);
            return builder.equal(t.get("name"), teamName);
        };
    }

    public static Specification<Member> userName(final String userName) {
        return (Specification<Member>) (root, query, builder) -> {
            return builder.equal(root.get("userName"), userName);
        };
    }
}
