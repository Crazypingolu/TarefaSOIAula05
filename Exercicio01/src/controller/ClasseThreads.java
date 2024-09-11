package controller;
import java.util.concurrent.Semaphore;
import java.util.Random;
public class ClasseThreads extends Thread {
    // Definir variáveis (privadas da classe):
    private int idBancarrio;
    private Semaphore semaforo;
    // Gerar construtores:
    public ClasseThreads(int idBancarrio, Semaphore semaforo){
        this.idBancarrio = idBancarrio;
        this.semaforo = semaforo;
    }
    // Método para simular o calculo (baseado no index)
    public void calc(int resto, int idBancarrio, int indx) {
        Random rad = new Random();
        int t;
        if (resto == 1){
            t = rad.nextInt(800) + 200; // min = 0.2, max = 1s
        } else if (resto == 2) {
            t = rad.nextInt(1000) + 500; // min = 0.5s, max = 1.5s
        } else { // resto == 0
            t = rad.nextInt(1000) + 1000; // min = 1s, max = 2s
        }
        try {
            sleep(t);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("#" + idBancarrio + ":" + (indx + 1) + "º cálculo concluído!");
        }
    }
    // Método para simular a transação com BD
    public void tran(int resto, int idBancarrio, int indx) {
        int t;
        if (resto == 1){
            t = 1000;
        } else { // resto == 0 & resto == 2
            t = 1500;
        }
        try {
            sleep(t);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("#" + idBancarrio + ": " + (indx + 1) + "º transação concluída!");
        }
    }

    @Override
    public void run(){
        int resto = idBancarrio % 3; // Gera o fator de diferença para o processamento
        int loop = 0;
        if (resto == 1){
            loop = 2;
        } else { // resto == 0 && resto == 2
            loop = 3;
        }
        for (int i = 0; i < loop; i++) {
            calc(resto, idBancarrio, i); // Chama a simulação de cálculo:
            try {
                semaforo.acquire(); // Chama o semáforo
                tran(resto, idBancarrio, i); // Executa a simulação com BD
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaforo.release(); // Libera o semáforo
            }
        }
    }
}
