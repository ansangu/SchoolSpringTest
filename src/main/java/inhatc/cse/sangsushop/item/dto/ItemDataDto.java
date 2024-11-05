package inhatc.cse.sangsushop.item.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDataDto {
    private String name;
    private int age;
    private String dept;
    private int grade;

}
