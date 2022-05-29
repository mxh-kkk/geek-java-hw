import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Main2 {
  @SneakyThrows
  public static void main(String[] args) {
    AtomicReference<String> result = new AtomicReference<>();
    CountDownLatch countDownLatch = new CountDownLatch(1);
    Thread thread = new Thread(() -> {
      result.set("");
      countDownLatch.countDown();
    });
    thread.start();
    countDownLatch.await();
    System.out.println(result.get());
  }
}
