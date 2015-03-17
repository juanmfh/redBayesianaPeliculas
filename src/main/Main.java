/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            vista.setVisible(true);
        });  
        
    }   
}
