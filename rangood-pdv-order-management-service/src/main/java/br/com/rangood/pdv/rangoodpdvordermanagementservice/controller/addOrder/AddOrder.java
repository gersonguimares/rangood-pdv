package br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.addOrder;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.RestErrorHandler;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.ValidationErrorHandler;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.expections.UnavailableServiceFeignClientException;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.productservice.Product;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.Order;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.OrderEvent;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.OrderItem;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.service.OrderService;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class AddOrder {

    private final OrderService orderService;
    private final ProductService productService;
    @Autowired
    public AddOrder(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity execute(@RequestBody @Valid final AddOrderRequestModel addOrderRequestModel, final BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ValidationErrorHandler.fromBindingErrors(bindingResult));
        }

        if (addOrderRequestModel.getItens().isEmpty())
            return RestErrorHandler.ResponseEntityErrorBuilder
                    .badRequest(new String[]{"itens", "The item list can not empty"});

        List<Product> productList = getProductListInService();
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal orderAmount = new BigDecimal("0.0");
        for (OrderItemRequestModel item : addOrderRequestModel.getItens()) {

            //Verify product uuid
            Product product = verifyProduct(productList, item.getProductId());
            if(product == null) {
                return RestErrorHandler.ResponseEntityErrorBuilder
                        .conflited("ID_PRODUCT_NOT_FOUND",
                                new String[]{"productId", item.getProductId().toString()});
            }

            //If the quantity is not greater than 0
            if(item.getQuantity() < 1) {
                return RestErrorHandler.ResponseEntityErrorBuilder
                        .conflited("ITEM_QUANTITY_INVALID",
                                new String[]{"quantity", item.getProductId().toString()});
            }

            //Put product in order
            orderItems.add(new OrderItem(product.getPrice(), item.getQuantity(), product.getId()));
            BigDecimal itemAmount = product.getPrice().multiply(new BigDecimal(item.getQuantity()));
            //Add product price at total amount
            orderAmount = orderAmount.add(itemAmount);

        }

        //Register the Order
        final LocalDateTime localDateTime = LocalDateTime.now();
        Order order = new Order(String.valueOf((new Random()).nextLong()), localDateTime);
        order.setRequesterId(addOrderRequestModel.getCustomerId());

        OrderEvent orderEvent = new OrderEvent(OrderEvent.Type.REQUESTED.toString(),"",localDateTime);
        order.addOrderEvent(orderEvent);
        order.setItens(orderItems);
        order.setStatus(OrderEvent.Type.REQUESTED.toString());
        orderService.addOrder(order);

        AddOrderResponseModel addOrderResponseModel = new AddOrderResponseModel();
        addOrderResponseModel.setOrderAmount(orderAmount);
        addOrderResponseModel.setItens(orderItems);
        addOrderResponseModel.setOrderNumber(order.getNumberOrder());
        addOrderResponseModel.setId(order.getId());

        return ResponseEntity.ok(addOrderResponseModel);
    }

    private List<Product> getProductListInService() {

        try {
            return productService.getAllProducts();
        } catch (UnavailableServiceFeignClientException e) {
            return null;
        }
    }

    private Product verifyProduct(List<Product> productList, UUID productId) {

        for (Product p : productList) {
            if(p.getId().toString().equals(productId.toString())) {
                return p;
            }
        }
        return null;

    }
}
