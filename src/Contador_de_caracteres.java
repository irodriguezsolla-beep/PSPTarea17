import java.io.*;
import java.util.Scanner;

public class Contador_de_caracteres {
     void main(){
        //Pedir consonantes
        Scanner scanner = new Scanner(System.in);
        String[] listaconstantes = new String[5];
        for (int i =0; i<5; i++){
            System.out.println("Di una costante, conatante actual: "+ i);
            String constante = scanner.nextLine();
            if (constante.length() == 1 && Character.isLetter(constante.charAt(0)) && !"aeiouAEIOU".contains(constante)) {
                listaconstantes[i] = constante;
            }else{
                System.out.println("Da un valor valido.");
                i--;
            }
        }

        //Añadir texto
        System.out.println("Pasame el texto: ");
        String text = scanner.nextLine();


        File texto = new File("texto.txt");
        try(FileWriter agregar = new FileWriter(texto,true)){

            agregar.write(text + "\n");
            System.out.println("Texto agregado");
        } catch (IOException e) {
            System.out.println("Error");
        }

         //Leer texto
         StringBuilder contenido = new StringBuilder();

         try (DataInputStream leer = new DataInputStream(new FileInputStream(texto))) {
             int byteLeido;
             // read() devuelve -1 cuando llega al final del archivo
             while ((byteLeido = leer.read()) != -1) {
                 contenido.append((char) byteLeido);
             }
         } catch (IOException e) {
             System.err.println("Error al leer el archivo: " + e.getMessage());
         }

         String textoCompleto = contenido.toString();

         //Crear hilos
        hilo_contador[] hilos = new hilo_contador[5];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new hilo_contador(listaconstantes[i], textoCompleto);
            hilos[i].start();
        }

        //suma de caracteres
        int sumaTotalCaracteres = 0;

        for (int i = 0; i < 5; i++) {
            try {
                hilos[i].join();
                sumaTotalCaracteres += hilos[i].getRepeticionConstate();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Hilo interrumpido: " + e.getMessage());
            }
        }

        System.out.println("RESULTADOS");
        for (int i = 0; i < 5; i++) {
            System.out.println("Constante " + hilos[i].getCons() + ": " + hilos[i].getRepeticionConstate() + " repeticiones.");
        }

        System.out.println("SUMA TOTAL DE CARACTERES ENCONTRADOS: " + sumaTotalCaracteres);
    }
}
