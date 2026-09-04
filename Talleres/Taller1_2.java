package Talleres;
import java.io .*;
import java.lang.reflect.Array;
import java.util .*;

public class Taller1_2 {
    public static ArrayList<Integer> seqBFS(String s){
        ArrayList<Integer> res = new ArrayList<>();
        // split("\\s+") evita fallos por espacios dobles o bordes
        String[] partes = s.split("\\s+");
        for (String p : partes) {
            if (!p.isEmpty()) {
                res.add(Integer.parseInt(p));
            }
        }
        return res;
    }
    public static void main(String[] args){
        // --- Armo los objetos para imprimir y leer por consola ---
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);

        // Cantidad de nodos en el arbol
        int n = Integer.parseInt(scanner.nextLine());
        
        // Lista de adyacencias. Porque la matriz para el n maximo a testear es muy grande para cantidad de elementos que se tienen en cuenta.
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n+1);
        for(int i=0; i < n+1; i++){
            adj.add(new ArrayList<Integer>()); // Si no hago esto tengo un arreglo de n+1 nulls
        }

        for(int i = 0; i < n-1; i++){ // n-1 aristas
            String a = scanner.nextLine();
            int pos_espacio = a.indexOf(' '); 
            int v = Integer.parseInt(a.substring(0, pos_espacio));
            int w = Integer.parseInt(a.substring(pos_espacio + 1, a.length()));
            adj.get(v).add(w);
            adj.get(w).add(v);
        }
        // --- bfs a chequear ---
        String seq_string = scanner.nextLine();
        scanner.close();

        ArrayList<Integer> seq_bfs = seqBFS(seq_string);
        

        /*
        seq_bfs nos da un orden en el que se procesan los nodos.
        Puedo reordenar las aristas para que al final del recorrido bfs del arbol quede igual al camino_bfs.
        int[] orden = new int[n+1] tal que la posición representa el nodo, y el valor en la posición i 
        representa el número de aparición en seq_bfs.
        */
        int[] orden = new int[n+1];
        for(int i = 1; i < n+1; i++){
            orden[seq_bfs.get(i-1)] =  i;
        }

        // Reordeno las aristas
        for(int i = 1; i < n+1; i++ ){
            adj.get(i).sort((u, v) -> Integer.compare(orden[u], orden[v]));;
        }

        // --- Ahora puedo implementar bfs sobre adj ---
        if(seq_bfs.get(0) != 1){
            printer.println("No");
            printer.close();
            return;
        }

        /* Creo la cola de elementos a procesar: q.
        // q.offer(e) -> insertar e
        // q.poll() -> obtiene y elimina, procesa el nodo.
        q.peek() -> consulta quien sigue.
        */
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        // Creo arreglo de visitados para evitar ciclos
        boolean[] visitados = new boolean[n+1];
        visitados[1] = true;

        // Hago bfs
        int i = 0;
        while(!q.isEmpty()){
            int e = q.poll();
            visitados[e] = true;
            if (!seq_bfs.get(i).equals(e)){
                printer.println("No");
                printer.close();
                return;
            }
            i++;
            for(int v:adj.get(e)){
                if(!visitados[v]){
                    visitados[v] = true;
                    q.offer(v);
                }
            }
        }
        printer.println("Yes");
        printer.close();
    }
}
