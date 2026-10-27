package Talleres;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Taller3_1{
    public static int[] merge(int[] izq, int[] der){
        int i = 0;
        int j = 0;
        int n = izq.length;
        int m = der.length;
        int[] res = new int[n + m];
        int k = 0;
        while(i<n && j<m){
            if(izq[i] <= der[j]){
                res[k] = izq[i];
                i++;
            }
            else{
                res[k] = der[j];
                j++;
            }
            k++;
        }
        while(i<n){
            res[k] = izq[i];
            i++;
            k++;
        }
        while(j<m){
            res[k] = der[j];
            j++;
            k++;
        }
        return res;
    }

    public static int[] mergeSort(int[] arr){
        int n = arr.length;
        if(n<=1){
            return arr;
        }
        int medio = n / 2;
        int[] izq = new int[medio];
        int[] der = new int[n-medio];
        for(int i = 0; i<n;i++){
            if(i<medio){
                izq[i] = arr[i];
            }
            else{
                der[i-medio] = arr[i];
            }
        }
        int[] izq_ordenada = mergeSort(izq);
        int[] der_ordenada = mergeSort(der);

        return merge(izq_ordenada, der_ordenada);
    }

    public static int alumnosSatisfechos(int[] arr){
        arr = mergeSort(arr);
        int res = 0; // alumnos satisfechos (o no frustrados)
        int t = 0; // Tiempo acumulado
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            int ti = arr[i];
            if(t <= ti){
                res += 1;
                t += ti;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        int res = alumnosSatisfechos(arr);
        printer.println(res);
        printer.close();
    }
}