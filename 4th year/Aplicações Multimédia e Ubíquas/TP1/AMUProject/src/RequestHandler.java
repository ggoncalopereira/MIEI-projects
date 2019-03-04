import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;


public class RequestHandler {

	
    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
    	
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
	
}
