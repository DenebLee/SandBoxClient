package kr.nanoit.client;

import kr.nanoit.dto.message_Structure.MessageType;
import kr.nanoit.dto.send.SmsMessageService;
import kr.nanoit.make_packet.MakeMessageServicePacket;
import kr.nanoit.main.TcpClient;
import kr.nanoit.socket.SocketUtil;
import kr.nanoit.util.Crypt;

import java.util.Random;

public class DbSearching implements Runnable {

    private final SocketUtil socketUtil;
    private SmsMessageService smsMessageService;
    private final Crypt crypt;

    private MakeMessageServicePacket makeMessageServicePacket;
    private final Random random;
    private String encrypkey = null;


    public DbSearching(SocketUtil socketUtil, SmsMessageService smsMessageService) {
        this.socketUtil = socketUtil;
        this.smsMessageService = smsMessageService;
        crypt = new Crypt();
        makeMessageServicePacket = new MakeMessageServicePacket();
        random = new Random();
        encrypkey = TcpClient.configuration.getString("auth.encryptkey.1");
    }

    @Override
    public void run() {

        /*
        *  불규칙하게 메시지 던지기 위해 Thread.sleep 시간 랜덤으로 설정
        */
        
        int min = 3000;
        int max = 10000;
        long threadTime = random.nextInt(max + min) + min;


        while (true) {
            try {
                if (smsMessageService != null) {
                    try {
                        Thread.sleep(threadTime);
                        InputSmsPacketData();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                socketUtil.socketClose();
            }

        }

    }
    
    /*
    * 실제로 DB에서 최신화된 값을 조회한 후 가져오기 때문에 해당 로직은 추후 삭제되어야 함
    */

    public void InputSmsPacketData() throws Exception {
        smsMessageService.setMessageServiceType(MessageType.SMS);
        smsMessageService.setProtocol("SEND");
        smsMessageService.setMessage_id("jeongseob");
        smsMessageService.setReceive_number(crypt.MessageDataEncrypt("01040811475", encrypkey));
        smsMessageService.setCallback_number(crypt.MessageDataEncrypt("01055552222", encrypkey));
        smsMessageService.setMsg(crypt.MessageDataEncrypt("Test", encrypkey));
        smsMessageService.setData_count("0");
        smsMessageService.setAttachment_data_type("1");
        smsMessageService.setAttachment_data_size("39");
        smsMessageService.setAttachment_data("");
        smsMessageService.setOrg_callback_number(crypt.MessageDataEncrypt("01044442222", encrypkey));
        smsMessageService.setBill_id(crypt.MessageDataEncrypt("id", encrypkey));

        socketUtil.getQueue_for_Send().offer(smsMessageService);
    }
}
