import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> task = () -> {
            System.out.println("one " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
            System.out.println("two " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
            System.out.println("three " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
            return 3;
        };
        ExecutorService service = Executors.newFixedThreadPool(4, r -> new Thread(r, "MySupperThread"));
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            tasks.add(task);
        }
        service.invokeAny(tasks);
        service.shutdown();
    }
}
