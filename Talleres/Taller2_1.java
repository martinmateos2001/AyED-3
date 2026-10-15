package Talleres;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Taller2_1{
    public static int mininimo(int a, int b, int c){
        if(a <= b && a <= c){
            return a;
        }
        if(b <= a && b<=c){
            return b;
        }
        else{
            return c;
        }
    }
    public static void main(String[] args) {
        // Leer entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);
        int n = scanner.nextInt(); // n dias
        int[] calendario = new int[n];
        for(int i = 0; i < n; i++){
            calendario[i] = scanner.nextInt();
        }
        scanner.close();
        int inf = 1000;
        // M[k] <- minimos descansos hasta i-1 si se hizo la actividad k
        int[] M = new int[3];
        for(int i = 0; i < n; i++){
            // si hoy es 0 sumamos uno al optimo
            int hoy0 = mininimo(M[0], M[1], M[2]) +1;
            // entonces no hay actividad
            int hoy1 = inf;
            int hoy2 = inf;
            
            // si hoy es 1 el optimo viene de 0 o 2
            if(calendario[i] == 1 || calendario[i] == 3){
                hoy1 = mininimo(M[0], inf, M[2]);
            }
            // si hoy es 2 el optimo viene de 0 o 1
            if(calendario[i] == 2 || calendario[i] == 3){
                hoy2 = mininimo(M[0], M[1], inf);
            }

            // actualizamos los optimos
            M[0] = hoy0;
            M[1] = hoy1;
            M[2] = hoy2;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);
        int res = mininimo(M[0], M[1], M[2]);
        printer.println(res);
        printer.close();
    }
} 
    