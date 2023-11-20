package Modelo;

import Vista.PantallaSalida;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Fede
 */
public class SSTF implements Estrategia{

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
        
        //Paso la lista original a una auxiliar para poder trabajar con la auxiliar
        ArrayList<Integer> peticionesAux = (ArrayList<Integer>)peticiones.clone();
        
        
        
        
        //Comienza el algoritmo de planificacion
        //Mientras la lista no este vacia, la recorro buscando cual es la pista con la distancia mas corta. 
        //Al final borro ese elemento de la lista.
        while(peticionesAux.isEmpty() == false){
            Integer distanciaParcial = 0;
            Integer distanciaMasCorta = 99999;
            Integer posicionMasCorta = 0;
            
            //Aca se hace el recorrido buscando el elemento con la distancia mas corta
            for(int i=0; i < peticionesAux.size(); i++){
                distanciaParcial = posicionCabezal - peticionesAux.get(i);
                if(distanciaParcial < 0){
                    distanciaParcial = distanciaParcial * -1;
                }
                if(distanciaMasCorta > distanciaParcial){
                    distanciaMasCorta = distanciaParcial;
                    posicionMasCorta = i;
                }
            }
            
            //Se calcula la distancia parcial para llegar al requerimiento posicionCabezal
            distanciaParcial = posicionCabezal - peticionesAux.get(posicionMasCorta);
            ordenNroPeticion.add(peticiones.indexOf(peticionesAux.get(posicionMasCorta)));
            if(distanciaParcial < 0){
                distanciaParcial = distanciaParcial * -1;
            }
            //Se guardan las distancias parciales
            distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
            if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
            
            //Se calcula la distancia total sumandole la parcial
            distanciaTotal = distanciaTotal + distanciaParcial;
            
            //Se mueve el cabezal de la pista anterior a la posicionCabezal
            posicionCabezal = peticionesAux.get(posicionMasCorta);
            
            //Se guarda la peticion atendida en una lista para que quede el regisdtro del orden en que se atendieron
            peticionesCumplidas.add(peticionesAux.get(posicionMasCorta.intValue()));
            
            //Elimino el elemento de la lista
            peticionesAux.remove(posicionMasCorta.intValue());
        }
        
        tiempoAccesoTotal = (distanciaTotal * disco.getSeekTimeMedio()) + (retardoRotacional + tiempoTransferencia1Bloque) * cantidadBloquesLeidos;
        
        pantallaSalida.meterTexto("Para algoritmo SSTF\n");
        pantallaSalida.meterTexto("    Cadena de peticiones que simulo: " + peticiones + "\n");
        pantallaSalida.meterTexto("    Nro Peticion + Pista servida + distancia parcial recorrida hasta ese pista\n");
        for(int i=0; i < peticionesCumplidas.size(); i++){
            pantallaSalida.meterTexto("        Nro." + ordenNroPeticion.get(i) + "  Pista:" + peticionesCumplidas.get(i) + " --> " + distanciasParcialesPorPeticionCumplida.get(i) + "\n");
        }
        pantallaSalida.meterTexto("    Distancia Total: " + distanciaTotal + "\n");
        pantallaSalida.meterTexto("    Retardo Rotacional: " + retardoRotacional + "ms" + "\n");
        pantallaSalida.meterTexto("    Tiempo de transferencia de 1 bloque: " + tiempoTransferencia1Bloque + "ms" + "\n");
        pantallaSalida.meterTexto("    Calculos para realizar el Tiempo de Acceso Total:" + "\n");
        pantallaSalida.meterTexto("        Cantidad de SKm (movimientos del brazo: " + cantidadMovimientosDelBrazo + "\n");
        pantallaSalida.meterTexto("        Cantidad de rr: " + cantidadBloquesLeidos + "\n");
        pantallaSalida.meterTexto("        Tiempo de Acceso Total: " + tiempoAccesoTotal + "ms" + "\n");
        pantallaSalida.meterTexto("\n");
        pantallaSalida.meterTexto("------------------------------------------------------------------\n");
        pantallaSalida.meterTexto("\n");
    }
    
}
