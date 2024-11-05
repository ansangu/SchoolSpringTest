package inhatc.cse.sangsushop.item.repository;

import inhatc.cse.sangsushop.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    List<Item> findByItemNm(String itemNm);

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    List<Item> findByItemNmLike(String itemNm);

    @Query(value = "select i from Item i where i.itemDetail like %:itemDetail% " +
            "order by i.price desc")
    List<Item> findItemDetail(@Param("itemDetail") String itemDetail);


    //이건 디비버에있는 데이터라 글자 그대로 item_Detail
    @Query(value = "select * from item i where i.item_Detail like %:itemDetail% " +
            "order by i.price desc",nativeQuery = true)
    List<Item> findItemDetailNative(@Param("itemDetail") String itemDetail);


}
