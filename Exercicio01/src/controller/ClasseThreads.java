package controller;
import java.util.concurrent.Semaphore;
public class ClasseThreads extends Thread {
    // variáveis:
    private int idBancarrio;
    private int resto;
    private Semaphore semaforo;
    // instância:
    ClasseMetodos met = new ClasseMetodos();
    
    public ClasseThreads(int idBancarrio, Semaphore semaforo){
        this.idBancarrio = idBancarrio;
        this.semaforo = semaforo;
    }

    @Override
    public void run(){
        resto = met.calculo(idBancarrio);
        switch (resto) {
            case 1: // resto == 1
                
                break;
            case 2: // resto == 2

                break;
            case 3: // resto == 0

                break;
        }
        try { 
            semaforo.acquire();
        } catch (Exception e){
            
        } finally {
            semaforo.release();
        }
    }

}
