package com.chacon.materiales.dao;

import com.chacon.materiales.dto.MaterialDto;
import java.util.ArrayList;

public interface IMaterialesDao {
    public ArrayList<MaterialDto> obtenerTodo();

    public void guardarTodo(ArrayList<MaterialDto> materiales);
}
