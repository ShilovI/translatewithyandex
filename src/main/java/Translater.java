import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by Administrator on 7/14/2017. Hello%20World!
 */
public class Translater {
    static final String host = "https://translate.yandex.net";
    static final String source = "/api/v1.5/tr/translate?";
    static final String  APIkey = "key=trnsl.1.1.20170714T140502Z.3085b0d53b5185e2.46eb9e50a78ba111ddd15ed41999d0377ffc8266";

    public static String connection(String s) throws IOException {
        String sInUTF = URLEncoder.encode(s, "UTF-8");
        String requestTo = host + source + APIkey;

        URL urlObj = new URL(requestTo);

        HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        String request = "text=" + sInUTF;
        request = request + "&lang=en-ru"; //Translate direction
        request = request + "&[format=plain]"; //Text format
        request = request + "&[options=1]";

        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes(request);

        InputStream response = connection.getInputStream();
        Scanner scanner = new Scanner(response);
        String xml = "";
        while (scanner.hasNext()) {
            xml = xml + scanner.nextLine();
        }

        String[] strings = xml.split("text>");
        String[] strings1 = strings[1].split("</");
        String result = strings1[0];

        return result;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(connection("Hello World!"));
    }
}
