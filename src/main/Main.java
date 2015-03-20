package main;

import controlador.ControladorPrincipal;
import vista.VistaPrincipal;

/**
 *
 * @author juan
 */
public class Main {
    
    static VistaPrincipal  vista;
    static ControladorPrincipal controlador;
    
    public static void main(String args[]) {   
        
        java.awt.EventQueue.invokeLater(() -> {
            
            vista = new VistaPrincipal();
            controlador = new ControladorPrincipal(vista);
            vista.setControlador(controlador);
            vista.setVisible(true);
        });  
        
    }   
}
