package kr.nanoit.make_packet;

import kr.nanoit.dto.login.IndexLogin;
import kr.nanoit.dto.message_Structure.IndexHeader;
import kr.nanoit.dto.message_Structure.PacketType;

import java.util.Arrays;

import static kr.nanoit.dto.message_Structure.LengthHeader.LENGTH_HEADER_PACKET_TYPE_INDEX;
import static kr.nanoit.make_packet.MakeMessageServicePacket.BYTE_SPACE;

public class MakeLoginPacket {

        public byte[] login(String id , String password , String version) {
            byte[] data = new byte[IndexLogin.INDEX_LOGIN_FULL_LENGTH];

            Arrays.fill(data, 0, data.length, BYTE_SPACE);
            String bodySize = Integer.toString(data.length - IndexHeader.INDEX_HEADER_FULL_LENGTH);

        /*
          Header (packetType + bodyLength)
         */

            System.arraycopy(PacketType.LOGIN.getBytes(), 0, data, LENGTH_HEADER_PACKET_TYPE_INDEX, PacketType.LOGIN.getBytes().length);
            System.arraycopy(bodySize.getBytes(), 0, data, IndexHeader.INDEX_HEADER_BODY_LEN, bodySize.getBytes().length);

        /*
          Body (id + password + version)
         */
            System.arraycopy(id.getBytes(), 0, data, IndexLogin.INDEX_LOGIN_ID, id.getBytes().length);
            System.arraycopy(password.getBytes(), 0, data, IndexLogin.INDEX_LOGIN_PASSWORD, password.getBytes().length);
            System.arraycopy(version.getBytes(), 0, data, IndexLogin.INDEX_LOGIN_VERSION, version.getBytes().length);
            return data;
        }
}
