package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.productDTO;
import com.makeupp.makeupp.model.product;
import com.makeupp.makeupp.model.category;
import com.makeupp.makeupp.repository.Iproduct;
import com.makeupp.makeupp.repository.Icategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productService {

    @Autowired
    private Iproduct data;

    @Autowired
    private Icategory categoryRepository;

    public List<product> findAll() {
        return data.findAll();
    }

    public Optional<product> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteProduct(int id) {
        if (!findById(id).isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El producto no existe"
            );
        }
        data.deleteById(id);
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Producto eliminado correctamente"
        );
    }

    public responseDTO save(productDTO productDTO) {
        Optional<category> category = categoryRepository.findById(productDTO.getCategoryId());
        if (!category.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La categoría no existe"
            );
        }
        
        product productRegister = convertToModel(productDTO, category.get());
        data.save(productRegister);
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Producto guardado correctamente"
        );
    }

    public responseDTO updateProduct(int id, productDTO productDTO) {
        Optional<product> optionalProduct = data.findById(id);
        if (!optionalProduct.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El producto no existe");
        }
    
        product existingProduct = optionalProduct.get();
        
        // Verificar si se envió una nueva categoría, si no, mantener la actual
        Optional<category> category = categoryRepository.findById(productDTO.getCategoryId());
        if (productDTO.getCategoryId() != 0 && !category.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La categoría no existe");
        }
    
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStock(productDTO.getStock());
        if (productDTO.getCategoryId() != 0) {
            existingProduct.setCategory(category.get());
        }
        existingProduct.setImage(productDTO.getImage());
    
        data.save(existingProduct);
    
        return new responseDTO(HttpStatus.OK.toString(), "Producto actualizado correctamente");
    }
    

    public productDTO convertToDTO(product product) {
        return new productDTO(
                product.getProduct_id(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getCategory_id(),
                product.getImage()
        );
    }

    public product convertToModel(productDTO productDTO, category category) {
        return new product(
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getStock(),
                category,
                productDTO.getImage()
        );
    }
}
