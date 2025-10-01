package hello.hello_spring.repository;

import java.util.List;
import java.util.Optional;

import hello.hello_spring.domain.Member;

public interface MemberRepository {
  Member save(Member member)  ; // 저장
  Optional<Member> findById(Long id); // 조회
  Optional<Member> findByName(String name); // 조회
  List<Member> findAll(); // 모두 조회
}
