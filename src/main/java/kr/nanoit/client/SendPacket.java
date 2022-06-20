package kr.nanoit.client;

import kr.nanoit.dto.message_Structure.MessageService;
import kr.nanoit.dto.send.SmsMessageService;
import kr.nanoit.encode.EncodeMessageService;
import kr.nanoit.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SendPacket implements Runnable {

    private final SocketUtil socketUtil;
    private final SmsMessageService smsMessageService;
    private EncodeMessageService encodeMessageService;


    public SendPacket(SocketUtil socketUtil, SmsMessageService smsMessageService) {
        smsMessageService = new SmsMessageService();
        this.socketUtil = socketUtil;
        this.smsMessageService = smsMessageService;
        encodeMessageService = new EncodeMessageService();
    }

    @Override
    public void run() {
        while (true) {
            try {
                MessageService messageService = socketUtil.getQueue_for_Send().poll(1000, TimeUnit.MICROSECONDS);
                if (messageService != null) {
                    if (messageService instanceof SmsMessageService) {
                        SmsMessageService smsMessageService = (SmsMessageService) messageService;
                        if (socketUtil.write(encodeMessageService.EncodeSms(smsMessageService))) {
                            log.info("[HTTPCLIENT] SEND SUCCESS MESSAGE-TYPE : {} MESSAGE-ID : {} MESSAGE-RECEIVE-NUM : {} MESSAGE-CALLBACK-NUM : {} MESSAGE : {} "
                                    , smsMessageService.getMessageServiceType(), smsMessageService.getMessage_id(), smsMessageService.getReceive_number(),
                                    smsMessageService.getCallback_number(), smsMessageService.getMsg());
                        }else{
                            log.info("[HTTPCLIENT] SEND FAIL MESSAGE-TYPE : {} MESSAGE-ID : {} MESSAGE-RECEIVE-NUM : {} MESSAGE-CALLBACK-NUM : {} MESSAGE : {} "
                                    , smsMessageService.getMessageServiceType(), smsMessageService.getMessage_id(), smsMessageService.getReceive_number(),
                                    smsMessageService.getCallback_number(), smsMessageService.getMsg());
                        }

                    }
//                }
                }
            } catch (Exception e) {
                log.warn("[HTTPCLIENT] SendPacket is FAIL ", e);
            }

        }
    }
}





