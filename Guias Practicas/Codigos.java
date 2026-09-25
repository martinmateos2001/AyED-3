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

    /* GP 4 Ej 11 
    public int contarInvAux(int[] A, int i, int j){
        if (i >= j){
            return 0;
        }
        if(A[i] <= A[j]){
            return 0 + contarInvAux(A, i, j-1);
        }
        else{
            return 1 + contarInvAux(A, i, j-1);
        }
    }
    public int contarInversiones(int[] A){ // NO ES N LOG N. :(
        int j = A.length - 1;
        int res = 0;
        for(int i = 0; i < j; i++){
            res += contarInvAux(A, i, j);
        }
        return res;
        
    }
    */
    /*Solucion Ej 11 GP4*/
    /**
     * Método principal que inicializa el arreglo temporal y 
     * llama al método recursivo.
     */
    public static long contarInversiones(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] temp = new int[n];
        return mergeSortYContar(A, temp, 0, n - 1);
    }

    private static long mergeSortYContar(int[] A, int[] temp, int izq, int der) {
        long invCount = 0;
        
        if (izq < der) {
            // Evita el desbordamiento (overflow) para arreglos extremadamente grandes
            int medio = izq + (der - izq) / 2;
            
            // Conteo en las mitades
            invCount += mergeSortYContar(A, temp, izq, medio);
            invCount += mergeSortYContar(A, temp, medio + 1, der);
            
            // Conteo cruzado durante la fusión
            invCount += mergeYContar(A, temp, izq, medio, der);
        }
        
        return invCount;
    }

    private static long mergeYContar(int[] A, int[] temp, int izq, int medio, int der) {
        int i = izq;         // Índice para el sub-arreglo izquierdo
        int j = medio + 1;   // Índice para el sub-arreglo derecho
        int k = izq;         // Índice para el arreglo temporal
        long invCount = 0;

        // Fusión de las dos mitades
        while (i <= medio && j <= der) {
            if (A[i] <= A[j]) {
                temp[k++] = A[i++];
            } else {
                // Se encontró una inversión: A[i] > A[j]
                temp[k++] = A[j++];
                // Se suman todos los elementos restantes del lado izquierdo
                invCount += (medio - i + 1);
            }
        }

        // Copiar los elementos restantes del lado izquierdo (si los hay)
        while (i <= medio) {
            temp[k++] = A[i++];
        }

        // Copiar los elementos restantes del lado derecho (si los hay)
        while (j <= der) {
            temp[k++] = A[j++];
        }

        // Sobrescribir el arreglo original con los elementos ordenados
        for (i = izq; i <= der; i++) {
            A[i] = temp[i];
        }

        return invCount;
    }


    
    public static void main(String[] args) {
        System.out.println(exp(2, 8));
        System.out.println(exp(2, 9));
    }    
}
