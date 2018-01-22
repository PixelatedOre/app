package com.wojon.gerry.snabbjob;

import java.util.HashMap;
/**
 * Created by Gerry on 2017-12-27.
 */

public class PacketHandler {
    public static HashMap<Byte, Class> packets;
    public static enum PacketID{
        REGISTER(0),LOGIN(1);
        private byte id;
        PacketID(int id){
            this.id = (byte)id;
        }
        public byte getID(){
            return this.id;
        }
    }
    public static String getPacketName(byte id){
        for(PacketID packet: PacketID.values()){
            if(packet.getID() == id)
                return packet.name();
        }
        return "";
    }
    static {
        packets = new HashMap<Byte, Class>();
        packets.put((byte)1,PacketLogin.class);
        packets.put((byte)2,PacketLogin.class);
    }
    public static Packet getPacket(byte packetID) {
        try {
            return (Packet)packets.get(packetID).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
