package kr.nanoit.message;

import kr.nanoit.message_Structure.PacketType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ClientMessageDto implements Message {

    private PacketType packetType;
    private MessageType message_type;
    private String sender_agent_id;
    private String to_phone_number;
    private String from_phone_number;
    private String message_content;

    @Override
    public String getMessageType() {
        return message_type.getProperty();
    }

    @Override
    public Integer getStatus() {
        return null;
    }
}
