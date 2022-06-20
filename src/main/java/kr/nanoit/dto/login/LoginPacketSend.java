package kr.nanoit.dto.login;

import kr.nanoit.main.TcpClient;
import kr.nanoit.socket.SocketUtil;
import kr.nanoit.util.EndPoint;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPacketSend {

    private final SocketUtil socketUtil;
    private LoginPacket loginPacket;
    private String ID;
    private String PASSWORD;
    private String VERSION;

    public LoginPacketSend(SocketUtil socketUtil) {
        this.socketUtil = socketUtil;


        loginPacket = new LoginPacket();
    }

    public void Login_PK_Send() {
        ID = TcpClient.configuration.getString("auth.id.1");
        PASSWORD = EndPoint.getPASSWORD();
        VERSION = TcpClient.configuration.getString("auth.server.version");

        try {
            if (socketUtil.write(loginPacket.login(ID, PASSWORD, VERSION))){
            log.info("[HTTPCLIENT] LOGIN_PACKET SEND [SUCCESS] ID : {} PASSWORD : {} VERSION : {} ", ID, PASSWORD, VERSION);}
        } catch (Exception e) {
            log.warn("[HTTPCLIENT] LOGIN_PACKET SEND [FAIL]", e);

        }
    }


}

