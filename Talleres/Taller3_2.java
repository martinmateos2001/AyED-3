package Talleres;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Taller3_2
 */
public class Taller3_2 {
    static long inf = 1_000_000_000_000_000L;
    public static class MinHeap {
        private int n;
        private ArrayList<int[]> arr; // lista de pares (tiempo, ciudad)

        public MinHeap(int n){
            this.n = n;
            this.arr = new ArrayList<int[]>(n+1);
            for(int i = 1; i <= n; i++){
                Ciudad c = new Ciudad(i);
                this.arr.add(i, c);
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
            Ciudad ni = arr.get(i);
            Ciudad nj = arr.get(j);
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
        
        public boolean estaVacia(){
            if(n == 0){
                return true;
            }
            return false;
        }
    }

    public static int solucion(ArrayList<ArrayList<int[]>> adj){
        int[] res = new int[adj.size()];
        return res;
    }
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);

        int tests = scanner.nextInt();
        for(int i = 0; i < tests; i++){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n+1);
            for(int k = 1; i <= n; i++){
                ArrayList<int[]> N = new ArrayList<>();
                adj.add(N);
            }
            for(int j = 0; j<m; j++){
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int w = scanner.nextInt();

                int[] uv = new int[2];
                uv[0] = v;
                uv[1] = w;

                int[] vu = new int[2];
                vu[0] = u;
                vu[1] = w;

                adj.get(u).add(vu);
                adj.get(v).add(uv);
            }

            int[] factores = new int[n+1];
            for(int j = 1; j<=n; j++){
                factores[j] = scanner.nextInt();
            }

            printer.println(solucion(n, adj, factores));
        }
    }
}