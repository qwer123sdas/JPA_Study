package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // JUnit실행 할 때, 스프링이랑 함께 엮어서 실행하겠다는 의미
@SpringBootTest // 스프링 컨테이너에서 테스트를 돌리기 위함(DI)
@Transactional // 테스트 케이스에선 롤백하기 위한 트랜잭션
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository  memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        // em.flush(); //영속성 컨텍스으에 있는 내용을 db에 반영하는 메소드이므로 트랜잭션 어노테이션이 있어도 커밋함.
        // 즉 db에 강제로 보냄. 그리고 그 결과를 확인할 수 있음.
        assertEquals(member, memberRepository.findOne(saveId));
     }

     @Test(expected = IllegalStateException.class)
     public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2= new Member();
        member2.setName("kim");

         //when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야 하는 코드

         /*
         (expected = IllegalStateException.class)이 대체함
         * */
        /*try{
            memberService.join(member2); // 예외가 발생해야 하는 코드
        }catch (IllegalStateException e){
            return;
        }*/

        //then
        fail("예외가 발생해야 합니다."); // 여기 오면 안됨을 표시함

      }
}