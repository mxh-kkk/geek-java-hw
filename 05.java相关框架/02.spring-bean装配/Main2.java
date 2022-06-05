import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Main2 {

  /**
   * 将方法的返回值声明为bean
   * @return
   */
  @Bean
  public TestService testService() {
    return new TestService();
  }
}
