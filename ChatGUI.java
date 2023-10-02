package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ChatGUI extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField serverIpField;
    private JTextField portField;
    private JTextArea chatTextArea;
    private JTextField messageField;
    private JButton connectButton;
    private JButton sendButton;
    private JList<String> userList;

    public ChatGUI() {
        setTitle("Chat Client");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        
        JPanel serverPanel = new JPanel();
        serverPanel.setLayout(new GridLayout(2, 5));
        
        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel serverIpLabel = new JLabel("IP:");
        serverIpField = new JTextField();
        JLabel portLabel = new JLabel("Port:");
        portField = new JTextField();
        connectButton = new JButton("Connect");
        connectButton.setPreferredSize(new Dimension(70, 30)); // Установка желаемых размеров кнопки
        JLabel empty = new JLabel(" ");


        serverPanel.add(serverIpLabel);
        serverPanel.add(serverIpField);
        serverPanel.add(portLabel);
        serverPanel.add(portField);
        serverPanel.add(empty);
        serverPanel.add(loginLabel);
        serverPanel.add(loginField);
        serverPanel.add(passwordLabel);
        serverPanel.add(passwordField);
        serverPanel.add(connectButton);

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());

        chatTextArea = new JTextArea();
        chatTextArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatTextArea);

        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());

        messageField = new JTextField();
        sendButton = new JButton("Send");
        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);

        userList = new JList<>();
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        userListModel.addElement("Миша");
        userListModel.addElement("Анна");
        userListModel.addElement("Витя");
        userList.setModel(userListModel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(serverPanel, BorderLayout.NORTH);
        mainPanel.add(chatPanel, BorderLayout.CENTER);
        mainPanel.add(messagePanel, BorderLayout.SOUTH);
 
        add(mainPanel, BorderLayout.CENTER);        
        add(new JScrollPane(userList), BorderLayout.SOUTH); // Добавление JList

        // Добавление обработчиков событий
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Обработка отправки сообщения
                String message = messageField.getText();
                // Отправка сообщения на сервер или другую логику
                chatTextArea.append("You: " + message + "\n");
                messageField.setText("");
            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Обработка подключения к серверу
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                String serverIp = serverIpField.getText();
                String port = portField.getText();
                // Логика подключения к серверу
                chatTextArea.append("Connected to server\n");
            }
        });

        // Отображение окна
        setVisible(true);
        loadChatHistory();
    }
    

    private void loadChatHistory() {
    File chatHistoryFile = new File("chat_history.txt"); // Имя файла истории чата
    if (chatHistoryFile.exists()) {
        try (BufferedReader reader = new BufferedReader(new FileReader(chatHistoryFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}

