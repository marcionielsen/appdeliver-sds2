package br.com.nielsen.appdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nielsen.appdeliver.dto.OrderDTO;
import br.com.nielsen.appdeliver.entities.Order;
import br.com.nielsen.appdeliver.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> lista = orderRepository.findOrdersWithProducts();
		
		return lista.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
}
