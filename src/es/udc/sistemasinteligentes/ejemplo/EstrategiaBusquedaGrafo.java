package es.udc.sistemasinteligentes.ejemplo;
import es.udc.sistemasinteligentes.*;
import java.util.*;
public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {
    public class Printer{
        int contador;
        public void print(String mensaje){
            this.contador++;
            System.out.println(contador + mensaje);
        }
    }
    public Queue<Nodo> sucesores(ProblemaBusqueda p, Estado estadoActual, Nodo nodoPadre, ArrayList<Estado> explorados, Queue<Nodo> frontera, Printer printer) {
        Accion[] accionesDisponibles = p.acciones(estadoActual);
        Estado sc;
        boolean presente = false;
        for (Accion acc : accionesDisponibles) {
            if (acc.esAplicable(estadoActual)) {
                sc = p.result(estadoActual, acc);
                printer.print(" - RESULT(" + estadoActual + "," + acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    printer.print(" - " + sc + " NO explorado");
                    for (Nodo node : frontera) {
                        if (node.getEstado() == sc) {
                            presente = true;
                            break;
                        }
                    }
                    if (!presente) {
                        //printer.print(" - " + sc + " NO está en la frontera");
                        frontera.add(new Nodo(sc, acc, nodoPadre));

                    }
                } else {
                    printer.print(" - " + sc + " ya explorado");
                }

            }
        }
        return frontera;
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> nodosRecorridos = new ArrayList<>();
        ArrayList<Estado> explorados = new ArrayList<>();
        Queue<Nodo> frontera = new LinkedList<>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        Nodo aux;
        Printer printer= new Printer();
        frontera.add(new Nodo(estadoActual, null, null));

        printer.print(" - Empezando búsqueda en " + estadoActual);

        while (true){
            if(frontera.isEmpty()){
                throw new Exception("No hay solución");
            }
            else{ //cogemos el primer nodo de la cola
                aux = frontera.poll();
            }
            if(p.esMeta(aux.getEstado())){
                //printer.print(" - " + aux.getEstado() + " es meta");
                break;
            } else{
                printer.print(" - " + estadoActual + " no es meta");
                explorados.add(estadoActual);
                frontera = sucesores(p, estadoActual, aux, explorados, frontera, printer);
            }
            if(!frontera.isEmpty()){
                estadoActual = frontera.peek().getEstado();
                printer.print(" - Estado actual cambiado a " + estadoActual);
                nodosRecorridos.add(frontera.peek());
            }
        }
        printer.print(" - FIN - " + estadoActual);

        Nodo[] nodosExplorados = new Nodo[nodosRecorridos.size()]; //Creamos una lista de Nodos
        int cont = 0;
        for(Nodo nodo:nodosRecorridos){ //Introducimos en la lista de nodos cada elemento del arraylist de nodosRecorridos
            nodosExplorados[cont] = nodo;
            cont++;
        }
        return nodosExplorados;
    }
}