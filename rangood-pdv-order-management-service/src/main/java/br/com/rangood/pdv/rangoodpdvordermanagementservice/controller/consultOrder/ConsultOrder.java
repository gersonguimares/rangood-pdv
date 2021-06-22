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

    @GetMapping("/{orderId}")
    public ResponseEntity order(@PathVariable UUID orderId){

        Order order = orderService.findById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);

    }

}
