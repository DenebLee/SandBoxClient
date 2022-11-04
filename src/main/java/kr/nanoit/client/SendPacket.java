package kr.nanoit.client;

import kr.nanoit.make_packet.MakeMessageServicePacket;
import kr.nanoit.message.ClientMessageDto;
import kr.nanoit.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SendPacket implements Runnable {

    private final SocketUtil socketUtil;
    private MakeMessageServicePacket makeMessageServicePacket;


    public SendPacket(SocketUtil socketUtil) {
        this.socketUtil = socketUtil;
        makeMessageServicePacket = new MakeMessageServicePacket();
    }

    @Override
    public void run() {
        while (true) {
            try {
                ClientMessageDto clientMessageDto = socketUtil.getQueue_for_Send().poll(1000, TimeUnit.MICROSECONDS);
                if (clientMessageDto != null) {
                    if (socketUtil.write(makeMessageServicePacket.EncodeSms(clientMessageDto))) {
                        log.info("Test Success");
                    } else {
                        log.info("Test failed");
                        socketUtil.socketClose();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("[TCPCLIENT] SendPacket is FAIL ", e);
                socketUtil.socketClose();
            }

        }
    }
}





