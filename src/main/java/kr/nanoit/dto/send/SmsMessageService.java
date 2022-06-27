package kr.nanoit.dto.send;

import kr.nanoit.dto.message_Structure.MessageService;
import kr.nanoit.dto.message_Structure.MessageType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsMessageService implements MessageService {

    private MessageType messageServiceType;
    private String serverMessageId;
    private String send_time;
    private String protocol;


    private String result_code;
    private String message_id;
    private String receive_number;
    private String callback_number;
    private String msg;
    private String data_count;
    private String attachment_data_type;
    private String attachment_data_size;
    private String attachment_data;
    private String org_callback_number;
    private String bill_id;



    @Override
    public String getMessageServiceType() {
        return messageServiceType.getProperty();
    }
}
