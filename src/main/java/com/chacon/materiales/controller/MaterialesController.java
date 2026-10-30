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


    public void cargarTabla() throws IOException {
        ArrayList<MaterialDto> listaJsonDao = materialesService.leerTodos();
        listaObservable.setAll(listaJsonDao);
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
        try {
            String nombre = txtNombre.getText();
            if (!nombre.isEmpty()) {
                Long id = this.materialesService.contadorId();
                MaterialDto nuevoMaterial = new MaterialDto(id, nombre);
                this.materialesService.crear(nuevoMaterial);
                cargarTabla();
                txtNombre.clear();
            } else {
                throw new IOException("El campo nombre no puede estar vacio");
            }
        } catch (IOException e){
            System.out.println("MaterialesController handleAgregarMaterial: "+ e.getMessage());
        }
    }

    public void handleEliminarMaterial(ActionEvent actionEvent) throws IOException {
            if (!txtId.getText().isEmpty()) {
                Long id = Long.parseLong(txtId.getText());
                materialesService.eliminar(id);
            } else {
                this.mensaje("El campo 'id' no puede estar vacio y debe ser un numero entero");
            }

        cargarTabla();
        txtId.clear();
    }

    private void mensaje(String mensaje){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error de formulario");
        a.setHeaderText(mensaje);
        a.show();
    }

    public void handleModificarMaterial(ActionEvent actionEvent) throws Exception {
            if (!txtNombre.getText().isEmpty()) {
                MaterialDto nuevoMaterial = null;
                if (!txtId.getText().isEmpty()) {
                    Long id = Long.parseLong(txtId.getText());
                    nuevoMaterial = new MaterialDto(id, txtNombre.getText());
                    materialesService.actualizar(id, nuevoMaterial);
                }
                cargarTabla();
                txtId.clear();
                txtNombre.clear();
            } else {
                this.mensaje("El campo 'nombre' no puede setar vacio para hacer la modificación");
            }

    }

    public void handleSeleccionarMaterial(MouseEvent mouseEvent){
        MaterialDto material = (MaterialDto) this.tblMateriales.getSelectionModel().getSelectedItem();
        if (material != null) {
            //this.txtNombre.setText(material.getNombre());
            this.txtId.setText(material.getId().toString());
        }
        /*System.out.println(this.materialesService.leerTodos().size());
        for (MaterialDto material : this.materialesService.leerTodos()){
            System.out.println(material);
        }*/
    }
}
