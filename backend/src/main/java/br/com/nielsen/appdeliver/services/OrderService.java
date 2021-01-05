package br.com.nielsen.appdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nielsen.appdeliver.dto.OrderDTO;
import br.com.nielsen.appdeliver.dto.ProductDTO;
import br.com.nielsen.appdeliver.entities.Order;
import br.com.nielsen.appdeliver.entities.OrderStatus;
import br.com.nielsen.appdeliver.entities.Product;
import br.com.nielsen.appdeliver.repositories.OrderRepository;
import br.com.nielsen.appdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> lista = orderRepository.findOrdersWithProducts();
		
		return lista.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		List<ProductDTO> products = dto.getProducts();
		
		for (ProductDTO productDTO : products) {
			Product product = productRepository.getOne(productDTO.getId());
			
			order.getProducts().add(product);
		}
		
		order = orderRepository.save(order);
		
		return new OrderDTO(order);
	}
	
	
	@Transactional
	public OrderDTO setDelivered(Long id) {
		
		Order order = orderRepository.getOne(id);
		
		order.setStatus(OrderStatus.DELIVERED);
		
		order = orderRepository.save(order);
		
		return new OrderDTO(order);
	}
	

}
