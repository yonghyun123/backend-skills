package jpql;


import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //주의
            Member member = new Member();
            member.setUsername("nono2");
            member.setAge(10);

            em.persist(member);
            //flush 가 호출됨. commit 시점, query가 나가는 시점

//            TypedQuery<Member> queryMember = em.createQuery("select m from Member m", Member.class);
//            Query query = em.createQuery("select m.username, m.age from Member m", String.class);
//
//            List<Member> resultList = queryMember.getResultList();

            TypedQuery<Member> queryMember2 = em.createQuery("select m from Member m where m.username = :username", Member.class);
            queryMember2.setParameter("username", "nono2");
            Member singleResult = queryMember2.getSingleResult();
            System.out.println("singleResult = " + singleResult);

            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

            for (MemberDTO memberDTO : resultList) {
                System.out.println("memberDTO = " + memberDTO);
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
