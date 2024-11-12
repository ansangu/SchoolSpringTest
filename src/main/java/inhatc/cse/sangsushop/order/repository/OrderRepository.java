package inhatc.cse.sangsushop.order.repository;

import inhatc.cse.sangsushop.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
