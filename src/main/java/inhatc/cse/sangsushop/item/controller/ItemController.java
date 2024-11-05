package inhatc.cse.sangsushop.item.controller;

import inhatc.cse.sangsushop.item.dto.ItemDataDto;
import inhatc.cse.sangsushop.item.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    @GetMapping("/admin/item/new")
    public String itemAdd() {
        return "item/add";
    }

    @GetMapping("/admin/items")
    public String itemList() {
        return "item/list";
    }


    @GetMapping("/item/thymeleaf1")     // 브라우저는 기본으로 루트에서 시작
    public String thymeleaf1(Model model) {
        ItemDto itemDto = ItemDto.builder()
                .id(1L)
                .itemNm("상품명")
                .itemDetail("상품 설명")
                .price(1000)
                .stockNumber(100)
                .build();

        model.addAttribute("item", itemDto);
        return "item/thymeleaf1";
    }

    @GetMapping("/item/thymeleaf2")     // 브라우저는 기본으로 루트에서 시작
    public String thymeleaf2(ItemDataDto itemDataDto, Model model) {   // 그냥 날려도 상관없음
        System.out.println("======================"+itemDataDto);

        model.addAttribute("item", itemDataDto);

        return "item/thymeleaf2";
    }

    @GetMapping("/item/ex01")
    public String ex01(Model model) {
        model.addAttribute("data", "타임리프 예제");
        return "item/ex01";
    }

    @GetMapping("/item/ex02")
    public String ex02(Model model) {
        ItemDto itemDto = ItemDto.builder()
                .id(1L)
                .itemNm("상품명")
                .itemDetail("상품 설명")
                .price(1000)
                .stockNumber(100)
                .build();
        model.addAttribute("itemDto", itemDto);
        return "item/ex02";
    }


    // th:each 예제
    @GetMapping("/item/ex03")
    public String ex03(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            ItemDto itemDto = ItemDto.builder()
                    .id(1L*i)
                    .itemNm("상품명"+i)
                    .itemDetail("상품 설명"+i)
                    .price(i*1000)
                    .stockNumber(i*100)
                    .build();
            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "item/ex03";
    }


    // th:unless (ex04), switch, case (ex04-2) 예제, 짝수 홀수 구분
    @GetMapping("/item/ex04")
    public String ex04(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            ItemDto itemDto = ItemDto.builder()
                    .id(1L*i)
                    .itemNm("상품명"+i)
                    .itemDetail("상품 설명"+i)
                    .price(i*1000)
                    .stockNumber(i*100)
                    .build();
            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "item/ex04";
    }

    @GetMapping("/item/ex04-2")
    public String ex04_2(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            ItemDto itemDto = ItemDto.builder()
                    .id(1L*i)
                    .itemNm("상품명"+i)
                    .itemDetail("상품 설명"+i)
                    .price(i*1000)
                    .stockNumber(i*100)
                    .build();
            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "item/ex04-2";
    }


    // th:href
    @GetMapping("/item/ex05")
    public String ex05() {
        return "item/ex05";
    }

    @GetMapping("/item/ex06")       // 근데 이러면 매개값이 없으면 들어갈수가 없다
    public String ex06(String p1,String p2,Model model) {
        System.out.println(p1 + ", " + p2);
        model.addAttribute("p1", p1);
        model.addAttribute("p2", p2);
        return "item/ex06";
    }












}
