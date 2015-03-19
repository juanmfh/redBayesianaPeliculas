/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import smile.Network;

/**
 *
 * @author Juan A.
 */
//public Map<String, double> calcularProbabilidad(String year, String length, String budget, String rating, List<String> genres);
public class RedBayesiana {

    private final Map<String, String> titles;

    public RedBayesiana() {
        titles = new HashMap();
        titles.put("State0", "Aladdin");
        titles.put("State1", "Aliens");
        titles.put("State2", "American History X");
        titles.put("State3", "Antz");
        titles.put("State4", "Austin Powers: International Man of Mystery");
        titles.put("State5", "Batman: Dead End");
        titles.put("State6", "Big Fish");
        titles.put("State7", "Billy Elliot");
        titles.put("State8", "Braveheart");
        titles.put("State9", "Chocolat");
        titles.put("State10", "Cowboy Bebop: Tengoku no tobira");
        titles.put("State11", "Dark City");
        titles.put("State12", "El Diablo nunca duerme");
        titles.put("State13", "Die Hard");
        titles.put("State14", "Dragon Ball Z 9: Ginga girigiri!! Butchigiri no sugoi yatsu");
        titles.put("State15", "Dreamer: The Movie");
        titles.put("State16", "Entre todas las mujeres");
        titles.put("State17", "Fight Club");
        titles.put("State18", "Fuga de cerebros");
        titles.put("State19", "Gladiator");
        titles.put("State20", "Harry Potter and the Chamber of Secrets");
        titles.put("State21", "Harry Potter and the Prisoner of Azkaban");
        titles.put("State22", "Harry Potter and the Sorcerer's Stone");
        titles.put("State23", "Harry un ami qui vous veut du bien");
        titles.put("State24", "I Robot");
        titles.put("State25", "Ice Age");
        titles.put("State26", "Identity");
        titles.put("State27", "The Incredibles");
        titles.put("State28", "Initial D: Third Stage");
        titles.put("State29", "The Island");
        titles.put("State30", "Jurassic Park");
        titles.put("State31", "Kill Bill: Vol. 1");
        titles.put("State32", "Kill Bill: Vol. 2");
        titles.put("State33", "Lilo & Stitch");
        titles.put("State34", "The Lord of the Rings: The Fellowship of the Ring");
        titles.put("State35", "The Lord of the Rings: The Return of the King");
        titles.put("State36", "The Lord of the Rings: The Two Towers");
        titles.put("State37", "El Maquinista");
        titles.put("State38", "The Matrix Reloaded");
        titles.put("State39", "The Matrix");
        titles.put("State40", "Mulan");
        titles.put("State41", "Mystic River");
        titles.put("State42", "Pirates of the Caribbean: The Curse of the Black Pearl");
        titles.put("State43", "Shrek");
        titles.put("State44", "Shrek 2");
        titles.put("State45", "Sin City");
        titles.put("State46", "Spider-Man");
        titles.put("State47", "Spider-Man 2");
        titles.put("State48", "Star Trek the Experience: The Klingon Encounter");
        titles.put("State49", "Star Trek: First Contact");
        titles.put("State50", "Star Trek: New Voyages");
        titles.put("State51", "Star Wars: Episode II - Attack of the Clones");
        titles.put("State52", "Tarzan");
        titles.put("State53", "Toy Story");
        titles.put("State54", "Toy Story 2");
        titles.put("State55", "X-Men");
    }

    public Map<Double, String> calcularProbabilidad(String year, String length, String budget, String rating, List<String> genres) {
        
        Map<Double, String> res = new LinkedHashMap();
        Network net = new Network();
        
        net.readFile("series-red-rev.xdsl");
        
        // Updating the network:
        net.updateBeliefs();

        // Getting the handle of the node "TITLE":
        net.getNode("TITLE");

        // Getting the index of the "Moderate" outcome:
        String[] aSeriesOutcomeIds = net.getOutcomeIds("TITLE");
        
        if (year.length() != 0) {
            net.setEvidence("YEAR", year);
        }
        if (length.length() != 0) {
            net.setEvidence("LENGTH", length);
        }
        if (budget.length() != 0) {
            net.setEvidence("BUDGET", budget);
        }
        if (rating.length() != 0) {
            net.setEvidence("RATING", rating);
        }
        if (genres.contains("Action")) {
            net.setEvidence("ACTION", "State1");
        }
        if (genres.contains("Animation")) {
            net.setEvidence("ANIMATION", "State1");
        }
        if (genres.contains("Comedy")) {
            net.setEvidence("COMEDY", "State1");
        }
        if (genres.contains("Drama")) {
            net.setEvidence("DRAMA", "State1");
        }
        if (genres.contains("Documentary")) {
            net.setEvidence("DOCUMENTARY", "State1");
        }
        if (genres.contains("Romance")) {
            net.setEvidence("ROMANCE", "State1");
        }
        if (genres.contains("Short")) {
            net.setEvidence("SHORT", "State1");
        }

        net.updateBeliefs();

        // Mapa que almacena los pares probabilidad-indicePelicula
        SortedMap<Double, Integer> p_States = new TreeMap(Collections.reverseOrder());
        
        int i;
        for (i = 0; i < aSeriesOutcomeIds.length; i++) {
            double[] aValues = net.getNodeValue("TITLE");
            p_States.put(aValues[i], i);
        }
        
        // Seleccionamos los 10 mejores resultados y lo guardamos en el mapa res
        int j = 0;
        Iterator it = p_States.entrySet().iterator();
        if(it != null){
            while (j < 10 && it.hasNext()) {
                Map.Entry<Double, Integer> m = (Map.Entry<Double, Integer>) it.next();
                res.put(m.getKey(), titles.get("State" + (m.getValue())));
                j++;
            }
        }
        
        return res;
        
    }

}
