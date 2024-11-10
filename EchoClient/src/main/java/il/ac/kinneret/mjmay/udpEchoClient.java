package il.ac.kinneret.mjmay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This program demonstrates how to implement a UDP client program.
 * @author www.codejava.net
 */
public class udpEchoClient {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Syntax: udpEchoClient <hostname> <port>");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))){

            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer;
            System.out.println("Please enter a message:");
            String sentence = userInput.readLine();
            buffer = sentence.getBytes();

            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(request);

            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);
            sentence = new String(buffer, 0, response.getLength());
            System.out.println(sentence);

        } catch (Exception ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}