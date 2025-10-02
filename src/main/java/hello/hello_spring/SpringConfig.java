package hello.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;

@Configuration
public class SpringConfig {
  
  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  // 직접 스프링빈으로 등록하는 경우, Repository를 변경하기 쉽다 ("교안의 상황에 따라 구현 클래스를 변경해야 하는 경우가 이에 해당")
  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
