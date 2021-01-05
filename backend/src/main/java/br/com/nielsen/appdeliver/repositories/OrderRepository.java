package br.com.nielsen.appdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nielsen.appdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
