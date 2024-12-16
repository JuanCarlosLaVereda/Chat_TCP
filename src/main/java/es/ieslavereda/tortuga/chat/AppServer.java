package es.ieslavereda.tortuga.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {

    int PUERTO = 50001;
    ChannelManager channelManager = new ChannelManager();

    public AppServer() {

    }
    public static void main(String[] args) {
        AppServer server = new AppServer();
        server.start();
    }

    public void start(){
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)){
            System.out.println("Creado el server con el socket: " + PUERTO);
            while (true) {
                System.out.println("Esperando conexiones...");
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + socket.getInetAddress());
                channelManager.add(socket);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
