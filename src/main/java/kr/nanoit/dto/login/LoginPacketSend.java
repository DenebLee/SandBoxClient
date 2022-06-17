package kr.nanoit.dto.login;

import kr.nanoit.main.TcpClient;
import kr.nanoit.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPakcetSend {

    private final SocketUtil socketUtil;
    private LoginPacket loginPacket;
    private  String ID;
    private String PASSWORD;
    private  String VERSION;

    public LoginPakcetSend(SocketUtil socketUtil) {
        this.socketUtil = socketUtil;


        loginPacket = new LoginPacket();
    }

    public void Login_PK_Send() {
        ID = TcpClient.configuration.getString("auth.id.1");
        PASSWORD = TcpClient.configuration.getString("auth.password.1");
        VERSION = TcpClient.configuration.getString("auth.server.version");

        try {
            socketUtil.write(loginPacket.login(ID, PASSWORD,VERSION));
            log.info("[HTTPCLIENT] LOGIN_PACKET SEND [SUCCESS] ID : {} PASSWORD : {} VERSION : {}", ID, PASSWORD, VERSION);
        } catch (Exception e) {
            log.warn("[HTTPCLIENT] LOGIN_PACKET SEND [FAIL]");

        }
    }


}

