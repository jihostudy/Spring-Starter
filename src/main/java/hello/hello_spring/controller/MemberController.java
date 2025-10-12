package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.hello_spring.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

    @GetMapping(value = "/members/new")
    public String createForm() {
      return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String createMemberForm(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member: " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }
  
}
