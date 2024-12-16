package es.ieslavereda.tortuga.chat;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChannelManager implements Reciever{
    List<CommunicationManager> managers;

    public ChannelManager() {
        this.managers = new ArrayList<CommunicationManager>();
    }

    public List<CommunicationManager> getManagers() {
        return managers;
    }

    @Override
    public void receive(Message message) {
        for (CommunicationManager manager : managers) {
            manager.send(message);
        }
    }

    @Override
    public void remove(CommunicationManager communicationManager) {
        managers.remove(communicationManager);
    }

    public void add(Socket socket) {
        managers.add(new CommunicationManager(socket, this));
        System.out.println("Nuevo cliente conectado desde: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
    }
}
