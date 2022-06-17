package kr.nanoit.client;

import kr.nanoit.socket.SocketUtil;

public class SendPacket implements Runnable{

    private final SocketUtil socketUtil;



    public SendPacket(SocketUtil socketUtil) {
        this.socketUtil = socketUtil;
    }

    @Override
    public void run() {

    }}


