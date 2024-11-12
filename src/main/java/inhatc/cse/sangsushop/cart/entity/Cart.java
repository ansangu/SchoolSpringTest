package inhatc.cse.sangsushop.cart.entity;


import inhatc.cse.sangsushop.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO increment
    @Column(name = "cart_id")   //컬럼명 변경
    private long id;

    @OneToOne(fetch = FetchType.LAZY)   // 연관관계 (1:1), 괄호없어도 되지만 쓸데없이 성능잡는게 있으니 필요한 fetch만 가져옴
    @JoinColumn(name = "member_id") // 조인할 컬럼
    private Member member;  //멤버 테이블과 연결


}
