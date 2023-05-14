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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Member" );
            member.setAge(10);
            member.changeTeam(team);
            member.setMemberType(ADMIN);

            em.persist(member);

            em.flush();
            em.clear();
            //flush 가 호출됨. commit 시점, query가 나가는 시점

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

            String query = "select m.username, TRUE, 'HELLO' from Member m " +
                    "where m.memberType = jpql.MemberType.USER";
            List<Object[]> resultList1 = em.createQuery(query)
                    .getResultList();

            for (Object[] objects : resultList1) {
                System.out.println("objects = " + objects[0]);
                System.out.println("objects = " + objects[1]);
                System.out.println("objects = " + objects[2]);
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
