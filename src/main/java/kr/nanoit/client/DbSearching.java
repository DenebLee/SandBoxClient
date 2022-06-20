package kr.nanoit.client;

import kr.nanoit.dto.message_Structure.MessageType;
import kr.nanoit.dto.send.SmsMessageService;
import kr.nanoit.encode.EncodeMessageService;
import kr.nanoit.main.TcpClient;
import kr.nanoit.socket.SocketUtil;
import kr.nanoit.util.Crypt;

import java.util.Random;

public class DbSearching implements Runnable {

    private final SocketUtil socketUtil;
    private SmsMessageService smsMessageService;
    private final Crypt crypt;

    private EncodeMessageService encodeMessageService;
    private final Random random;
    private String encrypkey = null;


    public DbSearching(SocketUtil socketUtil, SmsMessageService smsMessageService) {
        this.socketUtil = socketUtil;
        this.smsMessageService = smsMessageService;
        crypt = new Crypt();
        encodeMessageService = new EncodeMessageService();
        random = new Random();
        encrypkey = TcpClient.configuration.getString("auth.encryptkey.1");
    }

    @Override
    public void run() {
        long threadTime = random.nextInt(50000 - 2000 + 2000);
        while (true) {
            if (smsMessageService != null) {
                try {
//                    Thread.sleep(threadTime);
                    InputSmsPacketData();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void InputSmsPacketData() throws Exception {
        smsMessageService.setMessageServiceType(MessageType.SMS);
        smsMessageService.setMessage_id("jeongseob");
        smsMessageService.setReceive_number(crypt.MessageDataEncrypt("01040811475", encrypkey));
        smsMessageService.setCallback_number(crypt.MessageDataEncrypt("01055552222", encrypkey));
        smsMessageService.setMsg(crypt.MessageDataEncrypt("양선호 바보", encrypkey));
        smsMessageService.setData_count("0");
        smsMessageService.setAttachment_data_type("1");
        smsMessageService.setAttachment_data_size("39");
        smsMessageService.setAttachment_data("");
        smsMessageService.setOrg_callback_number("01044442222");
        smsMessageService.setBill_id("ID");

        socketUtil.getQueue_for_Send().offer(smsMessageService);
    }
}
