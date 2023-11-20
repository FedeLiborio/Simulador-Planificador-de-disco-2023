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
public class FSCAN implements Estrategia{

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
        
        //Declaro las dos listas que se usan en F-SCAN
        ArrayList<Integer> lista1 = new ArrayList<Integer>();
        ArrayList<Integer> lista2 = new ArrayList<Integer>();
        
        //Se guardan en la primer lista la cantidad de requerimientos que se indicaron con parametro, los restantes se guardan en la segunda lista
        for(int i = 0; i < peticionesAux.size(); i++){
            if (i < disco.getElementosEnPrimerLista_FSCAN())
                lista1.add(peticionesAux.get(i));
            else
                lista2.add(peticionesAux.get(i));
        }
        
        //Paso las listas creadas a otras auxiliares para poder trabajar con las auxiliares
        ArrayList<Integer> lista1aux = (ArrayList<Integer>)lista1.clone();
        ArrayList<Integer> lista2aux = (ArrayList<Integer>)lista2.clone();
        
        //Se ordenan las dos listas
        Collections.sort(lista1aux);
        Collections.sort(lista2aux);
        
        
        
        
        //Comienza el algoritmo de planificacion
        //Se recorre la primera lista de requerimientos
        while (lista1aux.isEmpty() == false){
            //si el sentido es creciente impieza en el primer if, si no en el segundo
            if(disco.getSentido() == 'C'){
                //i sirve para saber desde que posicion de la lista de peticiones se tiene que empezar a servir
                int i = 0;
                if(posicionCabezal == (disco.getTotalPistas()- 1)){
                    i = posicionCabezal;
                }else{
                    while((i < lista1aux.size()) && (posicionCabezal > lista1aux.get(i))){
                        i++;
                    }
                }
                
                //Se recorre cada pista sirviendo peticiones
                for(int j = i; j < lista1aux.size(); j++){
                    distanciaParcial = posicionCabezal - lista1aux.get(j);
                    ordenNroPeticion.add(peticionesParaEliminarExtremos.indexOf(lista1aux.get(j)));
                    if (distanciaParcial < 0){
                        distanciaParcial = distanciaParcial * -1;
                    }
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(lista1aux.get(j));
                    posicionCabezal = lista1aux.get(j);
                }
                
                //Se eliminan las peticiones servidas de la lista auxiliar
                while(i <= lista1aux.size() -1 ){
                    lista1aux.remove(i);
                }
                
                //Cambio el sentido en que debe ir el cabezal
                Character sentidoAux = 'D';
                disco.setSentido(sentidoAux);
                
                //Se pregunta si el cabezal no llego a la ultima pista del disco
                if(posicionCabezal != disco.getTotalPistas() - 1){
                    //Se simula el movimiento hasta la ultima pista del disco
                    distanciaParcial = (disco.getTotalPistas() - 1) - posicionCabezal;
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(disco.getTotalPistas() - 1);
                    posicionCabezal = disco.getTotalPistas() - 1;
                }
            //Entra en caso de que el sentido sea drecresciente
            }else{
                //i sirve para saber desde que posicion de la lista de peticiones se tiene que empezar a servir
                int i = 0;
                while((lista1aux.get(i) < posicionCabezal) && (i != lista1aux.size() - 1)){
                    i++;
                }
                if(lista1aux.get(i) > posicionCabezal){
                    i--;
                }
                
                //Se recorre cada pista sirviendo peticiones
                for (int j = i; j >= 0; j--) {
                    distanciaParcial = posicionCabezal - lista1aux.get(j);
                    ordenNroPeticion.add(peticionesParaEliminarExtremos.indexOf(lista1aux.get(j)));
                    if (distanciaParcial < 0){
                        distanciaParcial = distanciaParcial * -1;
                    }
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(lista1aux.get(j));
                    posicionCabezal = lista1aux.get(j);
                }
                
                //Se eliminan las peticiones servidas de la lista auxiliar
                int j = 0;
                while(j <= i){
                    lista1aux.remove(0);
                    j++;
                }
                
                //Cambio el sentido en que debe ir el cabezal
                Character sentidoAux = 'C';
                disco.setSentido(sentidoAux);
                
                //Se pregunta si el cabezal no llego a la primer pista del disco
                if(posicionCabezal != 0){
                    //Se simula el movimiento hasta la primer pista del disco
                    distanciaParcial = posicionCabezal;
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(0);
                    posicionCabezal = 0;
                }      
            }
        }
        
        
        
        
        //Se recorre la segunda lista de requerimientos
        while(lista2aux.isEmpty() == false){
            //si el sentido es creciente impieza en el primer if, si no en el segundo
            if(disco.getSentido() == 'C'){
                //i sirve para saber desde que posicion de la lista de peticiones se tiene que empezar a servir
                int i = 0;
                if(posicionCabezal == (disco.getTotalPistas()- 1)){
                    i = posicionCabezal;
                }else{
                    while(posicionCabezal > lista2aux.get(i)){
                        i++;
                    }
                }
                
                //Se recorre cada pista sirviendo peticiones
                for(int j = i; j < lista2aux.size(); j++){
                    distanciaParcial = posicionCabezal - lista2aux.get(j);
                    ordenNroPeticion.add(peticionesParaEliminarExtremos.indexOf(lista2aux.get(j)));
                    if (distanciaParcial < 0){
                        distanciaParcial = distanciaParcial * -1;
                    }
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(lista2aux.get(j));
                    posicionCabezal = lista2aux.get(j);
                }
                
                //Se eliminan las peticiones servidas de la lista auxiliar
                while(i <= lista2aux.size() -1 ){
                    lista2aux.remove(i);
                }
                
                //Cambio el sentido en que debe ir el cabezal
                Character sentidoAux = 'D';
                disco.setSentido(sentidoAux);
                
                //Se pregunta si el cabezal no llego a la ultima pista del disco
                if(posicionCabezal != disco.getTotalPistas() - 1){
                    //Se simula el movimiento hasta la ultima pista del disco
                    distanciaParcial = (disco.getTotalPistas() - 1) - posicionCabezal;
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(disco.getTotalPistas() - 1);
                    posicionCabezal = disco.getTotalPistas() - 1;
                }
            //Entra en caso de que el sentido sea drecresciente
            }else{
                //i sirve para saber desde que posicion de la lista de peticiones se tiene que empezar a servir
                int i = 0;
                while((lista2aux.get(i) < posicionCabezal) && (i != lista2aux.size() - 1)){
                    i++;
                }
                if(lista2aux.get(i) > posicionCabezal){
                    i--;
                }
                
                //Se recorre cada pista sirviendo peticiones
                for (int j = i; j >= 0; j--) {
                    distanciaParcial = posicionCabezal - lista2aux.get(j);
                    ordenNroPeticion.add(peticionesParaEliminarExtremos.indexOf(lista2aux.get(j)));
                    if (distanciaParcial < 0){
                        distanciaParcial = distanciaParcial * -1;
                    }
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(lista2aux.get(j));
                    posicionCabezal = lista2aux.get(j);
                }
                
                //Se eliminan las peticiones servidas de la lista auxiliar
                int j = 0;
                while(j <= i){
                    lista2aux.remove(0);
                    j++;
                }
                
                //Cambio el sentido en que debe ir el cabezal
                Character sentidoAux = 'C';
                disco.setSentido(sentidoAux);
                
                //Se pregunta si el cabezal no llego a la primer pista del disco
                if(posicionCabezal != 0){
                    //Se simula el movimiento hasta la primer pista del disco
                    distanciaParcial = posicionCabezal;
                    distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                    if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
                    distanciaTotal = distanciaTotal + distanciaParcial;
                    peticionesCumplidas.add(0);
                    posicionCabezal = 0;
                }    
            }
        }
        tiempoAccesoTotal = (distanciaTotal * disco.getSeekTimeMedio()) + (retardoRotacional + tiempoTransferencia1Bloque) * cantidadBloquesLeidos;
        
        pantallaSalida.meterTexto("Para algoritmo F-SCAN\n");
        pantallaSalida.meterTexto("    Cadena de peticiones que simulo: " + peticiones + "\n");
        pantallaSalida.meterTexto("    Las listas quedaron de la siguiente forma:\n");
        pantallaSalida.meterTexto("        Lista 1: " + lista1 + "\n");
        pantallaSalida.meterTexto("        Lista 2: " + lista2 + "\n");
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
