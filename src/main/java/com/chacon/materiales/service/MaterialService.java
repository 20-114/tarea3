package com.chacon.materiales.service;

import com.chacon.materiales.dao.MaterialesDao;
import com.chacon.materiales.dto.MaterialDto;

import java.io.IOException;
import java.util.ArrayList;

public class MaterialService implements IMaterialesService {

    MaterialesDao dao;

    public MaterialService() {
        this.dao = new MaterialesDao();
    }

    @Override
    public void crear(MaterialDto material) throws IOException {
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        listado.add(material);
        this.dao.guardarTodo(listado);
    }

    @Override
    public ArrayList<MaterialDto> leerTodos() throws IOException {
        return this.dao.obtenerTodo();
    }

    @Override
    public MaterialDto leerPorId(Long id) throws IOException {
        MaterialDto encontrado = null;
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        for (MaterialDto mat: listado) {
            if (mat.getId().equals(id)) {
                encontrado = mat;
            }
        }
        return encontrado;
    }

    @Override
    public void actualizar(MaterialDto material) throws IOException {
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        for (MaterialDto mat: listado) {
            if (mat.getId().equals(material.getId())) {
                mat.setNombre(material.getNombre());
            }
        }
        this.dao.guardarTodo(listado);
    }

    @Override
    public void actualizar(Long id, MaterialDto materialDto) throws IOException {
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        for (MaterialDto mat: listado) {
            if (mat.getId().equals(id)) {
                mat.setNombre(materialDto.getNombre());
            }
        }
        this.dao.guardarTodo(listado);
    }

    @Override
    public void eliminar(Long id) throws IOException {
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        ArrayList<MaterialDto> listaNueva = new ArrayList<>();
        for (MaterialDto mat: listado) {
            if (!mat.getId().equals(id)) {
                listaNueva.add(mat);
            }
        }
        this.dao.guardarTodo(listaNueva);
    }

    @Override
    public void eliminar(MaterialDto material) throws IOException {
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        for (MaterialDto mat: listado) {
            if (mat.getId().equals(material.getId())) {
                listado.remove(mat);
            }
        }
        this.dao.guardarTodo(listado);
    }

    @Override
    public MaterialDto buscarPorParteDelNombre(String nombre) throws IOException {
        MaterialDto encontrado = null;
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        for (MaterialDto mat: listado) {
            if (mat.getNombre().contains(nombre)) {
                encontrado = mat;
            }
        }
        return encontrado;
    }

    @Override
    public MaterialDto buscarPorNombreExacto(String nombre) throws IOException {
        MaterialDto encontrado = null;
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        for (MaterialDto mat: listado) {
            if (mat.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = mat;
            }
        }
        return encontrado;
    }
}