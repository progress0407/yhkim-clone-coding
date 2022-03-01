package jpa.app.shop.repository;

import java.time.LocalDateTime;
import jpa.app.shop.domain.Address;
import jpa.app.shop.domain.Order;
import jpa.app.shop.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;

    public OrderSimpleQueryDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName(); // LAZY 초기화
        orderDate = order.getOrderDate();
        status = order.getStatus();
        address = order.getDelivery().getAddress(); // LAZY 초기화
    }

    public OrderSimpleQueryDto(
            Long orderId,
            String name,
            LocalDateTime orderDate,
            OrderStatus status,
            Address address) {

        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.status = status;
        this.address = address;
    }


}
