package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.categoryDTO;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.model.category;
import com.makeupp.makeupp.repository.Icategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class categoryService {
    
    @Autowired
    private Icategory data;

    public List<category> findAll() {
        return data.findAll();
    }

    public Optional<category> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteCategory(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "The register does not exist"
            );
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se eliminó correctamente"
        );
        return respuesta;
    }

    public responseDTO save(categoryDTO categoryDTO) {
        if (categoryDTO.getName().length() < 1 || categoryDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El nombre debe estar entre 1 y 50 caracteres"
            );
            return respuesta;
        }
        
        category categoryRegister = convertToModel(categoryDTO);
        data.save(categoryRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
        return respuesta;
    }

    public categoryDTO convertToDTO(category category) {
        return new categoryDTO(
                category.getCategory_id(),
                category.getName()
        );
    }

    public category convertToModel(categoryDTO categoryDTO) {
        return new category(
                categoryDTO.getCategoryId(),
                categoryDTO.getName()
        );
    }
}
