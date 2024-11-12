package inhatc.cse.sangsushop.cart.entity;


import inhatc.cse.sangsushop.item.entity.Item;
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
public class CartItem {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO increment
    @Column(name = "cart_item_id")   //컬럼명 변경
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)  // cartitem:cart = M:1 이고 이쪽이 M이니까 이렇게 함
    @JoinColumn(name = "cart_id") // 조인할 컬럼
    private Cart cart;  // 현재는 단방향으로 DB그림에선 양방향이지만 자바상에선 카트아이템은 카트접근되지만, 카트에선 카트아이템으로 갈수없음

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;  // 물품수량


}
