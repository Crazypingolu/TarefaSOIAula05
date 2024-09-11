package view;
import java.util.concurrent.Semaphore;
import controller.ClasseThreads;

public class ClassePrincipal {
    public static void main(String[] args) {
        int perm = 1;
        Semaphore semaforo = new Semaphore(perm);
        
        for (int i = 0; i < 22; i++) {
            ClasseThreads t = new ClasseThreads(i, semaforo);
            t.start();
        }
    }
}
