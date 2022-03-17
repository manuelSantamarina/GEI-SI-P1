package es.udc.sistemasinteligentes;

import es.udc.sistemasinteligentes.ejemplo.ProblemaAspiradora;

public class ProblemaCuadradoMagico extends ProblemaBusqueda{

    public static class EstadoCuadrado extends Estado {
        private int[][] cuadrado;
        public EstadoCuadrado(int[][] valoresIniciales){
            this.cuadrado = valoresIniciales;
        }
        @Override
        public String toString() {
            return null;
        }

        @Override
        public boolean equals(Object obj) {

            if(obj.equals(this.cuadrado)){
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.
        }
    }

    public static class AccionCuadrado extends Accion{

        public enum Tipo{RELLENAR};
        private Tipo tipo;

        public AccionCuadrado(Tipo tipo) {this.tipo = tipo;}
        @Override
        public String toString() {
            return tipo.name();
        }

        @Override
        public boolean esAplicable(Estado es) {
            return true; //siempre es aplicable? //TODO: Corregir
        }

        @Override
        public Estado aplicaA(Estado es) {
            return null;
        }
    }



    private Accion[] listaAcciones;

    public ProblemaCuadradoMagico(EstadoCuadrado estadoInicial){
        super(estadoInicial);
    }
    @Override
    public boolean esMeta(Estado es) {
        boolean isMeta = true;
        int suma = 0;
        int n = ((EstadoCuadrado) es).cuadrado.length;
        int[][] cuadrado = ((EstadoCuadrado) es).cuadrado;
        //Comprobar filas
        for (int i = 0; i < cuadrado.length; i++) {
            for (int j = 0; j < cuadrado[0].length; j++) {
                suma += cuadrado[i][j];
            }
            if(suma != (n*(n*n+1))/2){
                isMeta = false;
            }
            suma = 0;
        }

        //Comprobar columnas
        for (int i = 0; i < cuadrado.length; i++) {
            for (int j = 0; j < cuadrado[0].length; j++) {
                suma += cuadrado[j][i];
            }
            if(suma != (n*(n*n+1))/2){
                isMeta = false;
            }
            suma = 0;
        }
        //Comprobar diagonales
        // Diagonal
        if(isMeta) {
            int sumaDiagonal1 = 0;
            int sumaDiagonal2 = 0;
            int j = 0;
            for (int i = 0; i < n; i++) {
                sumaDiagonal1 = cuadrado[i][j];
                sumaDiagonal2 = cuadrado[i][n - j - 1];
                j++;
            }
            if (sumaDiagonal1 != (n * (n * n + 1)) / 2 || sumaDiagonal2 != (n * (n * n + 1) / 2)) {
                isMeta = false;
            }
        }
        return isMeta;
    }

    @Override
    public Accion[] acciones(Estado es) {
        //La lista de acciones se compone por todas las acciones aplicables al cuadrado.
        // Es decir, el número de casillas libres al cuadrado.
        // Estas acciones son aplicables
        return listaAcciones;
    }
}
