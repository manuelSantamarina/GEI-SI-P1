package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Stack;

public class BusquedaProfundidad implements EstrategiaBusqueda{
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        //Usamos una LIFO (Pila)

        ArrayList<Nodo> nodosExplorados = new ArrayList<Nodo>();
        Stack<Nodo> frontera = new Stack<Nodo>();

        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(estadoActual,null,null);
        nodosExplorados.add(new Nodo(estadoActual,null,null));
        int i = 1;
        while(!p.esMeta(estadoActual)){
            System.out.println((i++)+ "-"+estadoActual +"no es meta.");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;

            //Generar frontera
            for(Accion acc:accionesDisponibles){
                Estado sc = p.result(estadoActual,acc);
                frontera.add(new Nodo(p.result(estadoActual,acc),acc,nodoActual));
                if(nodosExplorados.contains(nodoActual)){
                    nodosExplorados.add(nodoActual);
                }else{
                    System.out.println("Nodo ya presente.");
                }
            }
            frontera.pop();
        }
        return (Nodo[]) nodosExplorados.toArray();
    }
}
