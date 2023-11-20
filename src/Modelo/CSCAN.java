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
public class CSCAN implements Estrategia{

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
        
        //Lista clonada de la original que sirve para poder sacar un numero de peticion si es que esta en los extremos del disco.
        //De esta lista solo se borran las peticiones que estan en los extremos
        ArrayList<Integer> peticionesParaEliminarExtremos = (ArrayList<Integer>)peticiones.clone();
        
        //Ordeno la lista para poder ir barriendo y todos los que estan desde la pista posicionCabezal hasta la ultima pista
        Collections.sort(peticionesAux);
        
        
        
        
        //Comienza el algoritmo de planificacion
        if(disco.getSentido() == 'C'){
            while(peticionesAux.isEmpty() == false){
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
                    ordenNroPeticion.add(peticionesParaEliminarExtremos.indexOf(peticionesAux.get(j)));
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

                //Se simula que el cabezal se movio hasta el final despues de la ultima peticion, y luego se mueve a la primer pista, sumando distancias en el proceso

                //Se pregunta si el cabezal no llego a la ultima pista del disco
                if(posicionCabezal != disco.getTotalPistas() - 1){
                    //Se simula el movimiento hasta la ultima pista del disco
                    distanciaParcial = (disco.getTotalPistas() - 1) - posicionCabezal;
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(disco.getTotalPistas() - 1);
                }

                //Se simula el movimiento desde la ultima pista de disco hasta la del principio
                if(peticionesAux.isEmpty() == false){
                    distanciaParcial = disco.getTotalPistas() - 1;
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    posicionCabezal = 0;
                    peticionesCumplidas.add(posicionCabezal);
                }
            }
        }else{
            while(peticionesAux.isEmpty() == false){
                //i sirve para saber desde que posicion de la lista de peticiones se tiene que empezar a servir
                int i = 0;
                if(posicionCabezal == (disco.getTotalPistas()- 1)){
                    i = peticionesAux.size() - 1;
                }else{
                    while(posicionCabezal > peticionesAux.get(i)){
                        i++;
                    }
                    if(posicionCabezal < peticionesAux.get(i)){
                        i--;
                    }
                }
                //Se recorre cada pista sirviendo peticiones
                for (int j = i; j >= 0; j--) {
                    distanciaParcial = posicionCabezal - peticionesAux.get(j);
                    ordenNroPeticion.add(peticionesParaEliminarExtremos.indexOf(peticionesAux.get(j)));
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
                
                //Se simula que el cabezal se movio hasta el final despues de la ultima peticion, y luego se mueve a la primer pista, sumando distancias en el proceso
                
                //Se pregunta si el cabezal no llego a la primer pista del disco
                if(posicionCabezal != 0){
                    //Se simula el movimiento hasta la primer pista del disco
                    distanciaParcial = posicionCabezal;
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(0);
                }
                
                //Se simula el movimiento desde la primer pista del disco hasta la ultima en caso de que aun queden peticiones por servir
                if(peticionesAux.isEmpty() == false){
                    distanciaParcial = disco.getTotalPistas() - 1;
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    posicionCabezal = disco.getTotalPistas() - 1;
                    peticionesCumplidas.add(posicionCabezal);
                }
            }
        }
        
        tiempoAccesoTotal = (distanciaTotal * disco.getSeekTimeMedio()) + ((retardoRotacional + tiempoTransferencia1Bloque) * cantidadBloquesLeidos);
 
        pantallaSalida.meterTexto("Para algoritmo C-SCAN\n");
        pantallaSalida.meterTexto("    Cadena de peticiones que simulo: " + peticiones + "\n");
        pantallaSalida.meterTexto("    Nro Peticion + Pista servida + distancia parcial recorrida hasta ese pista\n");
        Integer j=0;
        for(int i=0; i < peticionesCumplidas.size(); i++){
            if(peticionesParaEliminarExtremos.indexOf(peticionesCumplidas.get(i)) == -1){
                pantallaSalida.meterTexto("        -----" + "  Pista:" + peticionesCumplidas.get(i) + " --> " + distanciasParcialesPorPeticionCumplida.get(i) + "\n");
            }
            else{
                pantallaSalida.meterTexto("        Nro." + ordenNroPeticion.get(j) + "  Pista:" + peticionesCumplidas.get(i) + " --> " + distanciasParcialesPorPeticionCumplida.get(i) + "\n");
                peticionesParaEliminarExtremos.remove(peticionesCumplidas.get(i));
                j++;
            }   
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
