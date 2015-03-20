package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map;
import modelo.RedBayesiana;
import smile.SMILEException;
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
    
    public Map<Double, String> calcularProbabilidad() throws SMILEException{

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
                
                Map<Double, String> results = null;
                try{
                results = calcularProbabilidad();
                }catch(SMILEException er){
                    vista.addRowLog(er.getMessage());
                }
   
                if(results!= null){
                    DecimalFormat df = new DecimalFormat("0.0000"); 
                    results.entrySet().stream().forEach((row) -> {
                        vista.addRowResults(new Object[]{row.getValue(),df.format(row.getKey())});
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
