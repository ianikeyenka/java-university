import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 12345;

        try {
            // Подключение к серверу
            Socket socket = new Socket(serverHost, serverPort);

            // Получение идентификатора клиента
            String clientId = "client2";

            // Поток для чтения сообщений от сервера
            Thread readerThread = new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Сообщение от сервера: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readerThread.start();

            // Поток для отправки сообщений серверу
            Thread writerThread = new Thread(() -> {
                try {
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                    // Отправка идентификатора клиента на сервер
                    writer.println(clientId);

                    // Чтение сообщений с консоли и отправка их серверу
                    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                    String message;
                    while ((message = consoleReader.readLine()) != null) {
                        writer.println(clientId + ": " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writerThread.start();

            // Ожидание завершения работы потоков
            readerThread.join();
            writerThread.join();

            // Закрытие соединения
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}