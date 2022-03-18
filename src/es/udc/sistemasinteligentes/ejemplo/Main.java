package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new Estrategia4();
        Nodo[] solucion=buscador.soluciona(aspiradora);
        System.out.println(solucion[solucion.length-1].getEstado());

        int[][] mCuadradoA = {{4,9,2},{3,5,0},{0,1,0}};
        int[][] mCuadradoB = {{2,0,0},{0,0,0},{0,0,0}};
        int[][] mCuadradoC = {{2,0,0,0},{0,0,0,0},{0,0,0,0},{0,1,0,0}};
        ProblemaCuadradoMagico.EstadoCuadrado esCuadradoA = new ProblemaCuadradoMagico.EstadoCuadrado(mCuadradoA);
        ProblemaCuadradoMagico.EstadoCuadrado esCuadradoB = new ProblemaCuadradoMagico.EstadoCuadrado(mCuadradoB);
        ProblemaCuadradoMagico.EstadoCuadrado esCuadradoC = new ProblemaCuadradoMagico.EstadoCuadrado(mCuadradoC);

        ProblemaBusqueda cuadradoA = new ProblemaCuadradoMagico(esCuadradoA);
        ProblemaBusqueda cuadradoB = new ProblemaCuadradoMagico(esCuadradoB);
        ProblemaBusqueda cuadradoC = new ProblemaCuadradoMagico(esCuadradoC);

        EstrategiaBusqueda profundidad = new BusquedaProfundidad();
        EstrategiaBusqueda anchura = new BusquedaAnchura();

        Nodo[] solCuadradoAAnch = anchura.soluciona(cuadradoA);
        Nodo[] solCuadradoBAnch = anchura.soluciona(cuadradoB);
        Nodo[] solCuadradoCAnch = anchura.soluciona(cuadradoC);

        Nodo[] solCuadradoAProf = profundidad.soluciona(cuadradoA);
        Nodo[] solCuadradoBProf = profundidad.soluciona(cuadradoB);
        Nodo[] solCuadradoCProf = profundidad.soluciona(cuadradoC);


    }
}
