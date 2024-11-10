package il.ac.kinneret.mjmay;

import java.net.*;

/**
 * This program demonstrates how to implement a UDP server program.
 * @author www.codejava.net
 */
public class udpEchoServer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Syntax: udpEchoServer <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);

        try (DatagramSocket socket = new DatagramSocket(port)) {
            while(true){
                System.out.println("Listening...\n");
                byte[] buffer = new byte[1600];

                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();

                String sentence = new String(buffer, 0, request.getLength());
                System.out.println("Received: "+ sentence);
                System.out.println("IP: " + clientAddress.getHostAddress() + "\n" + "Name: " + clientAddress.getHostName() + "\n");

                DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                socket.send(response);
            }
        } catch (Exception ex) {
            System.out.println("Server error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
