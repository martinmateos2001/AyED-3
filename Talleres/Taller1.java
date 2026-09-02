package Talleres;
import java.io .*;
import java.util .*;

/*
Para leer datos: BufferedReader + Scanner (importados de java.io y java.util)
Para imprimir datos: BufferedWriter + PrintWriter
*/
public class Taller1 {

    /* Devuelve el nivel del nodo o profundidad a la que está.
    Modifica la lista auxiliar colocando un 1, indicando que el nodo ya se recorrió.
     */
    public static int nivel(int nodo, int[] ls, int[] niveles){
        int p = ls[nodo]; // p es el superior de nodo.
        if (ls[nodo] == -1){ // Si es raiz entonces no aporta
            return 0;
        }
        if(niveles[nodo] != 0){ // Si fue calculado es distinto de cero o es una raíz que en el proximo paso se calcula en O(1)
            return niveles[nodo];
        }
        // si no fue caculado le sumo 1 mas la cantidad de superirores que tenga, en cada recursion tambien guardo el nivel del nodo.
        niveles[nodo] = 1 + nivel(p, ls, niveles);
        return niveles[nodo];
    }
    public static void main(String[] args) {
        /* Con esto segun el apunte puedo imprimir
        System.out -> salida a consola

        OutputStreamWriter -> Traduce los char o String formato que usa la consola

        BufferedWriter -> guarda el string en memoria temporal y lo vuelca de lleno en la consola

        PrintWriter -> da formato al texto del BufferedWriter con metodos como println() o print()
        Luego de utilizar los print es necesario usar los metodos .close() para que se vuelque en 
        memoria el texto acumulado en el buffer y el printer se cierre. Si es necesario mostrarlo 
        en la mitad de un proceso se usa .flush(), así el texto se vuelca y nos permite seguir
        usando el printer.
         */
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter printer = new PrintWriter(bw);

        /* Con esto puedo recopilar datos
        BufferedReader acumula el texto proporcionado por la consola en RAM.
        Scanner procesa los datos acumulados por el BufferedReader y los traduce al
        tipo de varible que se precise .nextInt(), nextLong()
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(br);
        
        /* Puesto en práctica, supongo que es la entrada de mi problema a resolver.
        printer.println("Ingrese la cantidad de socio-vendedores");
        printer.flush(); 
        */
        int n = scanner.nextInt(); // En el taller la primera entrada es la cantidad de nodos.

        // Ahora vienen las relaciones. Las guardo en un arreglo.
        int[] superiores = new int[n+1]; // Vienen numerados del 1 al n incluido.
        for(int i=1; i < n+1; i++){
            /*
            printer.println("Ingrese el superior inmediato del nodo " + i);
            printer.flush();
            */
            int p = scanner.nextInt(); // El padre del nodo i es p o en terminos del ejercicio p revende a i
            superiores[i] = p; 
        }
        /* 
        printer.println("la lista de superiores es:");
        for(int i = 0; i<n;i++){
            printer.println(superiores[i]);
        }
        */
        
        // no necesito recopilar mas nada.
        scanner.close(); 

        /*Ya escaneé los datos que necesito, ahora busco la profundidad.
        La profundidad de un arbol con n nodos es log(n).
        Como busco la máxima profundidad que existe dentro de los arboles de los nodos el problema escala a n.log(n).
        Esto es un poco a la fuerza bruta, ¿como lo puedo mejorar?
        En una lista auxiliar indico los nodos procesados, y tomo un contador de nodos procesados porque un arbol tiene n-1
        aristas para n nodos, lo que siguiere que como maximo hay n-1 niveles. Cuando res >= r, con r los nodos restantes, el arbol
        posible que exista tendra r-1 aristas y por lo tanto ya no habrá un nivel mas profundo que res.
        Esta idea está pues corta caminos. Si un nodo ya fue procesado y luego existe un nodo mas abajo cuando el algoritmo suba lo
        va a cortar.
        Si aprovecho la recursión para calcular el nivel de todos los nodos procesados, en vez de cortar simplemente sumo los niveles.
        */
        int[] niveles = new int[n+1];
        int res = 0;
        for(int i = 1; i < n+1; i++){ // Para cada nodo del 0 al n-1
            int i_nivel = nivel(i, superiores, niveles); // Calculo el nivel
            if (res < i_nivel){ // si el nivel de i es mayor que el encontrado entonces lo actualizo.
                res = i_nivel;
            }
        }
        printer.println(res);
        printer.close();

    }
}
