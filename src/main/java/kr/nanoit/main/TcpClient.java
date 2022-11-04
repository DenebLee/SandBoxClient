package kr.nanoit.main;

import kr.nanoit.client.DbSearching;
import kr.nanoit.client.SendPacket;
import kr.nanoit.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TcpClient {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");

    public static void main(String[] args) throws ConfigurationException {
        try {
            log.info("[CLIENT] START {}", SIMPLE_DATE_FORMAT.format(new Date()));


            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress("localhost", 55555);
            socket.connect(socketAddress);

            SocketUtil socketUtil = new SocketUtil(socket);

            // 패킷 만들어주기 -> ClientMessageDto에 while문으로 계속 set
            Thread thread = new Thread(new DbSearching(socketUtil));
            thread.setName("MakePacket-Thread");


//            Thread thread2 = new Thread(new ReceivePacket(socketUtil, smsMessageService));
//            thread2.setName("Receive-Thread");

            Thread thread3 = new Thread(new SendPacket(socketUtil));
            thread3.setName("Send-Thread");
//
            thread.start();
//            thread2.start();
            thread3.start();

//
//            Runtime.getRuntime().addShutdownHook(new Thread(() -> log.info("[HTTPCLIENT] STOP {}", SIMPLE_DATE_FORMAT.format(new Date()))));
        } catch (Exception e) {
            log.error("IOException error occurred", e);
        }
    }
}
