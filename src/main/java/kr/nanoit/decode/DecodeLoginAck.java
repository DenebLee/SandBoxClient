package kr.nanoit.decode;


import kr.nanoit.dto.login.IndexLoginAck;
import kr.nanoit.dto.login.LengthLoginAck;

public class DecodeLoginAck {

    public String resultCode(byte[] byteOfReceive) {
        return (new String(byteOfReceive, (IndexLoginAck.COMMON_INDEX_LOGIN_ACKNOWLEDGEMENT_RESULT_CODE), LengthLoginAck.LENGTH_LOGIN_ACK_RESULT_CODE)).trim();
    }

    public String smsTps(byte[] byteOfReceive) {
        return (new String(byteOfReceive, (IndexLoginAck.COMMON_INDEX_LOGIN_ACKNOWLEDGEMENT_SMS_TPS), LengthLoginAck.LENGTH_LOGIN_ACK_SMS_TPS)).trim();
    }

    public String lmsTps(byte[] byteOfReceive) {
        return (new String(byteOfReceive, (IndexLoginAck.COMMON_INDEX_LOGIN_ACKNOWLEDGEMENT_LMS_TPS), LengthLoginAck.LENGTH_LOGIN_ACK_LMS_TPS)).trim();
    }

    public String mmsTps(byte[] byteOfReceive) {
        return (new String(byteOfReceive, (IndexLoginAck.COMMON_INDEX_LOGIN_ACKNOWLEDGEMENT_MMS_TPS), LengthLoginAck.LENGTH_LOGIN_ACK_LMS_TPS)).trim();
    }

    public String katTps(byte[] byteOfReceive) {
        return (new String(byteOfReceive, (IndexLoginAck.COMMON_INDEX_LOGIN_ACKNOWLEDGEMENT_KAT_TPS), LengthLoginAck.LENGTH_LOGIN_ACK_KAT_TPS)).trim();
    }

    public String kftTps(byte[] byteOfReceive) {
        return (new String(byteOfReceive, (IndexLoginAck.COMMON_INDEX_LOGIN_ACKNOWLEDGEMENT_KFT_TPS), LengthLoginAck.LENGTH_LOGIN_ACK_KFT_TPS)).trim();
    }

    public String gmsTps(byte[] byteOfReceive) {
        return (new String(byteOfReceive, (IndexLoginAck.COMMON_INDEX_LOGIN_ACKNOWLEDGEMENT_GMS_TPS), LengthLoginAck.LENGTH_LOGIN_ACK_GMS_TPS)).trim();
    }


}

