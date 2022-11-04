package kr.nanoit.message;


import java.io.Serializable;

public interface Message extends Serializable {

    String getMessageType();

    Integer getStatus();
}
