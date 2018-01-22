package com.wojon.gerry.snabbjob;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerry on 2017-12-23.
 */

public class Client  {
    public String name="Kalle";
    private DataInputStream inStream;
    private DataOutputStream outStream;
    boolean isRunning = false;
    private Socket socket;
    public NetHandler netHandler;
    private MessageHandler messageHandler;

    private ArrayList<Packet> packetsSent = new ArrayList<Packet>();
    public Client(MessageHandler messageHandler){
        netHandler = new NetHandler();
        this.isRunning = true;
        this.messageHandler = messageHandler;
    }
    public DataInputStream getInputStream(){
        return inStream;
    }
    public DataOutputStream getOutStream(){
        return this.outStream;
    }
    public void init(){
        try {

            socket = new Socket("192.168.1.3",7070);
            //socket = new Socket("82.196.112.130",7070);
            this.inStream = new DataInputStream(socket.getInputStream());
            this.outStream = new DataOutputStream(socket.getOutputStream());
            loop();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private void loop() throws IOException, IllegalAccessException, InstantiationException {
        int oldMsg = 1;
        this.socket.setSoTimeout(5000);
        while(isRunning){
            if(inStream.available() <= 0) continue;
            Packet packet =  PacketHandler.getPacket(inStream.readByte());
            packet.readMessage(inStream);
            removeRecivedPacketFromList(packet);
            messageHandler.onMessageReceive(packet);
        }

    }
    public void removeRecivedPacketFromList(Packet packet){
        for(int i = 0; i < packetsSent.size(); i++){
            if(packetsSent.get(i).packetID == packet.packetID){
                packetsSent.remove(i);
                break;
            }
        }
    }
    public void sendPacket(PacketData packetData){
        try {
            Packet packet = PacketHandler.getPacket(packetData.getPacketID());
            packetsSent.add(packet);
            packet.sendMessage(outStream, packetData);
            outStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public interface MessageHandler{
        public void onMessageReceive(Packet packet);
    }
}
