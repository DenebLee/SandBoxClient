package kr.nanoit.client;

import kr.nanoit.decode.DecodeLoginAck;
import kr.nanoit.decode.DecodeSendACk;
import kr.nanoit.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceivePacket implements Runnable {

    private final SocketUtil socketUtil;
    private final DecodeLoginAck decodeLoginAck;
    private final DecodeSendACk decodeSendACk;
    private byte[] receiveByte = null;


    public ReceivePacket(SocketUtil socketUtil) throws Exception {
        this.socketUtil = socketUtil;

        decodeLoginAck = new DecodeLoginAck();
        decodeSendACk = new DecodeSendACk();

        receiveByte = socketUtil.receiveByte();

    }

    @Override
    public void run() {
        log.info("[SendPacket-Thread START]");
        while (true) {
            try {
                if (receiveByte != null) {
                    // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    switch (socketUtil.getPacketType(receiveByte)) {
                        case SEND_ACKNOWLEDGEMENT:
                            send_Ack_Receive();
                            break;
                        case REPORT:
                            break;

                    }


                }
                // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void login_Ack_Receive() throws Exception {
        log.info("[HTTPCLIENT] Login SUCCESS! PACKET_TYPE : {} RESULT_CODE : {} SMS_TPS : {} LMS_TPS : {} MMS_TPS : {} KAT_TPS : {} KFT_TPS : {} GMS_TPS : {}",
                socketUtil.getPacketType(receiveByte), decodeLoginAck.resultCode(receiveByte), decodeLoginAck.smsTps(receiveByte), decodeLoginAck.lmsTps(receiveByte),
                decodeLoginAck.mmsTps(receiveByte), decodeLoginAck.katTps(receiveByte), decodeLoginAck.kftTps(receiveByte), decodeLoginAck.gmsTps(receiveByte));
    }


    public void send_Ack_Receive() {
        log.info("[HTTPCLIENT] Send Receive SUCCESS! RESULT_CODE : {} MESSAGE_TYPE : {} MESSAGE_ID : {}", decodeSendACk.resultCode(receiveByte), decodeSendACk.messageType(receiveByte), decodeSendACk.messageId(receiveByte));
    }

}

