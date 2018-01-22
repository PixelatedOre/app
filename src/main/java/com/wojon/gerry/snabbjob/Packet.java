package com.wojon.gerry.snabbjob;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Gerry on 2017-12-27.
 */

public abstract class Packet {
    int packetID = -1;
    public abstract void readMessage(DataInputStream inStream) throws IOException;
    public abstract void sendMessage(DataOutputStream outStream, PacketData data)throws IOException;
    public abstract void executePacket(NetHandler netHandler);
}
