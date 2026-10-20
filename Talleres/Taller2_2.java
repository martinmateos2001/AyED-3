package Talleres;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Taller2_2 {
    public static long minimo(long a, long b){
        if(a <= b){
            return a;
        }
        else{
            return b;
        }
    }
    public static long minCosto(int[] costo, int[] ruta){
        long a = costo[1];
        long b = costo[2];
        // En el peor caso si no le erro, el numero mas grande que se puede obtener es 8*10^21
        long inf = 1_000_000_000_000_000L;
        long subir = 2*a + 2*b;
        long bajar = 2*a + b;
        long seguirArriba = a + 2*b;
        long seguirAbajo = a + b;
        // M[0] <- optimo de elegir nivel 1
        // M[1] <- optimo de elegir nivel 2
        long[] M = new long[2];
        M[0] = b;
        M[1] = inf; // no arrancamos en el nivel 1.
        for(int i = 1; i<costo[0]; i++){
            long opt0 = inf;
            long opt1 = inf; 
            if(ruta[i] == 0){
                opt0 = minimo(M[1] + bajar, M[0] + seguirAbajo);
                opt1 = minimo(M[0]+subir, M[1] + seguirArriba);
            }
            else{
                opt0 = inf;
                if(i == 1){
                    opt1 = M[0] + subir;
                }
                else{
                    opt1 = minimo(M[0] + subir, M[1] + seguirArriba);
                }
            }
            M[0] = opt0;
            M[1] = opt1;
        }
        return M[0];
    }
    public static void main(String[] args) {
        // Leer entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);
        int T = scanner.nextInt();
        int[][] costos = new int[T][3];
        ArrayList<int[]> rutas = new ArrayList<>(T);
        for(int i = 0; i< T; i++){
            for(int j = 0; j< 3; j++){
                costos[i][j] = scanner.nextInt();
            }
            String r = scanner.next();
            int n = r.length();
            int[] ruta = new int[n];
            for(int j = 0; j<n; j++){
                ruta[j] = r.charAt(j) - '0';
            }
            rutas.add(ruta);
        }
        scanner.close();
        for(int i = 0; i< T; i++){
            System.out.println(String.valueOf(minCosto(costos[i], rutas.get(i))));
        }
        

    }
}
