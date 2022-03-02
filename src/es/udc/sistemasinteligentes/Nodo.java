package es.udc.sistemasinteligentes;
import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class Nodo {

    Estado estado;
    Accion accion;
    Nodo nodoPadre;

    public Nodo(Estado estado, Accion accion, Nodo nodoPadre) {
        this.estado = estado;
        this.accion = accion;
        this.nodoPadre = nodoPadre;
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
