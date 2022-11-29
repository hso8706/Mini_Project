package com.example.diary.content.repository;

import com.example.diary.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}


/*
: Repository 는 interface 로 구현함.
: proxy 객체가 알아서 구현체를 만들어 사용할 수 있게 해줌.
: JpaRepository 를 사용하여 JPQL 쿼리를 사용하게 됨.
: JpaRepository 의 제네릭 인자로는 (1)Entity 객체와 (2)Entity 객체의 id 자료형을 제공
 */

/*
기본 제공 메서드
Optional<T> findById(ID id);
Page<T> findAll(Pageable pageable); // JpaRepository -> PagingAndSortingRepository의 메서드
 */