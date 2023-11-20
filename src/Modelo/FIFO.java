package Modelo;

import Vista.PantallaSalida;
import java.util.ArrayList;
import java.io.PrintWriter;
import javax.swing.JTextArea;

/**
 *
 * @author Fede
 */
public class FIFO implements Estrategia{

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
        
        
        
        
        //Comienza el algoritmo de planificacion
        for(int i=0; i < peticiones.size(); i++){
            // Calculo la distancia parcial
            Integer distanciaParcial = posicionCabezal - peticiones.get(i);
            ordenNroPeticion.add(i);
            peticionesCumplidas.add(peticiones.get(i));
            if(distanciaParcial < 0){
                distanciaParcial = distanciaParcial * -1;
            }
            distanciasParcialesPorPeticionCumplida.add(distanciaParcial);
            if(distanciaParcial > 0)
                        cantidadMovimientosDelBrazo++;
            distanciaTotal = distanciaTotal + distanciaParcial;
            posicionCabezal = peticiones.get(i);
        }
        
        tiempoAccesoTotal = (distanciaTotal * disco.getSeekTimeMedio()) + (retardoRotacional + tiempoTransferencia1Bloque) * cantidadBloquesLeidos;
        
        //Imprimo datos, mover a pantalla de vista
        
        /*
        System.out.println("Para el algoritmo FIFO:");
        System.out.println("    Cadena de peticiones que se simuló: " + peticiones);
        System.out.println("    Nro Peticion + Pista servida + distancia parcial recorrida hasta ese pista");
        for(int i=0; i < peticionesCumplidas.size(); i++){
            System.out.println("        Nro." + ordenNroPeticion.get(i) + "  Pista:" + peticionesCumplidas.get(i) + " --> " + distanciasParcialesPorPeticionCumplida.get(i) );
        }
        System.out.println("    Distancia Total: " + distanciaTotal);
        
        System.out.println("    Retardo Rotacional: " + retardoRotacional + "ms");
        System.out.println("    Tiempo de transferencia de 1 bloque: " + tiempoTransferencia1Bloque + "ms");

        System.out.println("    Calculos para realizar el Tiempo de Acceso Total:");
        System.out.println("        Cantidad de SKm (movimientos del brazo: " + cantidadMovimientosDelBrazo);
        System.out.println("        Cantidad de rr: " + cantidadBloquesLeidos);
        System.out.println("        Tiempo de Acceso Total: " + tiempoAccesoTotal + "ms");
        System.out.println("");
        System.out.println("------------------------------------------------------------------");
        System.out.println(""); 
        */
        
        pantallaSalida.meterTexto("Para el algoritmo FIFO:\n");
        pantallaSalida.meterTexto("    Cadena de peticiones que se simuló: " + peticiones + "\n");
        pantallaSalida.meterTexto("    Nro Peticion + Pista servida + distancia parcial recorrida hasta ese pista\n");
        for(int i=0; i < peticionesCumplidas.size(); i++){
            pantallaSalida.meterTexto("        Nro." + ordenNroPeticion.get(i) + "  Pista:" + peticionesCumplidas.get(i) + " --> " + distanciasParcialesPorPeticionCumplida.get(i) + "\n");
        }
        pantallaSalida.meterTexto("    Distancia Total: " + distanciaTotal + "\n");
        
        pantallaSalida.meterTexto("    Retardo Rotacional: " + retardoRotacional + "ms" + "\n");
        pantallaSalida.meterTexto("    Tiempo de transferencia de 1 bloque: " + tiempoTransferencia1Bloque + "ms" + "\n");

        pantallaSalida.meterTexto("    Calculos para realizar el Tiempo de Acceso Total:\n");
        pantallaSalida.meterTexto("        Cantidad de SKm (movimientos del brazo: " + cantidadMovimientosDelBrazo + "\n");
        pantallaSalida.meterTexto("        Cantidad de rr: " + cantidadBloquesLeidos + "\n");
        pantallaSalida.meterTexto("        Tiempo de Acceso Total: " + tiempoAccesoTotal + "ms" + "\n");
        pantallaSalida.meterTexto("\n");
        pantallaSalida.meterTexto("------------------------------------------------------------------\n");
        pantallaSalida.meterTexto("\n");         
    }
    
}
