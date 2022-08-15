package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor  // 스프링 부트의 데이터 jPA는 Autowired로 DI로 일관성있게 작성 가능함.
public class MemberRepository {

    // @PersistenceContext // jpa의 엔티티 매니저(관리자)를 주입
    private final EntityManager em;
    // @PersistenceUnit // 매니저 팩토리를 주입

    public void save(Member member){
        em.persist(member);  // 영속성 컨텍스트에 멤버 객체를 넣고, 커밋하는 시점에 insert커리가 날라감
    }

    public Member findOne(Long id){
        // 단건 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        // jpql 차이 : from 의 대상이 엔티티 = 엔티티 객체를 기준으로 조회(SQL은 table 기준)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }


    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
