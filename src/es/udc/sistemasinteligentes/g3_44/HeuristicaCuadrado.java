package es.udc.sistemasinteligentes.g3_44;

public class HeuristicaCuadrado extends Heuristica{

    @Override
    public float evalua(Estado e) {
        ProblemaCuadradoMagico.EstadoCuadrado es = (ProblemaCuadradoMagico.EstadoCuadrado) e;


        return (float) es.getCasillasLibres();
    }
}
