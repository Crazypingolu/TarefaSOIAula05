package controller;
import java.util.concurrent.Semaphore;
import java.util.Random;
public class ClasseThreads extends Thread {
    // variáveis:
    private int idBancarrio;
    private Semaphore semaforo;
    
    public ClasseThreads(int idBancarrio, Semaphore semaforo){
        this.idBancarrio = idBancarrio;
        this.semaforo = semaforo;
    }

    public void calc(int resto, int idBancarrio, int indx) {
        Random rad = new Random();
        int t;
        if (resto == 1){
            t = rad.nextInt(800) + 200;
        } else if (resto == 2) {
            t = rad.nextInt(1000) + 500;
        } else { // resto == 0
            t = rad.nextInt(1000) + 1000;
        }
        try {
            sleep(t);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("#" + idBancarrio + ": Cálculo " + (indx + 1) + "concluído!");
        }
    }

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
            System.out.println("#" + idBancarrio + ": Transação " + (indx + 1) + " concluída!");
        }
    }

    @Override
    public void run(){
        int resto = idBancarrio % 3;
        int loop = 0;
        if (resto == 1){
            loop = 2;
        } else {
            loop = 3;
        }
        for (int i = 0; i < loop; i++) {
            calc(resto, idBancarrio, i);
            try {
                semaforo.acquire();
                tran(resto, idBancarrio, i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }
    }
}
