package kamianowski.maciej.demo.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class GithubService {

    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    public Uni<Collection<AuthMsg>> getRepositories(String username) {
        return Uni.createFrom().item(() -> fetchRepositories(username));
    }

    private Collection<AuthMsg> fetchRepositories(String username) {
        String url = GITHUB_API_URL + username + "/repos";
        String data = getJSON(url);

        if (data == null) {
            return null;
        }

        Type collectionType = new TypeToken<Collection<AuthMsg>>() {}.getType();
        return new Gson().fromJson(data, collectionType);
    }

    private String getJSON(String url) {
        HttpURLConnection connection = null;
        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.connect();

            int status = connection.getResponseCode();

            if (status == 200 || status == 201) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Malformed URL: " + url, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "I/O Exception while fetching data", ex);
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error while disconnecting", ex);
                }
            }
        }
        return null;
    }
}
