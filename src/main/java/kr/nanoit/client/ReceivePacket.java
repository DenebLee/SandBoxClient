package kr.nanoit.client;

import kr.nanoit.decode.DecodeLoginAck;
import kr.nanoit.decode.DecodeSendACk;
import kr.nanoit.decode.DecoderReport;
import kr.nanoit.dto.send.SmsMessageService;
import kr.nanoit.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceivePacket implements Runnable {

    private final SocketUtil socketUtil;
    private final DecodeLoginAck decodeLoginAck;
    private final DecodeSendACk decodeSendACk;
    private final DecoderReport decoderReport;

    private final SmsMessageService smsMessageService;


    public ReceivePacket(SocketUtil socketUtil, SmsMessageService smsMessageService) throws Exception {
        this.socketUtil = socketUtil;
        this.smsMessageService = smsMessageService;

        decodeLoginAck = new DecodeLoginAck();
        decodeSendACk = new DecodeSendACk();
        decoderReport = new DecoderReport();


    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] receiveByte = socketUtil.receiveByte();
                if (receiveByte != null) {

                    // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    switch (socketUtil.getPacketType(receiveByte)) {
                        case SEND_ACKNOWLEDGEMENT:
                            send_Ack_Receive(receiveByte);
                            break;

                        case ALIVE_ACKNOWLEDGEMENT:
                            break;

                        case LOGIN_ACKNOWLEDGEMENT:
                            login_Ack_Receive(receiveByte);
                            break;

                        case REPORT:
                            report_Ack_Receive(receiveByte);
                            break;

                    }


                }
                // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void login_Ack_Receive(byte[] receiveByte) throws Exception {
        log.info("[TCP_CLIENT] Login SUCCESS! PACKET_TYPE : '{}' RESULT_CODE : '{}' SMS_TPS : '{}' LMS_TPS : '{}' MMS_TPS : '{}' KAT_TPS : '{}' KFT_TPS : '{}' GMS_TPS : '{}'",
                socketUtil.getPacketType(receiveByte), decodeLoginAck.resultCode(receiveByte), decodeLoginAck.smsTps(receiveByte), decodeLoginAck.lmsTps(receiveByte),
                decodeLoginAck.mmsTps(receiveByte), decodeLoginAck.katTps(receiveByte), decodeLoginAck.kftTps(receiveByte), decodeLoginAck.gmsTps(receiveByte));
    }


    public void send_Ack_Receive(byte[] receiveByte) {
        log.info("[TCP_CLIENT] Send Receive SUCCESS! RESULT_CODE : '{}' MESSAGE_TYPE : '{}' MESSAGE_ID : '{}' ", decodeSendACk.resultCode(receiveByte), decodeSendACk.messageType(receiveByte), decodeSendACk.messageId(receiveByte));
    }

    public void report_Ack_Receive(byte[] receiveByte) {
        log.info("[TCP_CLIENT] Report Receive SUCCESS! RESULT_CODE : '{}' MESSAGE_KEY : '{}' MESSAGE_TYPE : '{}'", decoderReport.resultCode(receiveByte), decoderReport.messageKey(receiveByte), decoderReport.messageType(receiveByte));
        smsMessageService.setResult_code(decoderReport.resultCode(receiveByte));
        smsMessageService.setMessageServiceType(decoderReport.messageType(receiveByte));
        smsMessageService.setResult_code(decoderReport.resultCode(receiveByte));
    }



}

