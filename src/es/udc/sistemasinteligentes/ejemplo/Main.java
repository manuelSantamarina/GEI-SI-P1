package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new Estrategia4();
        Nodo[] explorados=buscador.soluciona(aspiradora);
        Nodo[] sol = buscador.reconstruye_sol(explorados);
        System.out.println("Solución:");
        for (int i = 0; i < sol.length-1; i++) {
            System.out.println(sol[i].toString()+ "\n");
        }
    }
}
