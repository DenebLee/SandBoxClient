package kr.nanoit.dto.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

/*
*  혹시 모를 LOGIN_ACK 결과값 저장
*/

public class LoginResultMessage {

    private String login_Ack_Result_Code;
    private String login_Ack_Sms_Tps;
    private String login_Ack_Lms_Tps;
    private String login_Ack_Mms_Tps;
    private String login_Ack_Kat_Tps;
    private String login_Ack_Kft_Tps;
    private String login_Ack_Gms_Tps;

}
