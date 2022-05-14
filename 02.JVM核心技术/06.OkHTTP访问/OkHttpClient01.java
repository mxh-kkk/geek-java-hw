import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

@Slf4j
public class OkHttpClient01 {

    @SneakyThrows
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://127.0.0.1:8001").build();
        Call call = client.newCall(request);
        Response response = call.execute();
        log.info(response.body().string());
    }
}
