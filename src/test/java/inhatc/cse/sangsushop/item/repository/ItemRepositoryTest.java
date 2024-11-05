package inhatc.cse.sangsushop.item.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.cse.sangsushop.item.entity.Item;
import inhatc.cse.sangsushop.item.entity.QItem;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static inhatc.cse.sangsushop.item.entity.QItem.item;

@SpringBootTest
//@Transactional    // 트랜젝션으로 오류걸리면 시작 상태로 되돌림
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;


    //@PersistenceContext
    @Autowired
    private EntityManager em;


    @Test
    public void test(){
        System.out.println("AAAAAAAAAAAA");
    }

    @Test
    @DisplayName("상품 등록 테스트")
    public void  insertItemTest(){
        Item item = Item.builder()
                .itemNm("신상품2")
                .itemDetail("신상품 상세설명2")
                .price(20000)
                .stockNumber(200)
                .build();

        Item saveItem =  itemRepository.save(item);
        System.out.println(saveItem);
    }

    @Test
    @DisplayName("상품 등록 테스트")
    public void  createItemTest(){
        for (int i = 0; i < 10; i++) {
            Item item = Item.builder()
                    .itemNm("신상품"+i)
                    .itemDetail("신상품 상세설명"+i)
                    .price(10000*i)
                    .stockNumber(100*i)
                    .build();

            Item saveItem =  itemRepository.save(item);
            System.out.println(saveItem);
        }
    }

    @Test
    public void findByItemNmTest(){
        List<Item> itemList = itemRepository.findByItemNm("신상품2");
        itemList.forEach(System.out::println);
    }

    @Test
    void findByPriceLessThanOOrderByPriceDesc() {
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(35000);
        itemList.forEach(System.out::println);
    }

    @Test
    public void findByItemNmLike(){
        List<Item> itemList = itemRepository.findByItemNmLike("%2%");
        itemList.forEach(System.out::println);
    }

    @Test
    public void findItemDetailNative(){
        List<Item> itemList = itemRepository.findItemDetailNative("3");
        itemList.forEach(System.out::println);
    }


    @Test
    @DisplayName("querydsl 테스트")
    public void querydslTest(){
        JPAQueryFactory query = new JPAQueryFactory(em);    // 엔티티매니저 필요

        List<Item> itemList = query.selectFrom(item)
                .where(item.itemNm.eq("신상품2"))
                .where(item.itemDetail.like("%"+"신상품"+"%"))
                .orderBy(item.price.desc())
                .fetch();

        itemList.forEach(System.out::println);
    }

    @Test
    @DisplayName("querydsl 테스트2")
    public void querydslTest2(){
        BooleanBuilder builder = new BooleanBuilder();
        String itemDetail = "설명";
        int price = 10;
        String ItemName = "신상품2";

        QItem item = QItem.item;

        builder.and(item.itemDetail.like("%"+itemDetail+"%"));  // 내용중 "테스트"
        builder.and(item.price.gt(price));  // 값이 10004보다 크게

        // Enum할때 쓰임
//        if (StringUtils.equals(ItemName,item.itemNm)){  // 아이템이름이 "신상품2"
//            builder.and(item.itemNm.eq(item.itemDetail));
//        }

        Pageable pageable = PageRequest.of(0,5);
        // 시작 0번부터 5개씩 페이지 만듬
        // 0을 1로 바꾸면 그 다음페이지 내용이 출력


        Page<Item> page = itemRepository.findAll(builder, pageable);
        List<Item> content = page.getContent();
        content.stream().forEach(System.out::println);
    }
}