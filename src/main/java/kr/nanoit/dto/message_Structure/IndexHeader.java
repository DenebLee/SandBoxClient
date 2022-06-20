package kr.nanoit.dto.message_Structure;

public class IndexHeader {
    /**
     * 헤더 바디 사이즈
     */
    public static int INDEX_HEADER_BODY_LEN = LengthHeader.LENGTH_HEADER_PACKET_TYPE_INDEX + LengthHeader.LENGTH_HEADER_PACKET_TYPE;
    /**
     * 헤더 전체 길이
     */
    public static int INDEX_HEADER_FULL_LENGTH = INDEX_HEADER_BODY_LEN + LengthHeader.LENGTH_HEADER_BODY_LEN;

}