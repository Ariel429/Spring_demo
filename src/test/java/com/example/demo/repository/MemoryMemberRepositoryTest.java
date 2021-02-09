package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional로 저장한 데이터를 get()로 꺼냄(좋은 방법은 아니지만, 테스트이므로)
        Member result = repository.findById(member.getId()).get();

        // 결과 출력으로 확인
        // System.out.println("result = " + (result == member));

        // jupiter Assertions 사용 -> expected와 actual을 비교
        // Assertions.assertEquals(member, result);

        // Assertions.assertThat()에서 alt + enter로 static import하기
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // Shift + F6 변수 Rename 가능!
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // ctrl + alt + v 로 선언 가능!
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
