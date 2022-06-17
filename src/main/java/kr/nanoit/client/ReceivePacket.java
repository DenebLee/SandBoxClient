package kr.nanoit.client;

import kr.nanoit.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceivePacket implements Runnable{

    private final SocketUtil socketUtil;


    public ReceivePacket(SocketUtil socketUtil) {
        this.socketUtil = socketUtil;
    }

    @Override
    public void run() {
        log.info("[SendPacket-Thread START");
        while (true) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
}
