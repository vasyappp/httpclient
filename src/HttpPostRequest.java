import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpPostRequest {
    static String sXML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:spel=\"http://speller.yandex.net/services/spellservice\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <spel:CheckTextRequest lang=\"en\" options=\"8\" format=\"\">\n" +
            "         <spel:text>to bee or nots to be</spel:text>\n" +
            "      </spel:CheckTextRequest>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://speller.yandex.net/services/spellservice");

        StringEntity strEnt = new StringEntity(sXML);
        httpPost.setEntity(strEnt);

        httpPost.addHeader("Accept-Encoding", "gzip,deflate");
        httpPost.addHeader("Content-Type", "text/xml;charset=UTF-8\n");
        httpPost.addHeader("SOAPAction", "http://speller.yandex.net/services/spellservice/checkText");
        httpPost.addHeader("Host", "speller.yandex.net");
        httpPost.addHeader("Connection", "Keep-Alive");
        httpPost.addHeader("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");

        CloseableHttpResponse resp1 = httpClient.execute(httpPost);

        System.out.println(EntityUtils.toString(resp1.getEntity()));
    }
}
