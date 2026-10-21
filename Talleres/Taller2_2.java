package Talleres;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
    
    public static long minCosto(int n, long a, long b, int[] ruta){
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
        M[1] = inf; // no arrancamos en el nivel 2.
        for(int i = 0; i < n; i++){
            long opt0 = inf;
            long opt1 = inf; 
            if(ruta[i] == 1){
                opt0 = inf;
                opt1 = M[1] + seguirArriba;  
            }
            else{
                opt0 = minimo(M[0] + seguirAbajo, M[1]+bajar);
                opt1 = minimo(M[0] + subir, M[1]+seguirArriba);
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);
        for(int i = 0; i< T; i++){
            int n = scanner.nextInt();
            long a = scanner.nextLong();
            long b = scanner.nextLong();
            String r = scanner.next();
            int[] ruta = new int[n];
            for(int j = 0; j<n; j++){
                ruta[j] = r.charAt(j) - '0';
            }
            printer.println(minCosto(n, a, b, ruta));
        }
        scanner.close();
        printer.close();
    }
}
