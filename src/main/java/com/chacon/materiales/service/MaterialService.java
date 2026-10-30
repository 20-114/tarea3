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

    private boolean evaluarExistenciaId(Long id){
        ArrayList<MaterialDto> lista = this.leerTodos();
        for (MaterialDto material : lista) {
            if (id.equals(material.getId())) {
                return false;
            }
        }
        return true;
    }
    public Long contadorId() {
        return System.currentTimeMillis();
    }
    @Override
    public void crear(MaterialDto material) {
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        listado.add(material);
        this.dao.guardarTodo(listado);
    }

    @Override
    public ArrayList<MaterialDto> leerTodos() {
        return this.dao.obtenerTodo();
    }

    @Override
    public MaterialDto leerPorId(Long id) {
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
    public void actualizar(MaterialDto material) {
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        for (MaterialDto mat: listado) {
            if (mat.getId().equals(material.getId())) {
                mat.setNombre(material.getNombre());
            }
        }
        this.dao.guardarTodo(listado);
    }

    @Override
    public void actualizar(Long id, MaterialDto materialDto) {
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        for (MaterialDto mat: listado) {
            if (mat.getId().equals(id)) {
                mat.setNombre(materialDto.getNombre());
            }
        }
        this.dao.guardarTodo(listado);
    }

    @Override
    public void eliminar(Long id) {
            ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
            ArrayList<MaterialDto> listaNueva = new ArrayList<>();
            for (MaterialDto mat : listado) {
                //if (!evaluarExistenciaId(id)) {
                    if (!mat.getId().equals(id)) {
                        listaNueva.add(mat);
                    }
                //}
            }
            this.dao.guardarTodo(listaNueva);
    }

    @Override
    public void eliminar(MaterialDto material) {
        ArrayList<MaterialDto> listado = this.dao.obtenerTodo();
        for (MaterialDto mat: listado) {
            if (mat.getId().equals(material.getId())) {
                listado.remove(mat);
            }
        }
        this.dao.guardarTodo(listado);
    }

    @Override
    public MaterialDto buscarPorParteDelNombre(String nombre) {
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
    public MaterialDto buscarPorNombreExacto(String nombre) {
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