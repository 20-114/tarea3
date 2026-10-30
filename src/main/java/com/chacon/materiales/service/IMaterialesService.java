package com.chacon.materiales.service;

import com.chacon.materiales.dto.MaterialDto;

import java.io.IOException;
import java.util.ArrayList;

public interface IMaterialesService {
    // C Crear, R Leer (todos, por id), U Actualizar, D Eliminar
    void crear(MaterialDto material) throws IOException;
    ArrayList<MaterialDto> leerTodos() throws IOException;
    MaterialDto leerPorId(Long id) throws IOException;
    void actualizar(MaterialDto material) throws IOException;
    void actualizar(Long id, MaterialDto materialDto) throws IOException;
    void eliminar(Long id) throws IOException;
    void eliminar(MaterialDto material) throws IOException;
    // metodos custom
    // buscar por parte del nombre
    MaterialDto buscarPorParteDelNombre(String nombre) throws IOException;
    MaterialDto buscarPorNombreExacto(String nombre) throws IOException;
}