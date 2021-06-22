package br.com.rangood.pdv.rangoodpdvordermanagementservice.repository;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.Order;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderEventRepository extends JpaRepository<OrderEvent, UUID> {
}
