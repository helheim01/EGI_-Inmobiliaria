package com.properties.EGI_Properties.repository;

import com.properties.EGI_Properties.entity.TipoPropiedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTipoPropiedad extends JpaRepository<TipoPropiedad, Integer> {
}
