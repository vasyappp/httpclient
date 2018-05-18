import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpPostRequest {
    static String sXML1 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:spel=\"http://speller.yandex.net/services/spellservice\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <spel:CheckTextRequest lang=\"en\" options=\"8\" format=\"\">\n" +
            "         <spel:text>to bee or nots to be</spel:text>\n" +
            "      </spel:CheckTextRequest>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    static String sXML2 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:spel=\"http://speller.yandex.net/services/spellservice\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <spel:CheckTextRequest lang=\"ru\" options=\"8\" format=\"\">\n" +
            "         <spel:text>шла саша по шосе</spel:text>\n" +
            "      </spel:CheckTextRequest>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost1 = new HttpPost("https://speller.yandex.net/services/spellservice");

        StringEntity strEnt1 = new StringEntity(sXML1);
        httpPost1.setEntity(strEnt1);

        httpPost1.addHeader("Accept-Encoding", "gzip,deflate");
        httpPost1.addHeader("Content-Type", "text/xml;charset=UTF-8\n");
        httpPost1.addHeader("SOAPAction", "http://speller.yandex.net/services/spellservice/checkText");
        httpPost1.addHeader("Host", "speller.yandex.net");
        httpPost1.addHeader("Connection", "Keep-Alive");
        httpPost1.addHeader("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");

        CloseableHttpResponse resp1 = httpClient.execute(httpPost1);

        System.out.println(EntityUtils.toString(resp1.getEntity()));
        System.out.println("\n");

        HttpPost httpPost2 = new HttpPost("https://speller.yandex.net/services/spellservice");

        StringEntity strEnt2 = new StringEntity(sXML2);
        httpPost2.setEntity(strEnt2);

        httpPost2.addHeader("Accept-Encoding", "gzip,deflate");
        httpPost2.addHeader("Content-Type", "text/xml;charset=UTF-8\n");
        httpPost2.addHeader("SOAPAction", "http://speller.yandex.net/services/spellservice/checkText");
        httpPost2.addHeader("Host", "speller.yandex.net");
        httpPost2.addHeader("Connection", "Keep-Alive");
        httpPost2.addHeader("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");

        CloseableHttpResponse resp2 = httpClient.execute(httpPost2);

        System.out.println(EntityUtils.toString(resp2.getEntity()));

    }
}
