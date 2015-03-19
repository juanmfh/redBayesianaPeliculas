package main;

import controlador.ControladorPrincipal;
import java.util.ArrayList;
import java.util.List;
import modelo.RedBayesiana;
import vista.VistaPrincipal;

/**
 *
 * @author juan
 */
public class Main {
    
    static VistaPrincipal  vista;
    static ControladorPrincipal controlador;
    static RedBayesiana modelo;
    
    public static void main(String args[]) {   
        
        java.awt.EventQueue.invokeLater(() -> {
            
            vista = new VistaPrincipal();
            controlador = new ControladorPrincipal(vista);
            vista.setControlador(controlador);
            vista.setVisible(true);
        });  
        
    }   
}
