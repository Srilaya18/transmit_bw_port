package com.socketapp;
import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Hello from Client");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}