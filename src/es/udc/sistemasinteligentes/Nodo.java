package es.udc.sistemasinteligentes;
import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class Nodo {

    Estado estado;
    Accion accion;
    Nodo nodo;

    public Nodo(Estado estado, Accion accion, Nodo nodo) {
        this.estado = estado;
        this.accion = accion;
        this.nodo = nodo;
    }

    public Estado getEstado() {
        return estado;
    }

    public Accion getAccion() {
        return accion;
    }

    public Nodo getNodo() {
        return nodo;
    }
}
