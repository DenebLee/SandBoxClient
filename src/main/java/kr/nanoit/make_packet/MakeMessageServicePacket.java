package kr.nanoit.make_packet;

import kr.nanoit.dto.message_Structure.IndexHeader;
import kr.nanoit.dto.message_Structure.PacketType;
import kr.nanoit.dto.send.IndexSend;
import kr.nanoit.dto.send.SmsMessageService;

import java.util.Arrays;

import static kr.nanoit.dto.message_Structure.LengthHeader.LENGTH_HEADER_PACKET_TYPE_INDEX;

public class MakeMessageServicePacket {
    public byte[] EncodeSms(SmsMessageService smsMessageService) {
        byte[] data = new byte[IndexSend.INDEX_SEND_FULL];

        Arrays.fill(data, 0, data.length, BYTE_SPACE);
        String bodySize = Integer.toString(data.length - IndexHeader.INDEX_HEADER_FULL_LENGTH);
        /*
         * Header
         */
        System.arraycopy(PacketType.SEND.getBytes(), 0, data, LENGTH_HEADER_PACKET_TYPE_INDEX, PacketType.SEND.getBytes().length);
        System.arraycopy(bodySize.getBytes(), 0, data, IndexHeader.INDEX_HEADER_BODY_LEN, bodySize.getBytes().length);

        /*
         * Body
         */
        System.arraycopy(smsMessageService.getMessageServiceType().getBytes(), 0, data, IndexSend.INDEX_MESSAGE_TYPE, smsMessageService.getMessageServiceType().getBytes().length);
        System.arraycopy(smsMessageService.getMessage_id().getBytes(), 0, data, IndexSend.INDEX_SEND_MESSAGE_ID, smsMessageService.getMessage_id().getBytes().length);
        System.arraycopy(smsMessageService.getReceive_number().getBytes(), 0, data, IndexSend.INDEX_SEND_RECEIVE_NUMBER, smsMessageService.getReceive_number().getBytes().length);
        System.arraycopy(smsMessageService.getCallback_number().getBytes(), 0, data, IndexSend.INDEX_SEND_CALLBACK_NUMBER, smsMessageService.getCallback_number().getBytes().length);
        System.arraycopy(smsMessageService.getMsg().getBytes(), 0, data, IndexSend.INDEX_SEND_MESSAGE, smsMessageService.getMsg().getBytes().length);
        System.arraycopy(smsMessageService.getData_count().getBytes(), 0, data, IndexSend.INDEX_SEND_DATA_COUNT, smsMessageService.getData_count().getBytes().length);
        System.arraycopy(smsMessageService.getAttachment_data_type().getBytes(), 0, data, IndexSend.INDEX_SEND_ATTACHMENT_DATA_TYPE, smsMessageService.getAttachment_data_type().getBytes().length);
        System.arraycopy(smsMessageService.getAttachment_data_size().getBytes(), 0, data, IndexSend.INDEX_SEND_ATTACHMENT_DATA_SIZE, smsMessageService.getAttachment_data_size().getBytes().length);
        System.arraycopy(smsMessageService.getAttachment_data().getBytes(), 0, data, IndexSend.INDEX_SEND_ATTACHMENT_DATA, smsMessageService.getAttachment_data().getBytes().length);
        System.arraycopy(smsMessageService.getOrg_callback_number().getBytes(), 0, data, IndexSend.INDEX_SEND_ORG_CALLBACK_NUMBER_SIZE, smsMessageService.getOrg_callback_number().getBytes().length);
        System.arraycopy(smsMessageService.getBill_id().getBytes(), 0, data, IndexSend.INDEX_SEND_BILL_ID, smsMessageService.getBill_id().getBytes().length);

        return data;
    }

    public static final byte BYTE_SPACE = ' ';
}
