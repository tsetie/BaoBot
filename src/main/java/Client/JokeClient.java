package Client;

import Model.Joke;
import okhttp3.*;
import com.google.gson.Gson;
import java.io.IOException;

public class JokeClient {
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    public String JokeGetRequest(){
        String url = "https://official-joke-api.appspot.com/random_joke";
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            Joke joke = gson.fromJson(response.body().string(), Joke.class);
            return joke.toString();

        } catch(IOException e){
            e.printStackTrace();
        }
        return "Something didn't work girly pop!!!";
    }
}
