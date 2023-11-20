/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.ArrayList;

/**
 *
 * @author Fede
 */
public class Controlador {
    ArrayList<Integer> peticiones = new ArrayList<Integer>();
    
    public Controlador(){
        
    }
    
    public ArrayList<Integer> obtenerListaPeticiones(){
        return peticiones;
    }
    
    public void agregarPeticion(Integer peticion){
        peticiones.add(peticion);
    }
    
    public void eliminarTodasLasPeticiones(){
        peticiones.clear();
    }
}
