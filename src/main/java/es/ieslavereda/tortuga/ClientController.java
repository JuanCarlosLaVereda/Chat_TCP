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
    @FXML
    private TextField fxml_direccion;
    @FXML
    private TextField fxml_puerto;
    @FXML
    private TextField fxml_usuario;
    @FXML
    private Button conectar;

    private CommunicationManager communicationManager;
    private String name;
    private String host;
    private int port;

    @FXML
    public void onSendButtonClick(){
        chat.appendText(name + ": " + mensaje_escribir.getText() + "\n");
        Message mensaje = new Message(name, mensaje_escribir.getText());
        communicationManager.send(mensaje);
    }

    @FXML
    public void onConnectButtonClick(){
        try {
            host = fxml_direccion.getText();
            if (!host.equals("localhost") || fxml_puerto.getText().equals("")){
                chat.appendText("[ERROR]: Direccion o puertos incorrectos");
            } else {
                port = Integer.parseInt(fxml_puerto.getText());
                chat.appendText("Intentando conectar a " + host + ":" + port + "\n");
                Socket socket = new Socket(host, port);
                communicationManager = new CommunicationManager(socket, this);
                chat.appendText("Conectado a la ip: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "\n");
                name = fxml_usuario.getText();
                conectar.setDisable(true);
            }

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
        if (message != null) {
            if (!message.getUser().equals(name)) {
                chat.appendText(message.getUser() + ": " + message.getText() +"\n");
            }
        }
    }

    @Override
    public void remove(CommunicationManager communicationManager) {

    }
}