/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.PantallaSalida;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Fede
 */
public class LOOK implements Estrategia{

    @Override
    public void ejecutar(Disco disco, ArrayList<Integer> peticiones, PantallaSalida pantallaSalida) {
        //Variables de salida
        Float retardoRotacional = 60 / disco.getVelocidadRotacionRPM().floatValue() * 1000 / 2;
        Float tiempoTransferencia1Bloque = disco.getTiempoTransferenciaUnSector() * disco.getTamañoBloque();
        Float tiempoAccesoTotal = new Float(0);
        //Variables que se usan para salida
        Integer cantidadMovimientosDelBrazo = 0;
        Integer cantidadBloquesLeidos = peticiones.size();
        
        //Para registrar el orden en que se atendieron las peticiones
        ArrayList<Integer> peticionesCumplidas = new ArrayList<Integer>();
        //Por cada i de peticionesCumplidas, hay una distancia en la posicion i dentro distanciasParcialesPorPeticionCumplida
        ArrayList<Integer> distanciasParcialesPorPeticionCumplida = new ArrayList<Integer>();
        //Para registrar el orden del numero de peticion
        ArrayList<Integer> ordenNroPeticion = new ArrayList<Integer>();
        
        //Variables usadas por el algoritmo
        Integer posicionCabezal = disco.getPosicionCabeza();
        Integer distanciaTotal = 0;
        Integer distanciaParcial;
        
        //Paso la lista original a una auxiliar para poder trabajar con la auxiliar
        ArrayList<Integer> peticionesAux = (ArrayList<Integer>)peticiones.clone();
        
        //Ordeno la lista para poder ir barriendo y todos los que estan desde la pista posicionCabezal hasta la ultima pista
        Collections.sort(peticionesAux);
        
        
        
        
        //Comienza el algoritmo de planificacion
        while(peticionesAux.isEmpty() == false){
            //si el sentido es creciente impieza en el primer if, si no en el segundo
            if(disco.getSentido() == 'C'){
                //i sirve para saber desde que posicion de la lista de peticiones se tiene que empezar a servir
                int i = 0;
                if(posicionCabezal == (disco.getTotalPistas()- 1)){
                    i = posicionCabezal;
                }else{
                    while((i < peticionesAux.size()) && (posicionCabezal > peticionesAux.get(i))){
                        i++;
                    }
                }
                
                //Se recorre cada pista sirviendo peticiones
                for(int j = i; j < peticionesAux.size(); j++){
                    distanciaParcial = posicionCabezal - peticionesAux.get(j);
                    ordenNroPeticion.add(peticiones.indexOf(peticionesAux.get(j)));
                    if (distanciaParcial < 0){
                        distanciaParcial = distanciaParcial * -1;
                    }
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(peticionesAux.get(j));
                    posicionCabezal = peticionesAux.get(j);
                }
                
                //Se eliminan las peticiones servidas de la lista auxiliar
                while(i <= peticionesAux.size() -1 ){
                    peticionesAux.remove(i);
                }
                
                //Cambio el sentido en que debe ir el cabezal
                Character sentidoAux = 'D';
                disco.setSentido(sentidoAux);
                
                
            //Entra en caso de que el sentido sea drecresciente
            }else{
                //i sirve para saber desde que posicion de la lista de peticiones se tiene que empezar a servir
                int i = 0;
                while((peticionesAux.get(i) < posicionCabezal) && (i != peticionesAux.size() - 1)){
                    i++;
                }
                if(peticionesAux.get(i) > posicionCabezal){
                    i--;
                }
                
                //Se recorre cada pista sirviendo peticiones
                for (int j = i; j >= 0; j--) {
                    distanciaParcial = posicionCabezal - peticionesAux.get(j);
                    ordenNroPeticion.add(peticiones.indexOf(peticionesAux.get(j)));
                    if (distanciaParcial < 0){
                        distanciaParcial = distanciaParcial * -1;
                    }
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(peticionesAux.get(j));
                    posicionCabezal = peticionesAux.get(j);
                }
                
                //Se eliminan las peticiones servidas de la lista auxiliar
                int j = 0;
                while(j <= i){
                    peticionesAux.remove(0);
                    j++;
                }
                
                //Cambio el sentido en que debe ir el cabezal
                Character sentidoAux = 'C';
                disco.setSentido(sentidoAux);
            }
        }
        
        tiempoAccesoTotal = (distanciaTotal * disco.getSeekTimeMedio()) + (retardoRotacional + tiempoTransferencia1Bloque) * cantidadBloquesLeidos;
 
        pantallaSalida.meterTexto("Para algoritmo LOOK\n");
        pantallaSalida.meterTexto("    Cadena de peticiones que simulo: " + peticiones + "\n");
        pantallaSalida.meterTexto("    Nro Peticion + Pista servida + distancia parcial recorrida hasta ese pista\n");
        Integer j=0;
        for(int i=0; i < peticionesCumplidas.size(); i++){
            pantallaSalida.meterTexto("        Nro." + ordenNroPeticion.get(i) + "  Pista:" + peticionesCumplidas.get(i) + " --> " + distanciasParcialesPorPeticionCumplida.get(i) + "\n");
        }
        pantallaSalida.meterTexto("    Distancia Total: " + distanciaTotal + "\n");
        pantallaSalida.meterTexto("    Retardo Rotacional: " + retardoRotacional + "ms\n");
        pantallaSalida.meterTexto("    Tiempo de transferencia de 1 bloque: " + tiempoTransferencia1Bloque + "ms\n");
        pantallaSalida.meterTexto("    Calculos para realizar el Tiempo de Acceso Total:\n");       
        pantallaSalida.meterTexto("        Cantidad de SKm (movimientos del brazo: " + cantidadMovimientosDelBrazo + "\n");
        pantallaSalida.meterTexto("        Cantidad de rr: " + cantidadBloquesLeidos + "\n");
        pantallaSalida.meterTexto("        Tiempo de Acceso Total: " + tiempoAccesoTotal + "ms\n");
        pantallaSalida.meterTexto("\n");
        pantallaSalida.meterTexto("------------------------------------------------------------------\n");
        pantallaSalida.meterTexto("\n");
    }
    
}
