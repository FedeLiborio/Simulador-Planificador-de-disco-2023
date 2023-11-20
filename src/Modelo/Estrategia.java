package Modelo;

import Vista.PantallaSalida;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Fede
 */
public interface Estrategia {
    public void ejecutar(Disco disco, ArrayList<Integer> peticiones, PantallaSalida pantallaSalida);
}
