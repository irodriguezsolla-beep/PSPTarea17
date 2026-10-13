import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Contador_de_caracteres {
    public static void main(){

        Scanner scanner = new Scanner(System.in);
        String[] listaconstantes = new String[5];
        for (int i =0; i<5; i++){
            int faltantes = 5 - i;
            System.out.println("Di una costante, conatante actual: "+i+", costantes faltates: " + faltantes );
            String constante = scanner.nextLine();
            listaconstantes[i] = constante;
        }


        try(FileInputStream Texto = new FileInputStream("texto.txt");
            ObjectOutputStream text = new ObjectInputStream(Texto){
            text.repeat(2000);

        }

    }
}
