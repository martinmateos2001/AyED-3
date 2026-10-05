package Guias_Practicas;    
import java.util.Random;
public class CodigosTests {
    public static int ejercitoDelReyFB(int n){
        if(n == 1 || n == 0){
            return 1;
        }
        return ejercitoDelReyFB(n-1) + ejercitoDelReyFB(n-2);
    }
    public static void testEjercitoDelRey(){
        System.out.println("Inicio test EjercitoDelRey");
        Codigos Cod = new Codigos();
        Random random = new Random();
        for(int test=0; test < 100; test++){
            int n = random.nextInt(1000);
            int esperado = ejercitoDelReyFB(n);
            int obtenido = Cod.ejercitoDelRey(n);
            assert esperado == obtenido : "Fallo en test " + test + " con n=" + n;
        }
        System.out.println("Exito");
    }
    public static void main(String[] args) {
        testEjercitoDelRey();
    }
}
