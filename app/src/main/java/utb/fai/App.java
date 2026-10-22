package utb.fai;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                "Použití: java App <port> <max_threads>"
            );
            return;
        }

        int port;
        int maxThreads;

        try {
            port = Integer.parseInt(args[0]);
            maxThreads = Integer.parseInt(args[1]);

            if (port < 1 || port > 65535) {
                System.err.println(
                    "Port musí být v rozsahu 1 až 65535."
                );
                return;
            }

            if (maxThreads < 1) {
                System.err.println(
                    "Maximální počet vláken musí být alespoň 1."
                );
                return;
            }

        } catch (NumberFormatException e) {
            System.err.println(
                "Port a maximální počet vláken musí být celá čísla."
            );
            return;
        }

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            maxThreads,
            maxThreads,
            0L,
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<>()
        );

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(
                "Server naslouchá na portu " + port
            );

            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();

                try {
                    executor.execute(
                        new ClientThread(clientSocket)
                    );

                    System.out.println(
                        "Připojen klient: "
                        + clientSocket.getRemoteSocketAddress()
                    );

                } catch (java.util.concurrent.RejectedExecutionException e) {
                    System.err.println(
                        "Server dosáhl maximálního počtu klientů."
                    );

                    try {
                        clientSocket.close();
                    } catch (IOException ignored) {
                        // Spojení se nepodařilo zavřít.
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(
                "Chyba serveru: " + e.getMessage()
            );
        } finally {
            executor.shutdownNow();
        }
    }
}
