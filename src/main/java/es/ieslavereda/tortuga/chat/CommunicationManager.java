package es.ieslavereda.tortuga.chat;

import es.ieslavereda.tortuga.ClientController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class CommunicationManager implements Sender {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private InetAddress ip;
    private Reciever reciever;

    public CommunicationManager(Socket socket , Reciever reciever) {
        try {
            this.socket = socket;
            this.reciever = reciever;
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            ip = socket.getInetAddress();
            new Thread(this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Reciever getReciever() {
        return reciever;
    }

    public void setReciever(Reciever reciever) {
        this.reciever = reciever;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message mensaje = (Message) ois.readObject();
                reciever.receive(mensaje);
            } catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

        }
    }

    public void send(Object message) {
        send(message, oos);
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public InetAddress getIp() {
        return ip;
    }

}
