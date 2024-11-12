package inhatc.cse.sangsushop.cart.repository;

import inhatc.cse.sangsushop.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> { // 두번쨰는 Cart의 기본키의 타입인 Long



}
