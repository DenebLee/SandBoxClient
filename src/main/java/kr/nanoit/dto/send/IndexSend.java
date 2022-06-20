package kr.nanoit.dto.send;


import kr.nanoit.dto.message_Structure.IndexHeader;

/**
 * The type Index send.
 */
public class IndexSend {
    /**
     * The constant BIZ_INDEX_SEND_MESSAGE_TYPE.
     */
    public static int INDEX_MESSAGE_TYPE = IndexHeader.INDEX_HEADER_FULL_LENGTH;
    /**
     * The constant BIZ_INDEX_SEND_MESSAGE_ID.
     */
    public static int INDEX_SEND_MESSAGE_ID = INDEX_MESSAGE_TYPE + LengthSend.LENGTH_SEND_MESSAGE_TYPE;
    /**
     * The constant BIZ_INDEX_SEND_RECEIVE_NUMBER.
     */
    public static int INDEX_SEND_RECEIVE_NUMBER = INDEX_SEND_MESSAGE_ID + LengthSend.LENGTH_SEND_MESSAGE_ID;
    /**
     * The constant BIZ_INDEX_SEND_CALLBACK_NUMBER.
     */
    public static int INDEX_SEND_CALLBACK_NUMBER = INDEX_SEND_RECEIVE_NUMBER + LengthSend.LENGTH_SEND_RECEIVE_NUMBER;
    /**
     * The constant BIZ_INDEX_SEND_MESSAGE.
     */
    public static int INDEX_SEND_MESSAGE = INDEX_SEND_CALLBACK_NUMBER + LengthSend.LENGTH_SEND_CALLBACK_NUMBER;
    /**
     * The constant BIZ_INDEX_SEND_DATA_COUNT.
     */
    public static int INDEX_SEND_DATA_COUNT = INDEX_SEND_MESSAGE + LengthSend.LENGTH_SEND_MESSAGE;
    /**
     * The constant BIZ_INDEX_SEND_ATTACHMENT_DATA_TYPE.
     */
    public static int INDEX_SEND_ATTACHMENT_DATA_TYPE = INDEX_SEND_DATA_COUNT + LengthSend.LENGTH_SEND_DATA_COUNT;
    /**
     * The constant BIZ_INDEX_SEND_ATTACHMENT_DATA_SIZE.
     */
    public static int INDEX_SEND_ATTACHMENT_DATA_SIZE = INDEX_SEND_ATTACHMENT_DATA_TYPE + LengthSend.LENGTH_SEND_ATTACHMENT_DATA_TYPE;
    /**
     * The constant BIZ_INDEX_SEND_ATTACHMENT_DATA.
     */
    public static int INDEX_SEND_ATTACHMENT_DATA = INDEX_SEND_ATTACHMENT_DATA_SIZE + LengthSend.LENGTH_SEND_ATTACHMENT_DATA_SIZE;
    /**
     * The constant BIZ_INDEX_SEND_ATTACHMENT_DATAB_SIZE.
     */

    public static int INDEX_SEND_ORG_CALLBACK_NUMBER_SIZE = INDEX_SEND_ATTACHMENT_DATA + LengthSend.LENGTH_SEND_ATTACHMENT_DATA;

    public static int INDEX_SEND_BILL_ID = INDEX_SEND_ORG_CALLBACK_NUMBER_SIZE + LengthSend.LENGTH_SEND_ORG_CALLBACK_NUMBER;

    /**
     * The constant BIZ_INDEX_SEND_FULL.
     */
    public static int INDEX_SEND_FULL = INDEX_SEND_BILL_ID + LengthSend.LENGTH_SEND_BILL_ID;
}