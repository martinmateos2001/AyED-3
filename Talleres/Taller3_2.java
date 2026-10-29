package Talleres;

import java.util.ArrayList;

/**
 * Taller3_2
 */
public class Taller3_2 {
    static long inf = 1_000_000_000_000_000L;
    public static class Nodo {
        private int i; // Ciudad i.
        private long t; // Tiempo minimo que se tarda en llegar desde la ciudad 1.
        public Nodo(int i){
            this.i = i;
            this.t = inf;
        }

        public int id(){
            return i;
        }

        public long distancia(){
            return t;
        }

        public void actualizarDistancia(long t){
            this.t = t;
        }
    }

    public static class MinHeap {
        private int n;
        private ArrayList<Nodo> arr;

        public MinHeap(int n){
            this.n = n;
            this.arr = new ArrayList<Nodo>(n+1);
            for(int i = 1; i <= n; i++){
                Nodo nodo = new Nodo(i);
                this.arr.add(i, nodo);
            }
        }

        public int tamaño(){
            return n;
        }

        private int padre(int i){
            return (i) / 2;
        }

        private int hijoIzq(int i){
            return 2*i;
        }

        private int hijoDer(int i){
            return (2*i) + 1;
        }

        private void intercambiar(int i, int j){
            Nodo ni = arr.get(i);
            Nodo nj = arr.get(j);
            arr.add(i, nj);
            arr.add(j, ni);
        }
        private void bajar(int i){
            int izq = hijoIzq(i);
            int der = hijoDer(i);
            int menor = i;

            if(izq <= n && arr.get(izq).distancia() < arr.get(menor).distancia()){
                menor = izq;
            }

            if(der <= n && arr.get(der).distancia() < arr.get(menor).distancia()){
                menor = der;
            }

            if(menor != i){
                intercambiar(izq, menor);
                bajar(menor);
            }
        }

        public Nodo extraer(){
            if(this.n == 0){
                return null;
            }
            Nodo minimo = arr.get(1);
            Nodo max = arr.get(n);
            arr.add(1, max);
            n = n - 1;

            if(n > 0){
                bajar(1);
            }
            return minimo;
        }
        
    }

    public static void main(String[] args) {
        
    }
}