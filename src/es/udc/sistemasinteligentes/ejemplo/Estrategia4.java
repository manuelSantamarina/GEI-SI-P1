package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {{{
    }
    }
    }

    public Nodo[] reconstruye_sol(Nodo node){
        int i = 0;
        ArrayList<Nodo> arrayNodos = new ArrayList<>();
        Nodo nodoActual = node;

        while(nodoActual != null){
            arrayNodos.add(nodoActual);
            nodoActual=nodoActual.getNodo(); //Obtenemos el nodo padre
        }

        Nodo[] solucion = new Nodo[arrayNodos.size()];
        for(Nodo nodo:arrayNodos){
            solucion[i]=nodo;
            i++;
        }

        return solucion;
    }

    @Override
<<<<<<< HEAD
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> nodosRecorridos = new ArrayList<Nodo>();
=======
    public Estado soluciona(ProblemaBusqueda p) throws Exception{
>>>>>>> wip
        ArrayList<Estado> explorados = new ArrayList<Estado>();

        //Esto es nuestra lista de nodos explorados
        ArrayList<Nodo> nodosExplorados = new ArrayList<>();


        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
<<<<<<< HEAD
        Nodo nodoPadre = null;

=======
        //Insertamos el primer nodo en la lista.
        Nodo nodoPadre = new Nodo(estadoActual,null,null);
        nodosExplorados.add(nodoPadre);
>>>>>>> wip
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
<<<<<<< HEAD
                    explorados.add(estadoActual); //Si quitamos esto entra en bucle infinito
=======
                    explorados.add(estadoActual);
                    //insertamos el estado en los nodos también
                    Nodo nodoActual = new Nodo(estadoActual,acc,nodoPadre);
                    nodosExplorados.add(nodoActual);
                    nodoPadre = nodoActual;

>>>>>>> wip
                    modificado = true;
                    //Creamos un nodo con el estado actual,
                    //la acción que se llevó a cabo para llegar a ese estado y el nodo padre al que apunta
                    Nodo node = new Nodo(estadoActual, acc, nodoPadre);
                    nodosRecorridos.add(node);
                    nodoPadre=node; //En la siguiente iteración hacemos que el nuevo
                                    // nodo apunte a su padre (el nodo anterior)

                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);

        Nodo[] solucion = new Nodo[nodosRecorridos.size()];
        int cont = 0;
        for(Nodo nodo:nodosRecorridos){
            solucion[cont] = nodo;
            cont++;
        }
        return solucion;
    }
}