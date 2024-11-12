package inhatc.cse.sangsushop.cart.repository;

import inhatc.cse.sangsushop.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
