package jpabook.jpashop;


import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            List<Member> resultList = em.createQuery("select m from Member m where m.name like 'kim%'", Member.class).getResultList();
            System.out.println("resultList = " + resultList);

            //주의
            Member member = new Member();
            member.setName("nono");
            em.persist(member);
            //flush 가 호출됨. commit 시점, query가 나가는 시점

            List resultList1 = em.createNativeQuery("select * from member ", Member.class).getResultList();

            System.out.println("resultList1 = " + resultList1);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
