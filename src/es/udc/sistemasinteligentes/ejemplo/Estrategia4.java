package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> explorados = new ArrayList<Nodo>();
        Estado estadoActual = p.getEstadoInicial();

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        //Mientras el estado actual no sea meta.
        while (!p.esMeta(estadoActual)){
            //Imprime el número de secuencia e indica que el estado actual no es meta.
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            //Coge la lista de acciones disponibles del problema pasándole el estado actual
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            //Recorre la lista de acciones disponibles
            for (Accion acc: accionesDisponibles) {
                //Estado sc es el estado resultado de aplicar la acción al estado
                Estado sc = p.result(estadoActual, acc);
                //Imprime que el resultado de aplicar la acción al estado actual es sc
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                //Si los explorados no contienen el estado resultado, entonces es un estado que no habíamos explorado antes

                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    //Imprime que el estado no estaba explorado.
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    //Ponemos que ha sido modificado (el espacio de estados?)
                    modificado = true;
                    //Imprimimos que le estado actual se ha cambiado a el sc que es el nuevo estado.
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                }
                else
                    //Si está dentro de explorados es que ya habíamos pasado por el estado así que lo ignoramos.
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            //Si no se ha modificado el espacio de estados es que no se ha podido encontrar una solución, por lo que
            //el programa acaba.
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        //Llegamos al final e imprimimos el estado en el que acabó el programa
        System.out.println((i++) + " - FIN - " + estadoActual);
        //Devolvemos el array de explorados que es el conjunto de nodos que hemos recorrido..
        return explorados.toArray(new Nodo[0]);
    }
}
