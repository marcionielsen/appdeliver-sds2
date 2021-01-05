package br.com.nielsen.appdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.nielsen.appdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("select distinct ord "
			+ " from Order ord  "
			+ "      join fetch ord.products  "
			+ " where ord.status = 0 "
			+ " order by ord.moment asc ")
	List<Order> findOrdersWithProducts();
}
