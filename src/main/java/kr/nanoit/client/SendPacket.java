package kr.nanoit.client;

import kr.nanoit.dto.message_Structure.MessageService;
import kr.nanoit.dto.send.SmsMessageService;
import kr.nanoit.make_packet.MakeMessageServicePacket;
import kr.nanoit.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SendPacket implements Runnable {

    private final SocketUtil socketUtil;
    private final SmsMessageService smsMessageService;
    private MakeMessageServicePacket makeMessageServicePacket;


    public SendPacket(SocketUtil socketUtil, SmsMessageService smsMessageService) {
        smsMessageService = new SmsMessageService();
        this.socketUtil = socketUtil;
        this.smsMessageService = smsMessageService;
        makeMessageServicePacket = new MakeMessageServicePacket();
    }

    @Override
    public void run() {
        while (true) {
            try {
                MessageService messageService = socketUtil.getQueue_for_Send().poll(1000, TimeUnit.MICROSECONDS);
                if (messageService != null) {
                    if (messageService instanceof SmsMessageService) {
                        SmsMessageService smsMessageService = (SmsMessageService) messageService;
                        if (socketUtil.write(makeMessageServicePacket.EncodeSms(smsMessageService))) {
                            log.info("[TCP_CLIENT] SEND SUCCESS MESSAGE-TYPE : '{}' MESSAGE-ID : '{}' MESSAGE-RECEIVE-NUM : '{}' MESSAGE-CALLBACK-NUM : '{}' MESSAGE : '{}' "
                                    , smsMessageService.getMessageServiceType(), smsMessageService.getMessage_id(), smsMessageService.getReceive_number(),
                                    smsMessageService.getCallback_number(), smsMessageService.getMsg());
                        }else{
                            log.info("[TCP_CLIENT] SEND FAIL MESSAGE-TYPE : '{}' MESSAGE-ID : '{}' MESSAGE-RECEIVE-NUM : '{}' MESSAGE-CALLBACK-NUM : '{}' MESSAGE : '{}' "
                                    , smsMessageService.getMessageServiceType(), smsMessageService.getMessage_id(), smsMessageService.getReceive_number(),
                                    smsMessageService.getCallback_number(), smsMessageService.getMsg());
                            socketUtil.socketClose();
                        }

                    }
//                }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("[TCPCLIENT] SendPacket is FAIL ", e);
                socketUtil.socketClose();
            }

        }
    }
}





