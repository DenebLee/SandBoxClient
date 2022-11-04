package kr.nanoit.client;

import kr.nanoit.message.ClientMessageDto;
import kr.nanoit.message.MessageType;
import kr.nanoit.message_Structure.PacketType;
import kr.nanoit.socket.SocketUtil;

import java.util.Random;

public class DbSearching implements Runnable {

    private final SocketUtil socketUtil;

    private final Random random;


    public DbSearching(SocketUtil socketUtil) {
        this.socketUtil = socketUtil;
        random = new Random();
    }

    @Override
    public void run() {

        int min = 3000;
        int max = 10000;
        long threadTime = random.nextInt(max + min) + min;

        while (true) {
            try {
                Thread.sleep(threadTime);
                InputSmsPacketData();
            } catch (Exception e) {
                e.printStackTrace();
                socketUtil.socketClose();
                break;
            }
        }
    }

    public void InputSmsPacketData() {
        ClientMessageDto clientMessageDto = new ClientMessageDto();
        clientMessageDto.setPacketType(PacketType.SEND);
        clientMessageDto.setMessage_type(MessageType.SMS);
        clientMessageDto.setSender_agent_id("1122");
        clientMessageDto.setFrom_phone_number("01044859595");
        clientMessageDto.setTo_phone_number("02028488595");
        clientMessageDto.setMessage_content("클라이언트에서 보내는 메시지 입니다 확인바랍니다");
        socketUtil.getQueue_for_Send().offer(clientMessageDto);
    }
}
