import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            // Создание серверного сокета
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен и ожидает подключения клиентов...");

            // Хранение информации о подключенных клиентах
            Map<String, Socket> clients = new HashMap<>();

            while (true) {
                // Ожидание подключения клиента
                Socket clientSocket = serverSocket.accept();
                String clientAddress = clientSocket.getInetAddress().getHostAddress();
                System.out.println("Подключен клиент: " + clientAddress);

                // Получение идентификатора клиента
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String clientId = reader.readLine();

                // Сохранение информации о клиенте
                clients.put(clientId, clientSocket);

                // Поток для чтения сообщений от клиента
                Thread readerThread = new Thread(() -> {
                    try {
                        BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        String message;
                        while ((message = clientReader.readLine()) != null) {
                            System.out.println("Сообщение от клиента " + clientId + ": " + message);
                            // Отправка сообщения каждому клиенту, кроме отправителя
                            for (String otherClientId : clients.keySet()) {
                                if (!otherClientId.equals(clientId)) {
                                    Socket otherClient = clients.get(otherClientId);
                                    PrintWriter clientWriter = new PrintWriter(otherClient.getOutputStream(), true);
                                    clientWriter.println(message);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                readerThread.start();

                // Поток для отправки сообщений клиенту
                Thread writerThread = new Thread(() -> {
                    try {
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                        // Отправка приветственного сообщения клиенту
                        writer.println("Добро пожаловать, " + clientId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}