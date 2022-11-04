import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import java.io.IOException;
import java.net.UnknownHostException;

public class HttpFacade {

    private <T> Response executeRequest(CloseableHttpClient client, T httpType){
        try (CloseableHttpResponse httpResponse = client.execute((ClassicHttpRequest) httpType)) {
            HttpEntity httpEntity = httpResponse.getEntity();
            String body = "";
            if(httpEntity!=null){
                body=EntityUtils.toString(httpEntity);
            }
            return Response.createNewResponse(httpResponse.getCode(), body, httpResponse.getReasonPhrase());
        } catch (IOException | ParseException e) {
            if (e instanceof ParseException) {
                throw new RuntimeException("A problem occurred while parsing response content.");
            }
            if (e instanceof UnknownHostException) {
                throw new RuntimeException("Can't reach host at given URL. Invalid URL or no connection to network.");
            }
            throw new RuntimeException("A problem occurred while trying to execute the HTTP GET request. ", e);
        }
    }

    public Response request(String url, HTTPRequest type, String body) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            switch (type){
                case GET:
                    return executeRequest(httpClient, new HttpGet(url));
                case POST:
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setEntity(new StringEntity(body));
                    httpPost.setHeader("Accept", "application/json");
                    httpPost.setHeader("Content-type", "application/json");
                    return executeRequest(httpClient, httpPost);
                case PUT:
                    HttpPut httpPut = new HttpPut(url);
                    httpPut.setEntity(new StringEntity(body));
                    return executeRequest(httpClient, httpPut);
                case PATCH:
                    return executeRequest(httpClient, new HttpPatch(url));
                case DELETE:
                    return executeRequest(httpClient, new HttpDelete(url));
                default:
                    return Response.createNewResponse(-1, "No such HTTP Request: " + type,"Invalid Request");
            }
        } catch (IOException e) {
            throw new RuntimeException("A problem occurred opening the HTTP Client. ",e);
        }
    }
}
