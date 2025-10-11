package com.properties.EGI_Properties.service;
import com.properties.EGI_Properties.entity.Ubicacion;

import java.util.List;
public interface IUbicacionService {
    Ubicacion getUbicacionById(Integer id);
    List<Ubicacion> getAllUbicaciones();
    String saveUbicacion(Ubicacion ubicacion);
    String updateUbicacion(Integer id, Ubicacion ubicacion);
    String deleteUbicacion(Integer id);
}
