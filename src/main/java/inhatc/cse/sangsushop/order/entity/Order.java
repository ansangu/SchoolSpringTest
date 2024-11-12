package inhatc.cse.sangsushop.order.entity;

import inhatc.cse.sangsushop.item.entity.Item;
import inhatc.cse.sangsushop.member.entity.Member;
import inhatc.cse.sangsushop.order.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")   // DB에는 oders로 들어가지만 자바에선 그대로 order로, DB에서 Order By로 이름충돌 일어날 수도 있어서
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO increment
    @Column(name = "order_id")   //컬럼명 변경
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 주문:멤버 = M:1
    @JoinColumn(name = "member_id")
    private Member member;
    
    private LocalDateTime orderDate;    // 요새는 시간데이터 이거 씀
    
    @Enumerated(EnumType.STRING)    // 이넘타입은 스트링
    private OrderStatus orderStatus;



}
