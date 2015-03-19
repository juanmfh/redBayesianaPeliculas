package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import vista.VistaPrincipal;

/**
 *
 * @author juan
 */
public class ControladorPrincipal implements ActionListener {
    
    private final VistaPrincipal vista;
    
    public ControladorPrincipal(VistaPrincipal vista){
        this.vista = vista;
    }
    
    public Map<String, Double> calcularProbabilidad(){
        // Datos de prueba (Sustituir por llamada al modelo)
        Map<String, Double> map = new HashMap();
        map.put("El señor de los anillos", 0.8);
        map.put("Matrix", 0.7);
        map.put("Seven", 0.6);
        map.put("Star wars", 0.5);
        map.put("Braveheart", 0.4);
        map.put("Armagedon", 0.3);
        map.put("Jurasic park", 0.2);
        map.put("Batman", 0.1);
        map.put("Spiderman", 0.05);
        map.put("Blancanieves", 0.01);
        
        return map;
        // LLamada al modelo para calcular la probabilidad a partir de los datos de la vista
        /* return modelo.calcularProbabilidad(
                vista.getYear(),
                vista.getLenght(),
                vista.getBudget(),
                vista.getRating(),
                vista.getGenres());*/
        
        //public Map<String, Double> calcularProbabilidad(String year, String length, String budget,String rating, List<String> genres);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "apply":
                vista.clearResults();
                Map<String, Double> results = calcularProbabilidad();
                if(results!= null){
                    results.entrySet().stream().forEach((row) -> {
                        vista.addRowResults(new Object[]{row.getKey(), row.getValue()});
                    });
                    if(results.size()>0){
                        vista.addRowLog("La película recomendada es " + results.entrySet().iterator().next().getKey() ); 
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
