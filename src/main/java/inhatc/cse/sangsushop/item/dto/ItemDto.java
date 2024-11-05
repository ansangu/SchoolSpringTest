package inhatc.cse.sangsushop.item.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private long id;            // id
    private String itemNm;      // 상품명
    private int price;          // 가격
    private int stockNumber;    // 재고 수
    private String itemDetail;  // 상품 설명



}
