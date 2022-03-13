package es.udc.sistemasinteligentes;

public interface EstrategiaBusqueda {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @return Estado meta obtenido[
     */
    public abstract Nodo[] soluciona(ProblemaBusqueda p) throws Exception;
    public default Nodo[] reconstruye_sol(Nodo[] explorados) throws Exception {
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
