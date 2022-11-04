//package kr.nanoit.http;
//
//import kr.nanoit.util.EndPoint;
//import kr.nanoit.util.URLMaker;
//import kr.nanoit.util.XmlParser;
//import lombok.extern.slf4j.Slf4j;
//import kr.nanoit.main.TcpClient;
//import kr.nanoit.util.Crypt;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//@Slf4j
//public class HttpConnect {
//
//    private final Crypt crypt;
//    private final URLMaker urlMaker;
//    HttpURLConnection conn;
//    private URL url;
//
//    private String address;
//
//    private String queryString;
//
//    private StringBuilder sb;
//    private BufferedReader bufferedReader;
//    private String serverResponseData;
//
//    private String responseData;
//    private XmlParser xmlParser;
//
//    public HttpConnect() throws IOException {
//        this.crypt = new Crypt();
//        urlMaker = new URLMaker();
//        sb = new StringBuilder();
//    }
//
//    public void Connection() {
//        String id = TcpClient.configuration.getString("auth.id.1");
//        String password = TcpClient.configuration.getString("auth.password.1");
//        address = TcpClient.configuration.getString("http.url");
//
//
//        /*
//        * password는 서버에서 aes128 알고리즘을 사용하여 복호화 하기에 aes128로 암호화 진행
//        */
//
//        try {
//            if (password != null) {
//                password = crypt.DataEncrypt(password, TcpClient.configuration.getString("auth.encryptkey.1"));
//                EndPoint.setPASSWORD(password);
//                log.info("[HTTP_CLIENT] Password Encrypt SUCCESS : '{}'" , password);
//            } else {
//                log.warn("[HTTP_CLIENT] Password encryption FAILED");
//            }
//
//
//
//            queryString = urlMaker.makeUrlParam(address, id, password);
//
//            url = new URL(queryString);
//            conn = (HttpURLConnection) url.openConnection();
//
//
//            try {
//                startHttp();
//                System.out.println("");
//                System.out.println("");
//                log.info("[HTTP_CLIENT] HttpCommunication START");
//                System.out.println("");
//                System.out.println("");
//            } catch (Exception e) {
//                stopHttp();
//                log.info("[HTTP_CLIENT] HttpCommunication FAILED");
//                e.printStackTrace();
//            }
//
//            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//
//            /*
//            * BufferedReader 로 한줄씩 responseData 에 읽어오고 StringBuilder 에 append
//            */
//
//            while ((responseData = bufferedReader.readLine()) != null) {
//                sb.append(responseData);
//            }
//            serverResponseData = sb.toString();
//            log.info("[HTTP_CLIENT] Value sent by server  : '{}' " , serverResponseData);
//
//
//            /*
//            * XML Parsing
//            */
//            xmlParser = new XmlParser(serverResponseData);
//            xmlParser.tst();
//
//
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void startHttp() {
//        try {
//            conn.setRequestMethod("POST");
//            conn.connect();
//            log.info("[HTTP_CLIENT] Http Request Type : '{}' Request Address : '{}' Request Data : '{}' ",conn.getRequestMethod(),address,queryString);
//            log.info("[HTTP_CLIENT] Http ResponseCode : '{}' ", conn.getResponseCode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void stopHttp() {
//        try {
//            conn.disconnect();
//            log.info("[HTTP_CLIENT] HttpClient Disconnect");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//}
