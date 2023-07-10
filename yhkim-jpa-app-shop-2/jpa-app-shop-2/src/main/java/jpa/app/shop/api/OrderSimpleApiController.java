package jpa.app.shop.api;

import static java.util.stream.Collectors.toList;

import java.util.List;
import jpa.app.shop.domain.Order;
import jpa.app.shop.repository.OrderRepository;
import jpa.app.shop.repository.OrderSearch;
import jpa.app.shop.repository.OrderSimpleQueryDto;
import jpa.app.shop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * XToOne (ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress(); // Lazy 강제 초기화
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<OrderSimpleQueryDto> ordersV2() {
        // ORDER 2개
        // 1 + N => ORDER 1개 + 회원 2개 + 배송 2개
        return orderRepository.findAllByString(new OrderSearch()).stream()
                .map(OrderSimpleQueryDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<OrderSimpleQueryDto> ordersV3() {
        return orderRepository.findAllWithMemberDelivery().stream()
                .map(OrderSimpleQueryDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @GetMapping("/api/v4-2/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4_2() {
        return orderSimpleQueryRepository.findOrderDtos2();
    }
}
