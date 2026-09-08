package Talleres;
import java.io .*;
import java.lang.reflect.Array;
import java.util .*;

public class Taller1_3 {

    public static void main(String[] args){
        // --- Armo los objetos para imprimir y leer por consola ---
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);

        // Cantidad de palabras 
        int n = Integer.parseInt(scanner.nextLine());
        String[] apellidos = new String[n];
        for(int i = 0; i < n ; i++){
            String a = scanner.nextLine();
            apellidos[i] = a;
        }
        // Ya escanee lo que necesitaba
        scanner.close();
        /* Estrategia
        Voy aprovechar las propiedades del orden topologico en el digrafo de letras
        del abecedario tal que (a,b) si y solo a<b. Al recorrer puedo dectectar ciclos,
        es decir, letras repetidas que rompen con el orden.
        */
        // Creo la lista de adj
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(26); // letra - 'a' -> pos de la letra en el abecedario
        // necesito almacenar los grados para tener candidatos a iniciales.
        int[] grados = new int[26];
        // Inicializo con arreglos vacios
        for(int i = 0; i < 26; i++){
            adj.add(new ArrayList<>());
        }

        // Lleno adj
        for(int i = 0; i < n-1; i++){
            String act = apellidos[i];
            String sig = apellidos[i+1];
            int min = 0;
            if(sig.length() <= act.length() && act.startsWith(sig)){ // no puede pasar que sig este contenida en act, debo imprimir imposible
                printer.println("Impossible");
                printer.close();
                return;
            } 
            if(act.length() <= sig.length()){
                min = act.length();
            }
            else{
                min = sig.length();
            }
            // comparo donde difieren las palabras para establecer la relacion de orden.
            for(int j = 0; j<min; j++){
                char l1 = act.charAt(j);
                char l2 = sig.charAt(j);
                if(l1 != l2){
                    adj.get(l1 - 'a').add(l2 - 'a');
                    grados[l2 -'a'] = grados[l2 - 'a'] + 1;
                    break;
                }
            }
        }

        // armo el orden
        Queue<Integer> Q = new LinkedList<>();
        for(int i = 0; i < 26; i++){
            if(grados[i] == 0){
                Q.add(i);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!Q.isEmpty()) {
            int l = Q.poll();
            res.append((char)('a' + l));
            for(int e : adj.get(l)){
                grados[e] = grados[e] - 1;
                if(grados[e]==0){
                    Q.add(e);
                }
            }
        }
        
        if(res.length() < 26){
            printer.println("Impossible");
        }
        else{
            printer.println(res.toString());
        }
        printer.close();
    }
}
