package es.ieslavereda.tortuga.chat;

public interface Reciever {
    void receive(Message message);
    void remove(CommunicationManager communicationManager);
}
