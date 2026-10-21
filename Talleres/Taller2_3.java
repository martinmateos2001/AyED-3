package Talleres;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Taller2_3{
    public static class AdjList {
        private int n;
        private int T;
        private ArrayList<int[]>[] adj;
        private int[] grados;
        private int[][] M; // para el nodo v, en la longitud del camino acumulado k_i guardo el minimo tiempo acumulado.
        private int[][] P; // para el nodo v, en la longitud del camino acumulado k_i guardo el padre talque el tiempo acumulado es minimo
        private int inf = 1500000000;
        
        public AdjList(int n,int T, int[][] aristas){ // O(n + m)
            this.n = n;
            this.T = T;
            this.adj = (ArrayList<int[]>[]) new ArrayList<?>[n+1];
            this.M = new int[n+1][n+1];
            this.P= new int[n+1][n+1];
            for(int i = 1; i<=n; i++){
                this.adj[i] = new ArrayList<>();
                for(int j = 0; j <= n; j++){
                    this.M[i][j] = inf;
                    this.P[i][j] = -1;
                }
            }
            M[1][0] = 0;
            this.grados = new int[n+1];
            for(int i = 0; i < aristas.length; i++){
                int u = aristas[i][0];
                int v = aristas[i][1];
                int t = aristas[i][2];
                if(t <= T){
                    int[] tupla = new int[2];
                    tupla[0] = v;
                    tupla[1] = t;
                    this.adj[u].add(tupla);
                    this.grados[v] += 1;
                }
            }
            
        }

        static class Resultado {
            int distancia;
            ArrayList<Integer> camino;

            Resultado(int distancia, ArrayList<Integer> camino){
                this.distancia=distancia;
                this.camino=camino;
            }
            
        }

        private ArrayList<Integer> ordenTopologico(){ // O(n+m)
            ArrayList<Integer> res = new ArrayList<>(n); 
            Queue<Integer> Q = new LinkedList<>();
            int[] gradosCopia = grados.clone();
            for(int i = 1; i <= n; i++){
                if(gradosCopia[i] == 0){
                    Q.add(i);
                }
            }
            while (!Q.isEmpty()) {
                int u = Q.poll();
                res.add(u);
                for(int[] V : adj[u]){
                    int v = V[0];
                    gradosCopia[v] -= 1;
                    if(gradosCopia[v] == 0){
                        Q.add(v);
                    }
                }
            }
            return res;
        }


        public void camMinDAGs(){ // O(n+m) + O(m*n) = O(m*n) 
            ArrayList<Integer> orden = ordenTopologico(); // O(n+m) 
            for(int u : orden){
                for(int[] V : adj[u]){ // con el bucle anterior tenemos O(m)
                    int v = V[0];
                    int peso = V[1];
                    for(int k = 1; k <= n ; k++){ // O(n)
                        if(M[u][k-1] != inf && M[u][k-1] + peso < M[v][k]){
                            M[v][k] = M[u][k-1] + peso;
                            P[v][k] = u;
                        }
                    }
                }
            }
        }

        public Resultado respuesta(){ // O(n*m)
            camMinDAGs(); // O(n*m)
            int k = n;
            while (k>2 && M[n][k] > T) {   // O(n)
                k--;
            }
            int distancia = k+1;
            ArrayList<Integer> camino = new ArrayList<>();
            int p = n;
            while (p!=-1) { // O(n)
                camino.addFirst(p);
                p = P[p][k];
                k--;
            }
            Resultado res = new Resultado(distancia, camino);
            return res;
        }
        
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int T = scanner.nextInt();
        int[][] aristas = new int[m][3];
        for(int i = 0; i < m; i++){
            int[] a = new int[3];
            a[0] = scanner.nextInt();
            a[1] = scanner.nextInt();
            a[2] = scanner.nextInt();
            aristas[i] = a;
        }
        scanner.close();
        AdjList listAdjacencias = new AdjList(n, T, aristas);
        AdjList.Resultado res = listAdjacencias.respuesta();
        printer.println(res.distancia);
        for (int nodo : res.camino) {
            printer.print(nodo + " ");
        }
        printer.println();
        printer.close();
    }
}