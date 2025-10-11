package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.TipoPropiedad;
import java.util.List;

public interface ITipoPropiedadService {
    TipoPropiedad getTipoPropiedadById(Integer id);
    List<TipoPropiedad> getAllTipoPropiedad();
    String saveTipoPropiedad(TipoPropiedad tipoPropiedad);
    String updateTipoPropiedad(Integer id, TipoPropiedad tipoPropiedad);
    String deleteTipoPropiedad(Integer id);
}
