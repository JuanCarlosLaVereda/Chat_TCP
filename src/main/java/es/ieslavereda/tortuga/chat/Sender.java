package es.ieslavereda.tortuga.chat;

import java.io.IOException;
import java.io.ObjectOutputStream;

public interface Sender<T> extends Runnable {
    default void send(T message, ObjectOutputStream oos){
        new Thread(() -> {
            try {
                oos.writeObject(message);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
