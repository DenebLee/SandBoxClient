package kr.nanoit.decode;

import kr.nanoit.dto.send.IndexSendAck;
import kr.nanoit.dto.send.LengthSendAck;

public class DecoderReport {

    public String resultCode(byte[] byteOfReceive) {
        return (new String(byteOfReceive, (IndexSendAck.INDEX_SEND_ACKNOWLEDGEMENT_RESULT_CODE), LengthSendAck.LENGTH_SEND_ACKNOWLEDGEMENT_RESULT_CODE)).trim();
    }

    public String messageKey(byte[] byteOfReceive) {
        return (new String(byteOfReceive, (IndexSendAck.INDEX_SEND_ACKNOWLEDGEMENT_MESSAGE_ID), LengthSendAck.LENGTH_SEND_ACKNOWLEDGEMENT_MESSAGE_ID)).trim();
    }

    public String messageType(byte[] byteOfReceiveData) {
        return (new String(byteOfReceiveData, (IndexSendAck.INDEX_SEND_ACKNOWLEDGEMENT_MESSAGE_TYPE), LengthSendAck.LENGTH_SEND_ACKNOWLEDGEMENT_MESSAGE_TYPE)).trim();
    }
}
