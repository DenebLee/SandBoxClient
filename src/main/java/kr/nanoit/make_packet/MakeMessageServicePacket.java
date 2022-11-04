package kr.nanoit.make_packet;

import kr.nanoit.message.ClientMessageDto;
import kr.nanoit.message_Structure.IndexHeader;
import kr.nanoit.message_Structure.LengthHeader;
import kr.nanoit.message_Structure.send.IndexSend;

import java.util.Arrays;

public class MakeMessageServicePacket {
    public byte[] EncodeSms(ClientMessageDto clientMessageDto) {
        byte[] data = new byte[IndexSend.INDEX_SEND_FULL];

        Arrays.fill(data, 0, data.length, BYTE_SPACE);
        String bodySize = Integer.toString(data.length - IndexHeader.COMMON_INDEX_HEADER_FULL_LENGTH);

        /*
         * Header
         */
        System.arraycopy(clientMessageDto.getPacketType().getBytes(), 0, data, LengthHeader.COMMON_LENGTH_HEADER_PACKET_TYPE_INDEX, clientMessageDto.getPacketType().getBytes().length);
        System.arraycopy(bodySize.getBytes(), 0, data, IndexHeader.COMMON_INDEX_HEADER_BODY_LEN, bodySize.getBytes().length);

        /*
         * Body
         */
        System.arraycopy(clientMessageDto.getMessageType().getBytes(), 0, data, IndexSend.INDEX_MESSAGE_TYPE, clientMessageDto.getMessageType().getBytes().length);
        System.arraycopy(clientMessageDto.getSender_agent_id().getBytes(), 0, data, IndexSend.INDEX_SENDER_AGENT_ID, clientMessageDto.getSender_agent_id().getBytes().length);
        System.arraycopy(clientMessageDto.getFrom_phone_number().getBytes(), 0, data, IndexSend.INDEX_FROM_PHONE_NUMBER, clientMessageDto.getFrom_phone_number().getBytes().length);
        System.arraycopy(clientMessageDto.getTo_phone_number().getBytes(), 0, data, IndexSend.INDEX_TO_PHONE_NUMBER, clientMessageDto.getFrom_phone_number().getBytes().length);
        System.arraycopy(clientMessageDto.getMessage_content().getBytes(), 0, data, IndexSend.INDEX_MESSAGE_CONTENT, clientMessageDto.getMessage_content().getBytes().length);

        return data;
    }

    public static final byte BYTE_SPACE = ' ';
}
