package com.properties.EGI_Properties.repository;

import com.properties.EGI_Properties.entity.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPropiedad extends JpaRepository<Propiedad, Integer> {
}
