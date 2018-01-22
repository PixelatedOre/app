package com.wojon.gerry.snabbjob;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Gerry on 2017-12-27.
 */

public class PacketLogin extends Packet{
    public byte valid;

    @Override
    public void readMessage(DataInputStream inStream) throws IOException {
        valid = inStream.readByte();
    }

    @Override
    public void sendMessage(DataOutputStream outStream, PacketData data) throws IOException {
        outStream.writeByte(PacketHandler.PacketID.LOGIN.getID());
        outStream.writeUTF(data.readString());
        outStream.writeUTF(data.readString());
        data.resetReader();
        Tools.Log("packet sent!!!!");
    }

    @Override
    public void executePacket(NetHandler netHandler) {
        netHandler.handlePacketLogin(this);
    }
}
