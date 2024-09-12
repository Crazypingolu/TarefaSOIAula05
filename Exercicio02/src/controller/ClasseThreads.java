package controller;

// Trazer as bibliotecas e pacotes:
import java.util.concurrent.Semaphore;
import java.util.Random;

public class ClasseThreads extends Thread {
    // Definir as variáveis privadas:
    private int idPrato;
    private Semaphore semaforo;
    
    // Gerar construtores:
    public ClasseThreads(int idPrato, Semaphore semaforo){
        this.idPrato = idPrato;
        this.semaforo = semaforo;
    }

    // Método de cozimento:
    public void cozindo(int indx, String prato){
        Random rad = new Random();
        int t;
        double porc;
        if (indx % 2 == 0){
            t = rad.nextInt(300) + 500;
        } else {
            t = rad.nextInt(600) + 600;
        }
        for (int i = 100; i <= t; i += 100) {
            try {
                sleep(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            porc = (i * 100) / t;
            System.out.println("#"+ indx + prato + ": " + porc + "% concluída");
        }
        System.out.println("#"+ indx + prato + ": está pronta para entrega!");
    }

    // Método entrega:
    public void entrega(int id, String prato) {
        try {
            sleep(500);
            System.out.println("#" + id + prato + ": Foi entregue!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        String prato = "";
        if (idPrato % 2 == 0){
            prato = " Lasanha a Bolonhesa";
        } else {
            prato = " Sopa de Cebola";
        }
        cozindo(idPrato, prato);
        try {
            semaforo.acquire();
            entrega(idPrato, prato);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }
}
