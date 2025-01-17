package com.beyond.basic.b3_post.repository;

import com.beyond.basic.b3_post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
//    post객체에는 email컬럼이 없어서 findByEmail 못함
}
