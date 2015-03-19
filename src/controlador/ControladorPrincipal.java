package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import modelo.RedBayesiana;
import vista.VistaPrincipal;

/**
 *
 * @author juan
 */
public class ControladorPrincipal implements ActionListener {
    
    private final VistaPrincipal vista;
    private final RedBayesiana modelo;
    
    public ControladorPrincipal(VistaPrincipal vista){
        this.vista = vista;
        this.modelo = new RedBayesiana();
    }
    
    public Map<Double, String> calcularProbabilidad(){

        return modelo.calcularProbabilidad(
                vista.getYear(),
                vista.getLenght(),
                vista.getBudget(),
                vista.getRating(),
                vista.getGenres());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "apply":
                vista.clearResults();
                Map<Double, String> results = calcularProbabilidad();
                if(results!= null){
                    results.entrySet().stream().forEach((row) -> {
                        vista.addRowResults(new Object[]{row.getValue(), row.getKey()});
                    });
                    if(results.size()>0){
                        vista.addRowLog("La película recomendada es: " + results.entrySet().iterator().next().getValue() ); 
                    }
                }else{
                    vista.addRowLog("No se ha encontrado ningún resultado");
                }
                break;
            case "clear":
                vista.clearForm();
                vista.clearLog();
                vista.clearResults();
                break;
        }
    }
    
    
    
}
