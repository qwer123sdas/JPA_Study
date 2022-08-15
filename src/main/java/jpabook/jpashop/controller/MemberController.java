package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){  // @Valid를 통해 @NotEmpty를 유효성 검사를 하게 됨
        // @Valid 뒤에 BindingResult가 있을 때, 오류가  생기면 BindingResult에 담겨서 실행된다.
        if(result.hasErrors()){
            // 만약 이름만 작성하지 않을 때라도, form 데이터도 그대로 가져가므로 다른 곳에서 작성하 데이터는 유지된다..
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        // ctr; + arlt + shift + t - > inline
        // model.addAttribute("members", memberService.findMember());
        return "members/memberList";
    }
}
