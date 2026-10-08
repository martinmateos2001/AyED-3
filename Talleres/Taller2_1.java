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
        
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);
        printer.println(res);
        printer.close();
    }
} 
    