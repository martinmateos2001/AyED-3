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
        int n;
        ArrayList<int[]>[] adj;
        int[] grados;
        int[] d;
        int[] padres;
        private int inf = 1500000000;

        
        public AdjList(int n,int T, int[][] aristas){
            ArrayList<int[]>[] adj = (ArrayList<int[]>[]) new ArrayList<?>[n];
            for(int i = 0; i<n; i++){
                adj[i] = new ArrayList<>();
            }
            int[] grados = new int[n];
            for(int i = 0; i < aristas.length; i++){
                int u = aristas[i][0];
                int v = aristas[i][1];
                int t = aristas[i][2];
                if(t <= T){
                    int[] tupla = new int[2];
                    tupla[0] = v;
                    tupla[1] = t;
                    adj[u-1].add(tupla);
                    grados[v-1] += 1;
                }
                
            }
            this.adj = adj;
            this.grados = grados;
            this.n = n;
        }

        private ArrayList<Integer> ordenTopologico(){
            int n = grados.length;
            ArrayList<Integer> res = new ArrayList<>(n); 
            Queue<Integer> Q = new LinkedList<>();
            for(int i = 0; i < n; i++){
                if(grados[i] == 0){
                    Q.add(i);
                }
            }
            while (!Q.isEmpty()) {
                int u = Q.poll();
                res.add(u);
                for(int[] v : adj[u]){
                    grados[v[0]] -= 1;
                    if(grados[v[0]] == 0){
                        Q.add(v[0]);
                    }
                }
            }
            return res;
        }

        private void relajar(int u, int v, int t){
            if(d[u] < inf && d[v] > d[u] + t){
                d[v] = d[u] + t;
                padres[v] = u;
            }
        }

        public int[][] camMinDAGs(){
            ArrayList<Integer> orden = ordenTopologico();
            this.d = new int[this.n];
            this.padres = new int[this.n];
            for(int i = 0; i< this.n; i++){
                d[i] = this.inf;
                padres[i] = -1;
            }
            int s = orden.get(0);
            d[s] = 0;
            for(int u : orden){
                for(int[] v : adj[u]){
                    relajar(u, v[0], v[1]);
                }
            }
            int[][] res = new int[2][this.n];
            res[0] = this.d;
            res[1] = this.padres;
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
        int [][] ls = listAdjacencias.camMinDAGs();
        int[] d = ls[0];
        int[] padres = ls[1];
        int u = -1;
        for(int i = 0; i < n; i++){
            if(d[i] > u && d[i] <= T){
                u = i;
            }
        }
        ArrayList<Integer> camino = new ArrayList<>();
        camino.addFirst(u+1);
        while(u != -1){
            u = padres[u];
            camino.addFirst(u+1);
        }
        printer.println(camino.size());
        printer.println(camino);
        printer.close();
    }
}