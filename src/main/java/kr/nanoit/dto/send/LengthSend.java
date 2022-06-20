package kr.nanoit.dto.send;

/**
 * Write 할때, 각 필드들 길이 및 인덱스
 */
public class LengthSend {
    /*
     * 메시지 타입
     */
    public static int LENGTH_SEND_MESSAGE_TYPE = 5;
    /*
     * 보낸 메시지 아이디
     */
    public static int LENGTH_SEND_MESSAGE_ID = 20;
    /*
     * 보낸 응답 번호
     */
    public static int LENGTH_SEND_RECEIVE_NUMBER = 50;
    /*
     * 콜백 번호
     */
    public static int LENGTH_SEND_CALLBACK_NUMBER = 50;
    /*
     * 보낸 메시지 길이
     */
    public static int LENGTH_SEND_MESSAGE = 200;
    /*
     * 데이터 수
     */
    public static int LENGTH_SEND_DATA_COUNT = 1;
    /*
     * 첨부 파일 데이터 유형
     */
    public static int LENGTH_SEND_ATTACHMENT_DATA_TYPE = 1;
    /*
     * 첨부 파일 데이터 크기
     */
    public static int LENGTH_SEND_ATTACHMENT_DATA_SIZE = 10;
    /*
     * 첨부 파일 데이터
     */
    public static int LENGTH_SEND_ATTACHMENT_DATA = 0;
    /*
     *
     */
    public static int LENGTH_SEND_ORG_CALLBACK_NUMBER = 50;
    /*
     *
     */
    public static int LENGTH_SEND_BILL_ID = 50;
}