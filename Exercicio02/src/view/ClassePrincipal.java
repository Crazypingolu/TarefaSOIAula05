package view;
// Trazer Bibliotecas e Pacotes
import java.util.concurrent.Semaphore;
import controller.ClasseThreads;
public class ClassePrincipal {
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1);
         // processamento + saída de dados:
         for (int i = 0; i < 5; i++) {
            ClasseThreads t = new ClasseThreads(i, semaforo);
            t.start();
        }
    }
}
