package server.messages.creator;

import server.ChatUser;
import server.ChatUserManager;
import server.history.History;
import server.messages.Message;
import server.messages.types.ExitMessage;
import server.messages.types.HistoryMessage;
import server.messages.types.RenameMessage;
import server.messages.types.SenderMessage;

import java.time.LocalDateTime;

public class MessageFabric {
    private static ChatUserManager chatUserManager;

    public static void setChatUserManager(ChatUserManager chatUserManager) {
        MessageFabric.chatUserManager = chatUserManager;
    }

    public static Message getMessage(LocalDateTime dateTime, ChatUser chatUser, String text) {
        if (text == null) {
            return new ExitMessage(dateTime, chatUser, chatUserManager);
        }
        String command = text.split(" ")[0];

        Message message;

        switch (command) {
            case "/snd":
                message = new SenderMessage(dateTime, chatUser, text.substring(5), chatUserManager);
                break;
            case "/hist":
                message = new HistoryMessage(dateTime, chatUser, chatUserManager);
                break;
            case "/chid":
                message = new RenameMessage(dateTime, chatUser, text.substring(6), chatUserManager);
                break;
            case "/exit":
                message = new ExitMessage(dateTime, chatUser, chatUserManager);
                break;
            default:
                message = null;
                break;
        }
        return message;
    }
}