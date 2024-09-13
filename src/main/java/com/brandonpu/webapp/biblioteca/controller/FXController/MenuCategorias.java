package com.brandonpu.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brandonpu.webapp.biblioteca.model.Categoria;
import com.brandonpu.webapp.biblioteca.service.CategoriaService;
import com.brandonpu.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class MenuCategorias  implements Initializable{

    @FXML
    TextField tfId, tfNombre,tfCategoriaId;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar,btnBuscar, btnRegresar;
    @FXML
    TableView tblCategorias;
    @FXML
    TableColumn colId, colNombre;

    @Setter
    private Main stage;

    @Autowired
    CategoriaService categoriaService;
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnGuardar){
            if (tfId.getText().isBlank()) {
                agregarCategoria();
            }else {
                editarCategoria();
            }
        } else if(event.getSource() == btnLimpiar){
            limpiarFormEditar();
        } else if(event.getSource() == btnEliminar){
            eliminarCategoria();
        } else if (event.getSource() == btnBuscar){
            tblCategorias.getItems().clear();
            if (tfCategoriaId.getText().equals("")) {
                cargarDatos();
            }else{
                buscarCategoriaPorId();
            }
        } else if(event.getSource() == btnRegresar){
            stage.indexView();
        }
    }

    public void cargarDatos(){
        tblCategorias.setItems(listaCategorias());
        colId.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombreCategoria"));
    }

    public void cargarFormEditar(){
        Categoria categoria = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
        if(categoria != null){
            tfId.setText(Long.toString(categoria.getId()));
            tfNombre.setText(categoria.getNombreCategoria());
        }
    }
    
    public void limpiarFormEditar(){
        tfId.clear();
        tfNombre.clear();
    }

    public ObservableList<Categoria> listaCategorias() {
        return FXCollections.observableList(categoriaService.listarCategorias());
    }

    public void agregarCategoria(){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }   

    public void editarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void eliminarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }

    public void buscarCategoriaPorId() {
        try {
            Long id = Long.parseLong(tfCategoriaId.getText());
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            tblCategorias.getItems().clear();
            if (categoria != null) {
                tblCategorias.getItems().add(categoria);
            } else {
                System.out.println("Categoría no encontrada");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido");
        }
    }
    
}
