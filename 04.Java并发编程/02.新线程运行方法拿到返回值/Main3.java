import lombok.SneakyThrows;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main3 {
  @SneakyThrows
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<String> future = executorService.submit(() -> "res");
    System.out.println(future.get());
    executorService.shutdown();
  }
}
