package com.chacon.materiales.dao;

import com.chacon.materiales.dto.MaterialDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MaterialesDao {
    private final static String NOMBRE_ARCHIVO = "materiales.json";
    private Path archivo;
    private Gson gson;

    public MaterialesDao() {
        this.archivo = Path.of(System.getProperty("user.home"), NOMBRE_ARCHIVO);
        System.out.println("MaterialesDao archivo " + this.archivo);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public ArrayList<MaterialDto> obtenerTodo() {
        try {
            String datos = Files.readString(this.archivo);
            Type tipoLista = new TypeToken<ArrayList<MaterialDto>>() {}.getType();
            ArrayList<MaterialDto> listado = this.gson.fromJson(datos, tipoLista);
            if (listado == null) {
                return new ArrayList<>();
            }
            return listado;
        } catch (IOException e) {
            System.out.println("MaterialesDao obtenerMateriales: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public void guardarTodo(ArrayList<MaterialDto> materiales) {
        try {
            String datos = this.gson.toJson(materiales);
            Files.writeString(this.archivo, datos);
        } catch (IOException e){
            System.out.println("MaterialesDao guardarTodo: " + e.getMessage());
        }
    }
}