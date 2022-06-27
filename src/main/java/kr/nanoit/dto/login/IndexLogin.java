package kr.nanoit.dto.login;

import kr.nanoit.dto.message_Structure.IndexHeader;

public class IndexLogin {
    /**
     * The constant COMMON_INDEX_LOGIN_ID.
     */
    public static int INDEX_LOGIN_ID = IndexHeader.INDEX_HEADER_FULL_LENGTH;
    /**
     * The constant COMMON_INDEX_LOGIN_PASSWORD.
     */
    public static int INDEX_LOGIN_PASSWORD = INDEX_LOGIN_ID + LengthLogin.LENGTH_LOGIN_ID;
    /**
     * The constant COMMON_INDEX_LOGIN_VERSION.
     */
    public static int INDEX_LOGIN_VERSION = INDEX_LOGIN_PASSWORD + LengthLogin.LENGTH_LOGIN_PASSWORD;
    /**
     * The constant COMMON_INDEX_LOGIN_FULL_LENGTH.
     */
    public static int INDEX_LOGIN_FULL_LENGTH = INDEX_LOGIN_VERSION + LengthLogin.LENGTH_LOGIN_VERSION;
}