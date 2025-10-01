package hello.hello_spring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.hello_spring.domain.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository {
  
  private static Map<Long,Member> store = new HashMap<>();
  private static long sequence = 0L;

  // 저장
  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  // ID조회
  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }  

  // 이름조회
  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
      .filter(member -> member.getName().equals(name))
      .findAny();
  }

  // 모두 조회
  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  // 전체 삭제
  @Override
  public void clearStore() {
    store.clear();
  }

  
}
