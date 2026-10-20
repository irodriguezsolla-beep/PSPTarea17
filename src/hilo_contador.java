public class hilo_contador extends Thread {
    private String texto;
    private String cons;
    private int repeticionConstante;

    // Variable compartida por todas las instancias (hilos) de la clase
    private static int totalCaracteres = 0;

    public hilo_contador(String cons, String texto) {
        super("Contador-" + cons);
        this.cons = cons;
        this.texto = texto;
    }

    // Méodo estático y sincronizado para modificar la variable compartida de forma segura
    public static synchronized void sumarAlTotal(int cantidad) {
        totalCaracteres += cantidad;
    }

    // Getter estático para leer el total acumulado por todos los hilos
    public static synchronized int getTotalCaracteres() {
        return totalCaracteres;
    }

    @Override
    public void run() {
        int contador = 0;
        int posicion = 0;

        while ((posicion = texto.indexOf(cons, posicion)) != -1) {
            contador++;
            posicion += cons.length();
        }

        // Asignación individual del hilo
        this.repeticionConstante = contador;

        // Suma al acumulador global sincronizado
        sumarAlTotal(contador);
    }

    public synchronized int getRepeticionConstante() {
        return repeticionConstante;
    }

    public String getCons() {
        return cons;
    }
}