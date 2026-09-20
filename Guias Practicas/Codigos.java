import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.ArrayList;

public class Codigos {

    public int dfsVisit(ArrayList<ArrayList<Integer>> adj, ArrayList<ArrayList<Integer>> bosque, Boolean[] visitados, int nodo, int[][] vida, int tiempo){
        tiempo++; 
        visitados[nodo] = true;
        vida[nodo][0]=tiempo;   // descubro el nodo, anoto su tiempo de descubrimiento
        for(int v: adj.get(nodo)){  // Para cada vecino, no visitado implica descubierto, aplico la recursión y lo agrego al bosque.
            if(!visitados[v]){
                bosque.get(nodo).add(v);
                tiempo = dfsVisit(adj, bosque, visitados, nodo, vida, tiempo);
            }
        }
        tiempo++;
        vida[nodo][1]=tiempo; // nodo procesado.
        return tiempo;
    }

    public ArrayList<ArrayList<Integer>> bfs(ArrayList<ArrayList<Integer>> adj){
        int n = adj.size(); // |V(adj)|
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            res.add(new ArrayList<>());
        }
        int[][] vida = new int[n][2]; // vida[nodo][0] = tiempo_desc y vida[nodo][1]= tiempo_procesado
        Boolean[] visitados = new Boolean[n];
        int tiempo = 0;
        
        for(int i = 0; i<n; i++){
            if(!visitados[i]){
                tiempo = dfsVisit(adj, res, visitados, n, vida, tiempo); // como tiempo se pasa como parametro, la manera de salvar la sincronizacion es que retorne el valor.
            }
        }
        
        return res;
    }
    public static int exp(int a, int b){
        if(b == 0){
            return 1;
        }
        else{
            int res = exp(a, b/2);
            if(b % 2 == 0){
                return res * res;
            }
            else{
                return a * res * res;
            }
        }
    }
    
    /* Represento el arbol sobre un heap
    Para la posición i del arreglo si abb[i] == 1 entonces hay un nodo, 0 si no lo hay:
    - hijo izquierdo = (2*i) + 1
    - hijo derecho = (2*i) + 2
    - padre = (i-1) / 2
    */
    public static int maxCamino(int[] abb){
        
        return 0;
    } 
    public static void main(String[] args) {
        System.out.println(exp(2, 8));
        System.out.println(exp(2, 9));
    }    
}
