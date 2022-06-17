package kr.nanoit.main;

import kr.nanoit.dto.login.LoginPacketSend;
import kr.nanoit.http.HttpConnect;
import kr.nanoit.socket.SocketUtil;
import kr.nanoit.util.EndPoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TcpClient {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
    public static Configuration configuration;

    public static void main(String[] args) throws ConfigurationException, IOException {
        final Configurations configurations = new Configurations();
        configuration = configurations.properties(new File("src/main/java/resources/ClientValue.properties"));

        try {
            log.info("[HTTPCLIENT] START {}",SIMPLE_DATE_FORMAT.format(new Date()));

            HttpConnect httpConnect = new HttpConnect();

            /*
            * Http 통신 시작
            */

            httpConnect.Connection();


            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(EndPoint.getIP(), Integer.parseInt(EndPoint.getPORT()));
            socket.connect(socketAddress);

            /*
            * 각 쓰레드 동시 동작 작업 메소드 유틸 class 선언
            */
            SocketUtil socketUtil = new SocketUtil(socket);
            
            /*
            * LOGIN_PACKET 보내기
            */

            LoginPacketSend loginPacketSend = new LoginPacketSend(socketUtil);
            loginPacketSend.Login_PK_Send();


//            Thread thread = new Thread(new SendPacket(socketUtil));
//            thread.setName("Send-Thread");
//
//            Thread thread2 = new Thread(new ReceivePacket(socketUtil));
//            thread.setName("Receive-Thread");
//
//            thread.start();
//            thread2.start();
//
//            Runtime.getRuntime().addShutdownHook(new Thread(() -> log.info("[HTTPCLIENT] STOP {}", SIMPLE_DATE_FORMAT.format(new Date()))));
        } catch (Exception e) {
            log.error("IOException error occurred", e);
        }
    }
}
