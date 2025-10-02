package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;

class MemberServiceTest {
  
  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  @DisplayName("회원가입")
  public void join() {
    // Given
    Member member = new Member();
    member.setName("hello");

    // When
    memberService.join(member);

    // Then
    Member findMember = memberService.findOne(member.getId()).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }
  
  @Test
  @DisplayName("중복 회원 예외")
  public void joinDuplicateMember() {
    // Given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    // When + Then
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }

  @Test
  @DisplayName("전체 회원 조회")
  public void findMembers() {
    // Given
    Member member1 = new Member();
    member1.setName("spring1");
    memberService.join(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    memberService.join(member2);

    // When
    List<Member> result = memberService.findMembers();
    assertThat(result.size()).isEqualTo(2);
    assertThat(result).contains(member1, member2);
  }
}
