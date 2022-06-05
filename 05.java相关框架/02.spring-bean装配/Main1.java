import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.mxh.mybaitsdemo.service.OtherTestService;
import com.mxh.mybaitsdemo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注解声明方式
 */
@Service
@Slf4j
public class Main1 {

    @Autowired
    private TestService testService;
    @Autowired
    private OtherTestService otherTestService;

    @DSTransactional
    public void test() {
        String data = testService.test();
        String data1 = otherTestService.test();

        log.info(data);
        log.info(data1);
    }
}
