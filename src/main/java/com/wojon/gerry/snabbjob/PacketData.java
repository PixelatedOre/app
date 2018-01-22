package com.wojon.gerry.snabbjob;

import java.util.ArrayList;

/**
 * Created by Gerry on 2017-12-28.
 */

public class PacketData {
    public ArrayList<Byte> bytes = new ArrayList<Byte>();
    public ArrayList<String> strings = new ArrayList<String>();
    public ArrayList<Integer> ints = new ArrayList<Integer>();
    public ArrayList<Float> floats = new ArrayList<Float>();
    private int[] readedValues = new int[]{0,0,0,0};
    private PacketHandler.PacketID packetID;
    public PacketData(PacketHandler.PacketID packetID){
        this.packetID = packetID;
    }
    public byte getPacketID(){
        return this.packetID.getID();
    }
    public void addString(String value){
        strings.add(value);
    }
    public void addInt(int value){
        ints.add(value);
    }
    public void addByte(byte value){
        bytes.add(value);
    }
    public void addFloat(float value){
        floats.add(value);
    }
    public String readString(){
        readedValues[1] ++;
        return strings.get(readedValues[1] - 1);
    }
    public byte readByte(){
        readedValues[0] ++;
        return bytes.get(readedValues[0] - 1);
    }
    public int readInt(){
        readedValues[2] ++;
        return ints.get(readedValues[2] - 1);
    }
    public float readFloat(){
        readedValues[3] ++;
        return floats.get(readedValues[3] - 1);
    }
    public void resetReader(){
        readedValues = new int[]{0,0,0,0};
    }

}
