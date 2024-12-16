package es.ieslavereda.tortuga;

import es.ieslavereda.tortuga.chat.CommunicationManager;
import es.ieslavereda.tortuga.chat.Message;
import es.ieslavereda.tortuga.chat.Reciever;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientController implements Reciever {
    @FXML
    private Button enviar;
    @FXML
    private TextArea chat;
    @FXML
    private TextField mensaje_escribir;

    private CommunicationManager communicationManager;
    private String name;
    private String host;
    private int port;

    @FXML
    public void onSendButtonClick(){
        chat.appendText(mensaje_escribir.getText());
        name = "Pepe";
        Message mensaje = new Message(name, mensaje_escribir.getText());
        communicationManager.send(mensaje);
    }

    @FXML
    public void onConnectButtonClick(){
        try {
            host = "172.30.9.17";
            port = 50001;
            System.out.println("Intentando conectar a " + host + ":" + port);
            Socket socket = new Socket(host, port);
            communicationManager = new CommunicationManager(socket, this);
            System.out.println("Conectado a la ip: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
        } catch (IOException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public String getName() {
        return name;
    }


    @Override
    public void receive(Message message) {
        chat.appendText(message.getText());
    }

    @Override
    public void remove(CommunicationManager communicationManager) {

    }
}