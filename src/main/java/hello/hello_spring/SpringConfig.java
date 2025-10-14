package hello.hello_spring;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    // JpaRepository를 extends하는 interface를 구현해두면 자동으로 DI
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
    return new MemberService(memberRepository);
  }

// NOTE: AOP는 따로 Config에 등록해두어서 확인가능하게 하는 것이 더 좋다고 함
  //    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

    //  private final DataSource dataSource;
    //  private final EntityManager em;
    //
    //
    //  public SpringConfig(DataSource dataSource, EntityManager em) {
    //    this.dataSource = dataSource;
    //    this.em = em;
    //  }

    // 직접 스프링빈으로 등록하는 경우, Repository를 변경하기 쉽다 ("교안의 상황에 따라 구현 클래스를 변경해야 하는 경우가 이에해당")
    //  @Bean
    //  public MemberRepository memberRepository() {
    //    return new MemoryMemberRepository();
    //    return new JdbcMemberRepository(dataSource);
    //      return new JdbcTemplateMemberRepository(dataSource);
    //      return new JpaMemberRepository(em);
    //  }

}
