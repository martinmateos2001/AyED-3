package Talleres;
import java.io .*;
import java.lang.reflect.Array;
import java.util .*;

public class Taller1_2 {
    public static ArrayList<Integer> seqBFS(String s){
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            String aux = s.substring(i, s.length());
            int espacio = aux.indexOf(' ');
            int v = Integer.parseInt(s.substring(i, espacio));
            res.add(v);
            i = espacio + 1;
        }
        return res;
    }
    public static void main(String[] args){
        // Armo los objetos para imprimir y leer por consola
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
            int pos_espacio = a.indexOf(' '); 
            int v = Integer.parseInt(a.substring(0, pos_espacio));
            int w = Integer.parseInt(a.substring(pos_espacio + 1, a.length()));
            adj.get(v).add(w);
            adj.get(w).add(v);
        }
        // bfs a chequear
        String seq_string = scanner.nextLine();
        scanner.close();

        ArrayList<Integer> seq_bfs = seqBFS(seq_string);
        

        /*
        camino_bfs nos da un orden en el que se procesan los nodos.
        Puedo reordenar las aristas para que al final del recorrido bfs del arbol quede igual al camino_bfs.
        int[] orden = new int[n+1] tal que la posición representa el nodo, y el valor en la posición i 
        representa el número de aparición en seq_bfs.
        */
        int[] orden = new int[n+1];
        for(int i = 1; i < seq_bfs.size(); i++){
            orden[seq_bfs.get(i)] =  i;
        }

        // Reordeno las aristas
        for(int i = 1; i < n+1; i++ ){
            adj.get(i).sort((u, v) -> Integer.compare(orden[u], orden[v]));;
        }

        // Ahora puedo implementar bfs sobre adj.
        if(seq_bfs.get(0) != 1){
            printer.println("No");
        }

        // Creo la cola de elementos a procesar: q
        // q.offer(e) -> insertar e
        // q.poll() -> obtiene y elimina, procesa el nodo.
        // q.peek() -> consulta quien sigue.
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        // Creo arreglo de visitados para evitar ciclos
        boolean[] visitados = new boolean[n+1];

        // Arreglo del recorrido.
        ArrayList<Integer> res = new ArrayList<>(n);

        // Hago bfs
        while(q.isEmpty() != false){
            int e = q.poll();
            res.add(e);
            visitados[e] = true;
            for(int v:adj.get(e)){
                if(visitados[v] == false){
                    q.offer(v);
                }
            }
        }

        // Comparo arreglos
        for(int i = 0; i < n; i++){
            if(res.get(i) != seq_bfs[i+1]){
                printer.println("No");
            }
        }
        printer.println("Yes");
        printer.close();
    }
}
