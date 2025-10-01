package hello.hello_spring.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.a÷pi.Assertions;
import org.junit.jupiter.api.Test;

import hello.hello_spring.domain.Member;

class MemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  public void beforeEach() {
    repository.clearStore(); // 각 테스트 실행 전에 메모리 초기화
  }

  @Test
  @DisplayName("회원 저장 테스트")
  public void save() {
    Member member = new Member();
    member.setName("spring");
    repository.save(member);
    Member result = repository.findById(member.getId()).get();
    
    // Assertions.assertEquals(member, result); // junit Test
    Assertions.assertThat(member).as("저장된 회원과 조회된 회원이 같은지 확인").isEqualTo(result);
  }

  @Test
  @DisplayName("ID로 회원 조회 테스트")
  public void findById() {
    Member member = new Member();
    member.setName("spring");
    repository.save(member);
    Member result = repository.findById(member.getId()).get();
    Assertions.assertThat(result).as("ID로 조회한 회원이 저장된 회원과 같은지 확인").isEqualTo(member);
  }

  @Test
  @DisplayName("이름으로 회원 조회 테스트")
  public void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result = repository.findByName("spring1").get();
    Assertions.assertThat(result).as("이름으로 조회한 회원이 올바른 회원인지 확인").isEqualTo(member1);
  }

  @Test
  @DisplayName("전체 회원 조회 테스트")
  public void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);
    
    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    List<Member> result = repository.findAll();
    Assertions.assertThat(result).as("조회된 리스트에 저장된 모든 회원이 포함되어 있는지 확인").contains(member1,member2);
    Assertions.assertThat(result.size()).as("크기 테스트").isEqualTo(2);
  }
}
