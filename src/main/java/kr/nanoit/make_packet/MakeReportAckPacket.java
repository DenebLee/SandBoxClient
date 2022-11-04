//package kr.nanoit.make_packet;
//
//import kr.nanoit.dto.message_Structure.IndexHeader;
//import kr.nanoit.dto.message_Structure.PacketType;
//import kr.nanoit.dto.report.IndexReportAck;
//import kr.nanoit.dto.send.SmsMessageService;
//
//import java.util.Arrays;
//
//import static kr.nanoit.dto.message_Structure.LengthHeader.LENGTH_HEADER_PACKET_TYPE_INDEX;
//import static kr.nanoit.make_packet.MakeMessageServicePacket.BYTE_SPACE;
//
//public class MakeReportAckPacket {
//    public byte[] report(SmsMessageService smsMessageService) {
//        byte[] data = new byte[IndexReportAck.INDEX_REPORT_ACK_FULL_LENGTH];
//
//        Arrays.fill(data, 0, data.length, BYTE_SPACE);
//        String bodySize = Integer.toString(data.length - IndexHeader.INDEX_HEADER_FULL_LENGTH);
//
//        /*
//          Header (packetType + bodyLength)
//         */
//
//        System.arraycopy(PacketType.REPORT.getBytes(), 0, data, LENGTH_HEADER_PACKET_TYPE_INDEX, PacketType.REPORT.getBytes().length);
//        System.arraycopy(bodySize.getBytes(), 0, data, IndexHeader.INDEX_HEADER_BODY_LEN, bodySize.getBytes().length);
//
//        /*
//          Body (resultCode + messageType +  serverMessage id)
//         */
//        System.arraycopy(smsMessageService.getResult_code().getBytes(), 0, data, IndexReportAck.INDEX_REPORT_ACK_RESULT_CODE, smsMessageService.getResult_code().getBytes().length);
//        System.arraycopy(smsMessageService.getMessageServiceType().getBytes(), 0, data, IndexReportAck.INDEX_REPORT_ACK_MESSAGE_TYPE, smsMessageService.getMessageServiceType().getBytes().length);
//        System.arraycopy(smsMessageService.getServerMessageId().getBytes(), 0, data, IndexReportAck.INDEX_REPORT_ACK_SERVER_MESSAGE_ID, smsMessageService.getServerMessageId().getBytes().length);
//        return data;
//    }
//}
