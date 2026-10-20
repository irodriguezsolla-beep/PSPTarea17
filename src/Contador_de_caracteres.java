import java.io.*;
import java.util.Scanner;

public class Contador_de_caracteres {
    public static void main(String[] args) {
        // Pedir consonantes
        Scanner scanner = new Scanner(System.in);
        String[] listaconstantes = new String[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Di una constante, constante actual: " + (i + 1));
            String constante = scanner.nextLine();
            if (constante.length() == 1 && Character.isLetter(constante.charAt(0)) && !"aeiouAEIOU".contains(constante)) {
                listaconstantes[i] = constante;
            } else {
                System.out.println("Da un valor valido (una sola consonante).");
                i--;
            }
        }

        // Añadir texto
        System.out.println("Pasame el texto: ");
        String text = scanner.nextLine();

        File texto = new File("texto.txt");
        try (FileWriter agregar = new FileWriter(texto)) {
            agregar.write(text + "\n");
            System.out.println("Texto agregado");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }

        // Leer texto
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader leer = new BufferedReader(new FileReader(texto))) {
            String linea;
            while ((linea = leer.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        String textoCompleto = contenido.toString();

        // Crear e iniciar hilos
        hilo_contador[] hilos = new hilo_contador[5];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new hilo_contador(listaconstantes[i], textoCompleto);
            hilos[i].start();
        }

        // Esperar a que terminen todos los hilos
        for (int i = 0; i < 5; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Hilo interrumpido: " + e.getMessage());
            }
        }

        // Mostrar resultados individuales
        System.out.println("RESULTADOS");
        for (int i = 0; i < 5; i++) {
            System.out.println("Consonante " + hilos[i].getCons() + ": " + hilos[i].getRepeticionConstante() + " repeticiones.");
        }

        // Obtener el total acumulado de forma segura mediante el método estático del hilo
        System.out.println("SUMA TOTAL DE CARACTERES ENCONTRADOS: " + hilo_contador.getTotalCaracteres());

        scanner.close();
    }
}