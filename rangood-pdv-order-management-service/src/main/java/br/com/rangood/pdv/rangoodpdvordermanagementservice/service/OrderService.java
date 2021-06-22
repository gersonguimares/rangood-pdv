package br.com.rangood.pdv.rangoodpdvordermanagementservice.service;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.Order;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.repository.OrderRepository;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.service.exceptions.EntityAlreadyExistException;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.service.exceptions.NotFoundEntityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order addOrder(final Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getlAll() {
        return orderRepository.findAll();
    }

    public Order findById(UUID orderId) {
        final Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            return null;
        }
    }

    public boolean isExist(UUID orderId) {
        final Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.isPresent();
    }

    public boolean deleteById(UUID orderId) throws NotFoundEntityException {

        if(!this.isExist(orderId)) {
            throw new NotFoundEntityException();
        }

        try {
            orderRepository.deleteById(orderId);
            return true;
        } catch (DataAccessException ex) {
            return false;
        }
    }

    public Order update(Order order) throws NotFoundEntityException, EntityAlreadyExistException {

        if(this.isExist(order.getId())) {
            try {
                return orderRepository.save(order);
            } catch (DataIntegrityViolationException e) { throw new EntityAlreadyExistException(e);}
        } else {
            throw new NotFoundEntityException();
        }
    }

}
