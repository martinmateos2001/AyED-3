package Talleres;
import java.io .*;
import java.util .*;

/*
Para leer datos: BufferedReader + Scanner (importados de java.io y java.util)
Para imprimir datos: BufferedWriter + PrintWriter
*/
public class Taller1 {
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
        
        //Puesto en práctica, supongo que es la entrada de mi problema a resolver.
        printer.println("Ingrese la cantidad de socio-vendedores");
        printer.flush();
        int entrada = scanner.nextInt(); // En el taller la primera entrada es la cantidad de nodos.

        // Ahora vienen las relaciones. Las guardo en un arreglo.
        ArrayList<Integer> padres = new ArrayList<Integer>(entrada);
        for(int i=0; i < entrada; i++){
            printer.println("Ingrese el superior inmediato del nodo " + i);
            printer.flush();
            int p = scanner.nextInt(); // El padre del nodo i es p o en terminos del ejercicio p revende a i
            padres.add(i, p); 
        }
        printer.println("la lista de superiores es:");
        for(int i = 0; i<entrada;i++){
            printer.println(padres.get(i));
        }
        printer.close();
        scanner.close();

        /*Ya escaneé los datos que necesito, ahora busco la profundidad.
        La profundidad de un arbol con n nodos es log(n).
        Como busco la máxima profundidad que existe dentro de los arboles de los nodos el problema escala a n.log(n).
        */
    }
}
