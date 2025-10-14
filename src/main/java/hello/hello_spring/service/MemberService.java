package hello.hello_spring.service;

import java.util.List;
import java.util.Optional;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import jakarta.transaction.Transactional;

@Transactional
public class MemberService {

  private final MemberRepository memberRepository;

  // 의존성 주입 (DI)
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 회원 가입
   * 
   * @param member
   * @return
   */
  public Long join(Member member) {
    long start = System.currentTimeMillis();
    try {
        // 중복 회원 검사
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    finally {
        long finish = System.currentTimeMillis();
        long timeMillis = finish - start;
        System.out.printf("join time: %d ms\n", timeMillis);
    }


  }

  /**
   * 전체 회원 조회
   * 
   * @param member
   */
  public List<Member> findMembers() {
      long start = System.currentTimeMillis();
      try{
          return memberRepository.findAll();
      }finally {
          long finish = System.currentTimeMillis();
          long timeMillis = finish - start;
          System.out.printf("join time: %d ms\n", timeMillis);
      }

  }

  /**
   * 특정 회원 조회
   * 
   * @param member
   */
  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }

  // 중복 회원 검사 메서드
  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }
}