package es.udc.sistemasinteligentes;

import es.udc.sistemasinteligentes.ejemplo.ProblemaAspiradora;

import java.util.ArrayList;

public class ProblemaCuadradoMagico extends ProblemaBusqueda{

    public static class EstadoCuadrado extends Estado {
        public int[][] cuadrado;
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
            int result = this.cuadrado.hashCode();
            return result;
        }

        public int getCasillasLibres() {
            int casillasLibres = 0;
            for (int i = 0; i < this.cuadrado.length; i++) {
                for (int j = 0; j < this.cuadrado.length; j++) {
                    if(this.cuadrado[i][j] == 0){
                        casillasLibres++;
                    }
                }
            }
            return casillasLibres;
        }
        public boolean isFull(){
            if (this.getCasillasLibres() == 0){
                return true;
            }else{
                return false;
            }

        }
    }

    public static class AccionCuadrado extends Accion{


        private int numero;
        private int x;
        private int y;

        public AccionCuadrado(int numero, int x, int y) {
            this.numero = numero;
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "Acción: Rellenar con "+this.numero+" en las coordenadas X: "+this.x+"Y: "+this.y;
        }

        @Override
        public boolean esAplicable(Estado es) {
            return true; //siempre es aplicable? //TODO: Corregir
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadrado esCu = (EstadoCuadrado) es;

            esCu.cuadrado[x][y] = numero;
            return new EstadoCuadrado(esCu.cuadrado);
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
        int casillasLibres = ((EstadoCuadrado)es).getCasillasLibres();

        //La lista de acciones se compone por todas las acciones aplicables al cuadrado.
        int numAcciones = casillasLibres * casillasLibres;
        int n = ((EstadoCuadrado) es).cuadrado.length;
        //Rellenar n cuadrados con los números que no se hayan puesto ya.


        this.listaAcciones = new Accion[numAcciones];
        //generamos la lista de acciones:

        ArrayList ocupados = new ArrayList<>();

        for (int x = 0; x < ((EstadoCuadrado) es).cuadrado.length; x++) {
            for (int y = 0; y < ((EstadoCuadrado) es).cuadrado.length; y++) {
                if(((EstadoCuadrado) es).cuadrado[x][y] == 0){
                    for (int i = 0; i < ((EstadoCuadrado) es).cuadrado.length*((EstadoCuadrado) es).cuadrado.length; i++) { //bucle número
                        //Añadir check de que no sea
                        if(!ocupados.contains(i)){
                            listaAcciones[i] = new AccionCuadrado(i, x, y);
                        }
                    }
                }else{
                    ocupados.add(((EstadoCuadrado) es).cuadrado[x][y]);
                    }
                }
            }
        return listaAcciones;
    }
}
