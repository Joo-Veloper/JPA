package com.jpabook.jpashop.service.query;

import com.jpabook.jpashop.api.OrderApiController;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderQueryService {
    private final OrderRepository orderRepository;

    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();

//        for (Order order : orders) {
//            System.out.println("Order = " + order + "id = " + order.getId());
//        }
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());
        return result;
    }
}
