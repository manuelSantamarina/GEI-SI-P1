package es.udc.sistemasinteligentes;
import es.udc.sistemasinteligentes.ProblemaCuadradoMagico;
public class HeuristicaCuadrado extends Heuristica{

    @Override
    public float evalua(Estado e) {
        ProblemaCuadradoMagico.EstadoCuadrado es = (ProblemaCuadradoMagico.EstadoCuadrado) e;


        return (float) es.getCasillasLibres();
    }
}