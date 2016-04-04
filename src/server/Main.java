package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {

    static DataHolder dataHolder;

    public static void main(String[] args) throws IOException {
        System.out.println("Server is running.");
        dataHolder = new DataHolder();
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(8080);
        try {
            while (true) {
                new MyServer(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class MyServer extends Thread {
        private Socket socket;
        private int clientNumber;

        public MyServer(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
                    String input = in.readLine();
                    System.out.println(input);
                    String[] strings = input.split(":");
                    if (strings[0].equals("name")) {
                        dataHolder.users.put(strings[1], socket);
                    } else {
                        Date d = new Date();
                        String user = strings[0];
                        String message = strings[1];
                        dataHolder.messages.add(new Message(user, message, d));
                        for (Socket socket1 : dataHolder.users.values()) {
                            new PrintWriter(socket1.getOutputStream(), true).println(d + " " + user + " " + message);
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
