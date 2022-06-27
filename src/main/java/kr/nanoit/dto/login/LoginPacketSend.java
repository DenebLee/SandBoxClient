package kr.nanoit.dto.login;

import kr.nanoit.make_packet.MakeLoginPacket;
import kr.nanoit.main.TcpClient;
import kr.nanoit.socket.SocketUtil;
import kr.nanoit.util.EndPoint;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPacketSend {

    private final SocketUtil socketUtil;
    private MakeLoginPacket makeLoginPacket;
    private String ID;
    private String PASSWORD;
    private String VERSION;

    public LoginPacketSend(SocketUtil socketUtil) {
        this.socketUtil = socketUtil;
        makeLoginPacket = new MakeLoginPacket();
    }

    public void Login_PK_Send() {
        ID = TcpClient.configuration.getString("auth.id.1");
        PASSWORD = EndPoint.getPASSWORD();
        VERSION = TcpClient.configuration.getString("auth.server.version");

        try {
            if (socketUtil.write(makeLoginPacket.login(ID, PASSWORD, VERSION))){
            log.info("[HTTP_CLIENT] LOGIN_PACKET SEND [SUCCESS] ID : '{}' PASSWORD : '{}' VERSION : '{}' ", ID, PASSWORD, VERSION);}
        } catch (Exception e) {
            log.warn("[HTTP_CLIENT] LOGIN_PACKET SEND [FAIL]", e);

        }
    }


}

