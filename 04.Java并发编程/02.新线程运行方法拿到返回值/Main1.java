import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicReference;

public class Main1 {
  @SneakyThrows
  public static void main(String[] args) {
    AtomicReference<String> result = new AtomicReference<>();
    Thread thread = new Thread(() -> result.set("res"));
    thread.start();
    thread.join();
    System.out.println(result.get());
  }
}
