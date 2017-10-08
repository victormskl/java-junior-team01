package server;

import server.commands.Command;
import server.commands.creator.CommandFabric;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ChatUser {
    private String roomName = "default";
    private String name = "";
    private BufferedWriter out;
    private BufferedReader in;

    public ChatUser(BufferedWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }


    public void send(String message) throws IOException {
        synchronized (out) {
            out.write(message);
            out.newLine();
            out.flush();
        }
    }

    public Command getMessage(LocalDateTime dateTime) throws IOException {
        return CommandFabric.getMessage(dateTime, this, in.readLine());
    }

    public void exit() {

    }

    public String getName() {
        return name;
    }
    
    public void rename(String name) throws IOException {
        this.name = name;
        synchronized (out) {
            out.write("Your name is " + name);
            out.newLine();
            out.flush();
        }
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
