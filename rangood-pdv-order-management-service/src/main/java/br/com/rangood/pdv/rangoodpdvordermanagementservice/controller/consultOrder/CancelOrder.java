package br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.consultOrder;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.RestErrorHandler;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.Order;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.OrderEvent;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.service.OrderService;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class CancelOrder {

    private final OrderService orderService;
    private final ProductService productService;
    @Autowired
    public CancelOrder(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/{orderId}/cancel")
    public ResponseEntity orderStatus(@PathVariable UUID orderId){

        Order order = orderService.findById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        if(!order.getStatus().equals(OrderEvent.Type.REQUESTED.toString())) {
            return RestErrorHandler.ResponseEntityErrorBuilder
                .conflited("ORDER_NOT_CANCELABLE",new String[]{"This order do not cancelable",""});
        }

        OrderEvent event = new OrderEvent(OrderEvent.Type.CANCELED.toString(),"", LocalDateTime.now());
        order.setStatus(OrderEvent.Type.CANCELED.toString());
        order.getEvents().add(event);

        order = orderService.addOrder(order);

        return ResponseEntity.ok(order);

    }


}
