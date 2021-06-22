package br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.consultOrder;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.RestErrorHandler;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.ValidationErrorHandler;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.addOrder.AddOrderRequestModel;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.addOrder.AddOrderResponseModel;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.addOrder.OrderItemRequestModel;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.expections.UnavailableServiceFeignClientException;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.productservice.Product;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.Order;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.OrderEvent;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.OrderItem;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.service.OrderService;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/order")
public class ConsultOrder {

    private final OrderService orderService;
    private final ProductService productService;
    @Autowired
    public ConsultOrder(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/{orderId}/status")
    public ResponseEntity orderStatus(@PathVariable UUID orderId){

        Order order = orderService.findById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        final Map<String, String> r = new HashMap<>();
        r.put("status", order.getStatus());
        return ResponseEntity.ok(r);

    }

    @PatchMapping ("/{orderId}/status/{status}")
    public ResponseEntity setOrderStatus(@PathVariable UUID orderId, @PathVariable String status){

        String resolvedEventStatus = null;
        try {
           final OrderEvent.Type ot =  OrderEvent.Type.valueOf(status);
           resolvedEventStatus = ot.toString();
        } catch (IllegalArgumentException e) {
            return RestErrorHandler.ResponseEntityErrorBuilder
                    .badRequest(new String[]{"status", "Invalid status"});
        }

        Order order = orderService.findById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        OrderEvent event = new OrderEvent(resolvedEventStatus,"", LocalDateTime.now());
        order.setStatus(resolvedEventStatus);
        order.getEvents().add(event);

        order = orderService.addOrder(order);

        return ResponseEntity.ok(order);


    }

    @GetMapping("/{orderId}")
    public ResponseEntity order(@PathVariable UUID orderId){

        Order order = orderService.findById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);

    }

}
