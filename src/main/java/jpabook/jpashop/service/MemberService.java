package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;

    /*
     * 회원 가입
     * */
    @Transactional(readOnly = false)
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId(); // db에 들어가지 않아도 영속성 값을 저장할 때 @Id @GeneratedValue를 항상 id값이 보장되므로 컨텍스트에 pk에도 값을 저장함(보장함)
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName()); // 멀티 쓰레드를 고려하여, 파라미터에 유니크 제약조건으로 잡아줄것
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    // 회원 전체 조회
    // @Transactional(readOnly = true)  // 읽기 전용 트랜잭션임을 알려줘 리소스를 많이 쓰지 않게 되어 최적화?
    public List<Member> findMember(){
        return memberRepository.findAll();
    }
    
    // 단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
