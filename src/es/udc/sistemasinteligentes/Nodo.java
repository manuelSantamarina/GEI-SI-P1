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
    public boolean isSolucion(){
        boolean outcome = false;
        if(this.nodoPadre == null){
            outcome = true;
        }
        return outcome;
    }
    public Estado getEstado() {
        return estado;
    }

    public Accion getAccion() {
        return accion;
    }

    public Nodo getNodoPadre() {
        return nodoPadre;
    }


    @Override
    public String toString() {
        return "(" +
                estado +
                "," + accion+ ")"
                +"\n"+ nodoPadre;
    }
}