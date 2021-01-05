package br.com.nielsen.appdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nielsen.appdeliver.dto.ProductDTO;
import br.com.nielsen.appdeliver.entities.Product;
import br.com.nielsen.appdeliver.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> lista = productRepository.findAllByOrderByNameAsc();
		
		return lista.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
}
