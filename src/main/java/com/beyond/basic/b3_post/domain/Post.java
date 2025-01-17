package com.beyond.basic.b3_post.domain;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b3_post.dtos.PostDetailDto;
import com.beyond.basic.b3_post.dtos.PostListDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
//Builder어노테이션을 사용하여, 빌더패턴으로 엔티티의 생성자를 구성
//빌더패턴의 장점: 매개변수의 순서와 개수를 유연학 세팅할 수 있다.
@Builder
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String contents;
//    n:1관계를 말함
//    LAZY로 설정시 member객체를 사용하지 않는 한, memeber테이블로 쿼리 발생하지 않음
//    이에 반해 EAGER(즉시로딩)타입으로 설정시 사용하지 않아도 member테이블로 쿼리 발생
    @ManyToOne(fetch = FetchType.LAZY)//ManyToOne에서는 default가 EAGER
//    member가 db에서 어떤 컬럼으로 들어가는지 정해줌
    @JoinColumn(name = "member_id")
    private Member member;

    public PostListDto toListDto(){
        return PostListDto.builder().id(this.id).title(this.title).build();
    }
//    post테이블에서 member
    public PostDetailDto toDetailDto(String email){
        return PostDetailDto.builder().id(this.id).title(this.title).contents(this.contents).memberEmail(email).build();
    }

    public PostListDto listFromEntity(){
        PostListDto postListDto = new PostListDto(this.id,this.title);
        return postListDto;
    }
}
