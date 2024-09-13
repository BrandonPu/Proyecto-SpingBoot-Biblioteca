package com.brandonpu.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brandonpu.webapp.biblioteca.model.Cliente;
import com.brandonpu.webapp.biblioteca.service.ClienteService;
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
public class MenuClientes implements Initializable{

    @FXML
    TextField tfDpi, tfNombre,tfApellido,tfTelefono;
    @FXML
    Button btnGuardar, btnEliminar, btnLimpiar,btnBuscar, btnRegresar;
    @FXML
    TableView tblClientes;
    @FXML
    TableColumn colDpi, colNombre,colApellido,colTelefono;

    @Setter
    private Main stage;

    @Autowired
    ClienteService clienteService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnGuardar) {
            if (tfDpi.getText().isBlank()) {
                agregarCliente();
            }else{
                editarCliente();
            }
        } else if (event.getSource() == btnLimpiar) {
            limpiarFormEditar();
        } else if(event.getSource() == btnEliminar){
            eliminarCliente();
        } else if (event.getSource() == btnRegresar) {
            stage.indexView();
        }
    }

    public void cargarDatos(){
        tblClientes.setItems(listaClientes());
        colDpi.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("dpi"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
    }

    public void cargarFormEditar(){
        Cliente cliente = (Cliente) tblClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            tfDpi.setText(Long.toString(cliente.getDpi()));
            tfNombre.setText(cliente.getNombre());
            tfApellido.setText(cliente.getApellido());
            tfTelefono.setText(cliente.getTelefono());
        }
    }

    public ObservableList<Cliente> listaClientes(){
        return FXCollections.observableList(clienteService.listarClientes());
    }


    public void limpiarFormEditar(){
        tfDpi.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfTelefono.clear();
    }

    public void agregarCliente(){
        Cliente cliente = new Cliente();
        cliente.setDpi(Long.parseLong(tfDpi.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }
    
    public void editarCliente(){
        Cliente cliente = clienteService.buscarClientePorDpi(Long.parseLong(tfDpi.getText()));
        cliente.setDpi(Long.parseLong(tfDpi.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void eliminarCliente(){
        Cliente cliente = clienteService.buscarClientePorDpi(Long.parseLong(tfDpi.getText()));
        clienteService.eliminarCliente(cliente);
        cargarDatos();
    }
    
}
