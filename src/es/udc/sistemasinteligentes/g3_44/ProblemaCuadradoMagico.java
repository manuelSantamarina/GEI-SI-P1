package es.udc.sistemasinteligentes.g3_44;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemaCuadradoMagico extends ProblemaBusqueda{

    public static class EstadoCuadrado extends Estado {
        public int[][] cuadrado = {{4,9,2},{3,5,0},{0,1,0}};

        public EstadoCuadrado(int[][] valoresIniciales){
            int[][] mCuadradoA = {{4,9,2},{3,5,0},{0,1,0}};
            this.cuadrado = mCuadradoA;
        }
        @Override
        public String toString() {
            String out = "";
            for (int i = 0; i < this.cuadrado.length; i++) {
                for (int j = 0; j < this.cuadrado.length; j++) {
                    out+= this.cuadrado[i][j]+",";
                }
                out+="\n";
            }
            return out;
        }

        @Override
        public boolean equals(Object obj) {
            return obj.equals(this.cuadrado);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(this.cuadrado);
        }

        public int getCasillasLibres() {
            int casillasLibres = 0;
            for (int[] ints : this.cuadrado) {
                for (int j = 0; j < this.cuadrado.length; j++) {
                    if (ints[j] == 0) {
                        casillasLibres++;
                    }
                }
            }
            return casillasLibres;
        }
        public boolean isFull(){
            return this.getCasillasLibres() == 0;

        }
    }

    public static class AccionCuadrado extends Accion{


        private final int numero;
        private final int x;
        private final int y;

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
        for (int[] ints : cuadrado) {
            for (int j = 0; j < cuadrado[0].length; j++) {
                suma += ints[j];
            }
            if (suma != (n * (n * n + 1)) / 2) {
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


        Accion[] listaAcciones = new Accion[numAcciones];
        //generamos la lista de acciones:

        List<Object> ocupados = new ArrayList<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if(((EstadoCuadrado) es).cuadrado[x][y] == 0){
                    for (int i = 1; i < n*n;i++) { //bucle número
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

