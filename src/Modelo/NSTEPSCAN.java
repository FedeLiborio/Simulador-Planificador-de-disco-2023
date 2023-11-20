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
public class NSTEPSCAN implements Estrategia{

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
        
        //Lista que sirve para almacenar la listas que produce el algoritmo NSTEPSCAN
        ArrayList<ArrayList> listaDeListas = new ArrayList<ArrayList>();
        ArrayList<ArrayList> listaDeListasParaImprimir = new ArrayList<ArrayList>();
        
        
        //Se recorre la lista auxiliar para pasar de a N elementos a otras listas
        int iListaExterior = 0;
        int iListaInterna = 0;
        ArrayList<Integer> listaAux =  new ArrayList<Integer>();
        for(int i = 0; i < peticionesAux.size(); i++){
            if(iListaInterna == 0){
                listaDeListas.add(new ArrayList<Integer>());
            }
            listaDeListas.get(iListaExterior).add(peticionesAux.get(i));
            iListaInterna++;
            if(iListaInterna == disco.getTamanioListaNSTEPSCAN()){
                iListaInterna = 0;
                Collections.sort(listaDeListas.get(iListaExterior));
                iListaExterior++;
            }
        }
        
        for(int i = 0; i < listaDeListas.size(); i++){
            listaDeListasParaImprimir.add((ArrayList<ArrayList>)listaDeListas.get(i).clone());
        }
        
        //Finaliza algoritmo para separar en listtas

        
        
        
        //Comienza el algoritmo de planificacion
        for(int indiceListaExterna = 0; indiceListaExterna < listaDeListas.size(); indiceListaExterna++){
            ArrayList<Integer> listaParaServir = listaDeListas.get(indiceListaExterna);
            while(listaParaServir.isEmpty() == false){
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
                    for(int j = i; j < listaParaServir.size(); j++){
                        distanciaParcial = posicionCabezal - listaParaServir.get(j);
                        ordenNroPeticion.add(peticionesParaEliminarExtremos.indexOf(listaParaServir.get(j)));
                        if (distanciaParcial < 0){
                            distanciaParcial = distanciaParcial * -1;
                        }
                        distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                        if(distanciaParcial > 0)
                            cantidadMovimientosDelBrazo++;
                        distanciaTotal = distanciaTotal + distanciaParcial;
                        peticionesCumplidas.add(listaParaServir.get(j));
                        posicionCabezal = listaParaServir.get(j);
                    }

                    //Se eliminan las peticiones servidas de la lista auxiliar
                    while(i <= listaParaServir.size() -1 ){
                        listaParaServir.remove(i);
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
                    while((listaParaServir.get(i) < posicionCabezal) && (i != listaParaServir.size() - 1)){
                        i++;
                    }
                    if(listaParaServir.get(i) > posicionCabezal){
                        i--;
                    }

                    //Se recorre cada pista sirviendo peticiones
                    for (int j = i; j >= 0; j--) {
                        distanciaParcial = posicionCabezal - listaParaServir.get(j);
                        ordenNroPeticion.add(peticionesParaEliminarExtremos.indexOf(listaParaServir.get(j)));
                        if (distanciaParcial < 0){
                            distanciaParcial = distanciaParcial * -1;
                        }
                        distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
                        if(distanciaParcial > 0)
                            cantidadMovimientosDelBrazo++;
                        distanciaTotal = distanciaTotal + distanciaParcial;
                        peticionesCumplidas.add(listaParaServir.get(j));
                        posicionCabezal = listaParaServir.get(j);
                    }

                    //Se eliminan las peticiones servidas de la lista auxiliar
                    int j = 0;
                    while(j <= i){
                        listaParaServir.remove(0);
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
                    /*
                    END ELSE END ELSE END ELSE END ELSE END ELSE END ELSE END ELSE END ELSE END ELSE END ELSE 
                    */

                }
            }
        }
        
        tiempoAccesoTotal = (distanciaTotal * disco.getSeekTimeMedio()) + (retardoRotacional + tiempoTransferencia1Bloque) * cantidadBloquesLeidos;
 
        pantallaSalida.meterTexto("Para algoritmo N-STEP-SCAN\n");
        pantallaSalida.meterTexto("    Cadena de peticiones que simulo: " + peticiones + "\n");
        pantallaSalida.meterTexto("    Las listas quedaron de la siguiente forma:\n");
        for(int i=0; i< listaDeListasParaImprimir.size(); i++){
            pantallaSalida.meterTexto("        Lista " + i + ": " + listaDeListasParaImprimir.get(i) + "\n");
        }
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
