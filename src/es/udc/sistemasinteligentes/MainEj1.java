package es.udc.sistemasinteligentes;

import es.udc.sistemasinteligentes.ejemplo.Estrategia4;
import es.udc.sistemasinteligentes.*;
import es.udc.sistemasinteligentes.ejemplo.ProblemaAspiradora;

public class MainEj1 {
    public static void main(String[] args) throws Exception{
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);

        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new Estrategia4();
        Nodo[] solucion=buscador.soluciona(aspiradora);
        System.out.println(solucion[solucion.length-1].getEstado());
    }

}
