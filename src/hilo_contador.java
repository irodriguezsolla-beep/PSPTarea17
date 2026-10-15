
public class hilo_contador extends Thread {
    String texto;
    String cons;
    int repeticionConstante;

    public hilo_contador(String cons, String texto){
        super("Contador-" + cons);
        this.cons = cons;
        this.texto = texto;
    }
    @Override
    public void run(){

        int contador = 0;
        int posicion = 0;

        while ((posicion = texto.indexOf(cons, posicion)) != -1) {
            contador++;
            posicion += cons.length();
        }

        this.repeticionConstante = contador;

    }
    public int getRepeticionConstate() {
        return repeticionConstante;
    }

    public String getCons() {
        return cons;
    }
}

