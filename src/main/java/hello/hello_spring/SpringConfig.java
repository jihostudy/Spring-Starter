package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;

@Configuration
public class SpringConfig {

  private final DataSource dataSource;
  private final EntityManager em;


  public SpringConfig(DataSource dataSource, EntityManager em) {
    this.dataSource = dataSource;
    this.em = em;
  }


    @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  // 직접 스프링빈으로 등록하는 경우, Repository를 변경하기 쉽다 ("교안의 상황에 따라 구현 클래스를 변경해야 하는 경우가 이에해당")
  @Bean
  public MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//    return new JdbcMemberRepository(dataSource);
//      return new JdbcTemplateMemberRepository(dataSource);
      return new JpaMemberRepository(em);
  }

}
