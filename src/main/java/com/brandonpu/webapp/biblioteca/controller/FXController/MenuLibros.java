package com.brandonpu.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brandonpu.webapp.biblioteca.model.Categoria;
import com.brandonpu.webapp.biblioteca.model.Libro;
import com.brandonpu.webapp.biblioteca.service.LibroService;
import com.brandonpu.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class MenuLibros implements Initializable{

    @FXML
    TextField tfId,tfIsbn,tfNombre,tfAutor,tfEditorial,tfDisponibilidad,tfNumeroEstanteria,tfCluster;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar,btnBuscar, btnRegresar;
    @FXML
    TableView tblLibros;
    @FXML
    TableColumn colId, colIsbn,colNombre,colSinopsis,colAutor,colEditorial,colDisponibilidad,colNumeroEstanteria,colCluster,colCategoria;
    @FXML
    TextArea tfSinopsis;
    @FXML
    ComboBox cmbCategoria;

    @Setter
    private Main stage;

    @Autowired
    LibroService libroService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){

    }

    public ObservableList<Libro> listaLibros(){
        return FXCollections.observableList(libroService.listarLibros());
    }

    public void cargarDatos(){
        tblLibros.setItems(listaLibros());
        colId.setCellValueFactory(new PropertyValueFactory<Libro,Long>("id"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<Libro,String>("isbn"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Libro,String>("nombre"));
        colSinopsis.setCellValueFactory(new PropertyValueFactory<Libro,String>("sinopsis"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Libro,String>("autor"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<Libro,String>("editorial"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<Libro,String>("disponibilidad"));
        colNumeroEstanteria.setCellValueFactory(new PropertyValueFactory<Libro,String>("numeroEstanteria"));
        colCluster.setCellValueFactory(new PropertyValueFactory<Libro,String>("cluster"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<Libro,Categoria>("categoria"));
        
    }
}
