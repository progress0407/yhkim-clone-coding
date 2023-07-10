package jpa.app.shop.repository.order.simplequery;

import java.util.List;
import javax.persistence.EntityManager;
import jpa.app.shop.repository.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                        "select new jpa.app.shop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)"
                                +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }

    public List<OrderSimpleQueryDto> findOrderDtos2() {
        return em.createQuery(
                        "select new jpa.app.shop.repository.order.simplequery.OrderSimpleQueryDto(o) from Order o" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
