import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.Locale;

public class BingMapsApi {

    private static final String BING_MAPS_API_URL = "https://dev.virtualearth.net/REST/v1/Imagery/Map/Road/";

    private final OkHttpClient client = new OkHttpClient();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public interface BingMapsCallback {
        void onResponse(Response response) throws IOException;

        void onFailure(IOException e);
    }

    public void getStaticMapImage(double latitude, double longitude, int zoom, BingMapsCallback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BING_MAPS_API_URL).newBuilder();
        urlBuilder.addPathSegment(String.format(Locale.US, "%.6f,%.6f", latitude, longitude));
        urlBuilder.addQueryParameter("zoom", String.valueOf(zoom));
        urlBuilder.addQueryParameter("mapSize", "500,300");
        urlBuilder.addQueryParameter("key", "YOUR_BING_MAPS_API_KEY");

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.post(() -> {
                    try {
                        callback.onResponse(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(() -> callback.onFailure(e));
            }
        });
    }
}
