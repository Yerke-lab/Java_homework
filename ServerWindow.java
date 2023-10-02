package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private JButton startButton;
    private JButton stopButton;
    private JTextArea logTextArea; 
    private boolean isServerWorking = false;

    public ServerWindow() {
        // Настройка окна
        setTitle("Chat server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Создание кнопок
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");

        Dimension buttonSize = new Dimension(250, 50);
        startButton.setPreferredSize(buttonSize);
        stopButton.setPreferredSize(buttonSize);

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);

        // Добавление обработчиков событий для кнопок
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    isServerWorking = true;
                    logStatus("Server is running");
                } else {
                    logStatus("Server is already running");
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    logStatus("Server stopped");
                } else {
                    logStatus("Server was stopped");
                }
            }
        });

        // Создание панели для размещения кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        
        JPanel logPanel = new JPanel();
        logPanel.setLayout(new BorderLayout());
        logPanel.add(new JScrollPane(logTextArea), BorderLayout.CENTER);


        add(buttonPanel, BorderLayout.SOUTH);
        add(logPanel, BorderLayout.CENTER);
        logStatus("Server is not running");
        setVisible(true);

        }

    private void logStatus(String message) {
        logTextArea.append(message + "\n");
        repaint(); // Обновляем окно для отображения текущего состояния
    }
}
