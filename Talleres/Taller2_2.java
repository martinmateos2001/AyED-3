package Talleres;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taller2_2 {
    public static int minCosto(int[] costo, int[] ruta){

        return 0;
    }
    public static void main(String[] args) {
        // Leer entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);
        System.out.println("Numero de gasoductos");
        int T = scanner.nextInt();
        int[][] costos = new int[T][3];
        ArrayList<int[]> rutas = new ArrayList<>(T);
        for(int i = 0; i< T; i++){
            System.out.println("Ingrese longitud de la ruta, luego costo unidad gasoducto, luego costo pilar");
            for(int j = 0; j< 3; j++){
                costos[i][j] = scanner.nextInt();
            }
            System.out.println("Ingrese la ruta");
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
            System.out.println("Costos: " + java.util.Arrays.toString(costos[i]));
            System.out.println("Ruta: " + java.util.Arrays.toString(rutas.get(i)));
        }
        
    }
}
