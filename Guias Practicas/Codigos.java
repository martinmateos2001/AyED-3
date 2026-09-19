import java.util.ArrayList;

public class Codigos {
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
