package utb.fai;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientThread extends Thread {

    private final Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[4096];

        try (
            Socket socket = clientSocket;
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream()
        ) {
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                String received = new String(
                    buffer, 0, bytesRead, StandardCharsets.UTF_8
                );

                System.out.print("Klient poslal: " + received);
                System.out.flush();

                output.write(buffer, 0, bytesRead);
                output.flush();
            }

        } catch (IOException e) {
            if (!clientSocket.isClosed()) {
                System.err.println(
                    "Chyba komunikace: " + e.getMessage()
                );
            }
        }
    }
}
