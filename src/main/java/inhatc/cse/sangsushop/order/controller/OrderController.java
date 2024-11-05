package inhatc.cse.sangsushop.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/orders")
    public String orderList(){
        return "order/list";
    }
}