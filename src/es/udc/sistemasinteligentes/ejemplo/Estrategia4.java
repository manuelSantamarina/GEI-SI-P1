package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }



    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> nodosRecorridos = new ArrayList<Nodo>();
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        Nodo nodoPadre = null;

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
                    explorados.add(estadoActual); //Si quitamos esto entra en bucle infinito
                    modificado = true;
                    //Creamos un nodo con el estado actual,
                    //la acción que se llevó a cabo para llegar a ese estado y el nodo padre al que apunta
                    Nodo node = new Nodo(estadoActual, acc, nodoPadre);
                    nodosRecorridos.add(node);
                    nodoPadre=node; //En la siguiente iteración hacemos que el nuevo
                    // nodo apunte a su padre (el nodo anterior)

                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    //System.out.println("Nodo padre "+ (j++)+ " : " + node.getNodoPadre());
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);

        Nodo[] nodosExplorados = new Nodo[nodosRecorridos.size()]; //Creamos una lista de Nodos
        int cont = 0;
        for(Nodo nodo:nodosRecorridos){ //Introducimos en la lista de nodos cada elemento del arraylist de nodosRecorridos
            nodosExplorados[cont] = nodo;
            cont++;
        }
        return nodosExplorados;
    }

    @Override
    public Nodo[] reconstruye_sol(Nodo[] explorados) throws Exception {
        int len = 0;
        Nodo[] solucion;
        Nodo nodoActual = explorados[explorados.length-1];
        do {
            nodoActual = nodoActual.getNodoPadre();
            len++;
        }while (!nodoActual.isSolucion());

        nodoActual = explorados[explorados.length-1];
        solucion = new Nodo[len];
        for(int i = 0; i < len; i++){
            solucion[i] = nodoActual;
            nodoActual = nodoActual.getNodoPadre();
        }
        return solucion;
    }
}