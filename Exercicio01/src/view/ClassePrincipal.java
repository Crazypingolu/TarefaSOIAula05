package view;
// Trazer Bibliotecas e Pacotes
import java.util.concurrent.Semaphore;
import controller.ClasseThreads;
public class ClassePrincipal {
    public static void main(String[] args) {
        // Definir variáveis:
        int perm = 1; // quantas Transações podem ser feitas simultaneamente
        Semaphore semaforo = new Semaphore(perm); // Criação do semáforo
        // processamento + saída de dados:
        for (int i = 0; i < 22; i++) {
            ClasseThreads t = new ClasseThreads(i, semaforo);
            t.start();
        }
    }
}
