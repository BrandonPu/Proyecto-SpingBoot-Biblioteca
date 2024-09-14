package com.brandonpu.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.brandonpu.webapp.biblioteca.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.Setter;

@Component
public class IndexController implements Initializable {

    @FXML
    MenuItem btnCategorias,btnClientes,btnEmpleados;

    @Setter
    private Main stage;
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        
    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnCategorias) {
            stage.menuCategorias();
        } else if(event.getSource() == btnClientes){
            stage.menuClientes();
        } else if (event.getSource() == btnEmpleados) {
            stage.menuEmpleados();
        }
    }
    

}
