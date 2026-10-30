package com.chacon.materiales.controller;

import com.chacon.materiales.dto.MaterialDto;
import com.chacon.materiales.service.IMaterialesService;
import com.chacon.materiales.service.MaterialService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class MaterialesController {
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtId;
    @FXML
    public Button btnAgregar;
    @FXML
    public Button btnEliminar;
    @FXML
    public Button btnModificar;
    @FXML
    public TableColumn colId;
    @FXML
    public TableColumn colNombre;
    @FXML
    public TableView tblMateriales;

    public IMaterialesService materialesService;
    public ObservableList listaObservable;

    public void initialize() throws IOException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        listaObservable = FXCollections.observableArrayList();
        tblMateriales.setItems(listaObservable);
        cargarTabla();
    }

    public MaterialesController(){
        this.materialesService = new MaterialService();
    }

    public boolean evaluarExistenciaId(Long id) throws IOException {
        ArrayList<MaterialDto> lista = this.materialesService.leerTodos();
        for (MaterialDto material : lista) {
            if (id.equals(material.getId())) {
                return false;
            }
        }
        return true;
    }
    public void cargarTabla() throws IOException {
        ArrayList<MaterialDto> listaJsonDao = materialesService.leerTodos();
        listaObservable.setAll(listaJsonDao);
    }
    public Long contadorId() throws IOException {
        Long id = 0L;
        while (true){
            id += 1;
            if (evaluarExistenciaId(id)){
                return id;
            };
        }
    }
    public MaterialDto buscarMaterialPorId(Long id) throws Exception {
        MaterialDto materialBuscado = null;
        ArrayList<MaterialDto> lista = materialesService.leerTodos();
        for (MaterialDto material : lista) {
            if (id.equals(material.getId())) {
                materialBuscado = material;
            }
        }
        if (materialBuscado != null) {
            return materialBuscado;
        } else {
            throw new Exception("El material no existe en la base de datos");
        }
    }

    public void handleAgregarMaterial(ActionEvent actionEvent) throws IOException {
        String nombre = txtNombre.getText();
        Long id = contadorId();
        MaterialDto nuevoMaterial = new MaterialDto(id, nombre);
        this.materialesService.crear(nuevoMaterial);
        cargarTabla();
        txtNombre.clear();
    }

    public void handleEliminarMaterial(ActionEvent actionEvent) throws IOException {
        if (txtId.getText() != null){
            Long id = Long.parseLong(txtId.getText());
            if (!evaluarExistenciaId(id)){
                materialesService.eliminar(id);
            }
        }
        cargarTabla();
        txtId.clear();
    }

    public void handleModificarMaterial(ActionEvent actionEvent) throws Exception {
        MaterialDto nuevoMaterial = null;
        if (txtId.getText() != null) {
            Long id = Long.parseLong(txtId.getText());
            if (!evaluarExistenciaId(id)) {
                nuevoMaterial = new MaterialDto(id, txtNombre.getText());
                materialesService.actualizar(id, nuevoMaterial);
            }
        }
        cargarTabla();
        txtId.clear();
        txtNombre.clear();
    }

    public void handleSeleccionarMaterial(MouseEvent mouseEvent) throws IOException {
        System.out.println(this.materialesService.leerTodos().size());
        for (MaterialDto material : this.materialesService.leerTodos()){
            System.out.println(material);
        }
    }
}
