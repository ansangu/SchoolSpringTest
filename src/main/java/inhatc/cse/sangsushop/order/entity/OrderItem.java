package inhatc.cse.sangsushop.order.entity;

import inhatc.cse.sangsushop.item.entity.Item;
import inhatc.cse.sangsushop.member.entity.Member;
import inhatc.cse.sangsushop.order.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO increment
    @Column(name = "order_item_id")   //컬럼명 변경
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 주문아이템:아이템 = M:1
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)  // 주문아이템:아이템 = M:1
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;

    private int count;
    



}
