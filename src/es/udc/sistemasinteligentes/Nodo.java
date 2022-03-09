package es.udc.sistemasinteligentes;
import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class Nodo {

    Estado estado;
    Accion accion;
    Nodo nodoPadre;

    public Nodo(Estado estado, Accion accion, Nodo nodo) {
        this.estado = estado;
        this.accion = accion;
        this.nodoPadre = nodo;
    }

    public Estado getEstado() {
        return estado;
    }

    public Accion getAccion() {
        return accion;
    }

    public Nodo getNodo() {
        return nodoPadre;
    }
}
