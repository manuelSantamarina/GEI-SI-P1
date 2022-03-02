package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public Estado soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<Estado>();

        //Esto es nuestra lista de nodos explorados
        ArrayList<Nodo> nodosExplorados = new ArrayList<>();


        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        //Insertamos el primer nodo en la lista.
        Nodo nodoPadre = new Nodo(estadoActual,null,null);
        nodosExplorados.add(nodoPadre);
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    //insertamos el estado en los nodos también
                    Nodo nodoActual = new Nodo(estadoActual,acc,nodoPadre);
                    nodosExplorados.add(nodoActual);
                    nodoPadre = nodoActual;

                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return estadoActual;
    }
}