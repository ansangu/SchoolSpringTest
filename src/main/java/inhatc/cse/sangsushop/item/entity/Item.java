package inhatc.cse.sangsushop.item.entity;
// DB를 만들어보자


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO increment
    @Column(name = "item_id")   //컬럼명 변경
    private long id;

    @Column(nullable = false, length = 50)  //설정
    private String itemNm;

    private int price;

    @Column(name = "stock")
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

}
