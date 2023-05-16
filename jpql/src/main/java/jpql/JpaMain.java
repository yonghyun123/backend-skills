package jpql;


import javax.persistence.*;
import java.util.List;

import static jpql.MemberType.ADMIN;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //주의
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀A");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("회원1" );
            member.setAge(10);
            member.changeTeam(teamA);
            member.setMemberType(ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("회원2" );
            member2.setAge(10);
            member2.changeTeam(teamA);
            member2.setMemberType(ADMIN);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3" );
            member3.setAge(10);
            member3.changeTeam(teamB);
            member3.setMemberType(ADMIN);

            em.persist(member3);

            em.flush();
            em.clear();
            //flush 가 호출됨. commit 시점, query가 나가는 시점
            String query = "select m from Member m where m = :member";
            Member findMember = em.createQuery(query, Member.class)
                    .setParameter("member", member)
                    .getSingleResult();

            System.out.println("findMember = " + findMember);

            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "회원1")
                    .getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }


            //fetch join을 사용하지 않았다면??
            //member.getTeam을 호출하는 순간 지연로딩으로 쿼리가 날아감
            // member1 -> 쿼리
            // member2 -> 1차캐쉬
            // member3 -> 쿼리
            // 회원이 100명이라면? -> N+1 문제 발생


//            TypedQuery<Member> queryMember = em.createQuery("select m from Member m", Member.class);
//            Query query = em.createQuery("select m.username, m.age from Member m", String.class);
//
//            List<Member> resultList = queryMember.getResultList();

//            TypedQuery<Member> queryMember2 = em.createQuery("select m from Member m where m.username = :username", Member.class);
//            queryMember2.setParameter("username", "nono2");
//            Member singleResult = queryMember2.getSingleResult();
//            System.out.println("singleResult = " + singleResult);
//
//            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
//
//            for (MemberDTO memberDTO : resultList) {
//                System.out.println("memberDTO = " + memberDTO);
//            }

//            String query = "select m.username, TRUE, 'HELLO' from Member m " +
//                    "where m.memberType = jpql.MemberType.USER";
//            List<Object[]> resultList1 = em.createQuery(query)
//                    .getResultList();
//
//            for (Object[] objects : resultList1) {
//                System.out.println("objects = " + objects[0]);
//                System.out.println("objects = " + objects[1]);
//                System.out.println("objects = " + objects[2]);
//            }
//
//            String query2 = "select case when m.age <= 10 then '학생요금' " +
//                    "                   when m.age >=60 then '경로요금' " +
//                    "                   else '일반요금' end " +
//                    "from Member m";
//            List<String> resultList = em.createQuery(query2, String.class).getResultList();
//            for (String s : resultList) {
//                System.out.println("s = " + s);
//            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
