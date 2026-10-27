package Talleres;
public class Taller3_1{
    public int[] merge(int[] izq, int[] der){
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
    public int[] mergeSort(int[] arr){
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
    public static void main(String[] args) {
        
    }
}