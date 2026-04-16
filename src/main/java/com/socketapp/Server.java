package com.socketapp;
import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started on port 5000...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String message = input.readLine();
            System.out.println("Received: " + message);
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}