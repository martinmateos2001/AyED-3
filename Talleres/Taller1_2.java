package Talleres;
import java.io .*;
import java.lang.reflect.Array;
import java.util .*;

public class Taller1_2 {
    
    public static void main(String[] args){
        // Cargo los datos
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);

        // Cantidad de nodos en el arbol
        int n = scanner.nextInt();
        
        // Lista de adyacencias. Porque la matriz para el n maximo a testear es muy grande para cantidad de elementos que se tienen en cuenta.
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n+1);
        for(int i = 1; i < n; i++){ // n+1 -> n relaciones
            String a = scanner.nextLine();
            int v = a.charAt(0) - '0';
            int w = a.charAt(2) - '0';
            adj.get(v).add(w);
            adj.get(w).add(v);
        }
        // bfs a chequear
        String seq = scanner.nextLine();
        scanner.close();
        String seq_sin_espacios = seq.replace(" ", "");
        int[] seq_bfs = new int[n+1];
        seq_bfs[0] = 0;
        for(int i = 1; i < n+1; i = i + 2){
            seq_bfs[i] = seq_sin_espacios.charAt(i) - '0';
        }

        /*
        camino_bfs nos da un orden en el que se procesan los nodos.
        Puedo reordenar las aristas para que al final del recorrido bfs del arbol quede igual al camino_bfs.
        int[] orden = new int[n+1] tal que la posición representa el nodo, y el valor en la posición i 
        representa el número de aparición en seq_bfs.
        */
        int[] orden = new int[n+1];
        for(int i = 1; i < seq_bfs.length; i++){
            orden[seq_bfs[i]] =  i;
        }

        // Reordeno las aristas
        for(int i = 1; i < n+1; i++ ){
            adj.get(i).sort((u, v) -> Integer.compare(orden[u], orden[v]));;
        }

        // Ahora puedo implementar bfs sobre adj.
        if(seq_bfs[0] != 1){
            printer.println("No");
        }
        
        
    }
}
