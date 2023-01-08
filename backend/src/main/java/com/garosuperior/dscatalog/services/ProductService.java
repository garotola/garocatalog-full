package com.garosuperior.dscatalog.services;

import com.garosuperior.dscatalog.dto.ProductDTO;
import com.garosuperior.dscatalog.entities.Category;
import com.garosuperior.dscatalog.entities.Product;
import com.garosuperior.dscatalog.repositories.CategoryRepository;
import com.garosuperior.dscatalog.repositories.ProductRepository;
import com.garosuperior.dscatalog.services.exceptions.DataBaseException;
import com.garosuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    ProductRepository repository;
    @Autowired
    CategoryRepository categoryRepository;
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<ProductDTO> productDTOList = repository.findAll(pageRequest)
                .map(ProductDTO::new);
        return productDTOList;
    }
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        return new ProductDTO(product, product.getCategories());
    }
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        copyProductDtoToProduct(dto, product);
        product = repository.save(product);
        return new ProductDTO(product);
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        copyProductDtoToProduct(dto, product);
        product = repository.save(product);
        return new ProductDTO(product);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException err) {
            throw new ResourceNotFoundException("Id not found " + id);
        }catch (DataIntegrityViolationException err){
            throw new DataBaseException("Integrity violation");
        }
    }

    private void copyProductDtoToProduct(ProductDTO productDTO, Product product){
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setDate(productDTO.getDate());
        product.setImgUrl(productDTO.getImgUrl());

        product.getCategories().clear();
        productDTO.getCategories().forEach(categoryDTO -> {
            Category category = categoryRepository.findById(categoryDTO.getId()).get();
            product.getCategories().add(category);
        });
    }
}
