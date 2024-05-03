package jpa.realjpa.repository;

import jakarta.persistence.criteria.*;
import jpa.realjpa.entity.Member;
import jpa.realjpa.entity.Team;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class MemberSpec {
    public static Specification<Member> teamName(final String teamName) {
        return (root, query, builder) -> {

            if (StringUtils.isEmpty(teamName)) {
                return null;
            }
            Join<Member, Team> t = root.join("team", JoinType.INNER);// 회원과 조인
            return builder.equal(t.get("name"), teamName);
        };
    }
    public static Specification<Member> username(final String username){
        return (root, query, builder) ->
            builder.equal(root.get("username"), username);
    }
}
