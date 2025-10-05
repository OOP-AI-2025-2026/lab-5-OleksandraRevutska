package ua.opnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainFrame extends JFrame implements ActionListener {

    public MainFrame(String title) throws HeadlessException {
        super(title);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));

        JButton rockButton = new JButton("Камінь");
        rockButton.addActionListener(this);
        rockButton.setActionCommand("rock");
        JButton paperButton = new JButton("Папір");
        paperButton.addActionListener(this);
        paperButton.setActionCommand("paper");
        JButton scissorsButton = new JButton("Ножиці");
        scissorsButton.addActionListener(this);
        scissorsButton.setActionCommand("scissors");

        this.add(rockButton);
        this.add(paperButton);
        this.add(scissorsButton);

        this.pack();
        this.setVisible(true);
    }

    // --- 1. Випадкове створення фігури комп'ютера ---
    private GameShape generateShape() {
        int random = new Random().nextInt(3);
        switch (random) {
            case 0:
                return new Rock();
            case 1:
                return new Paper();
            case 2:
                return new Scissors();
            default:
                return new Rock();
        }
    }

    // --- 2. Перевірка переможця ---
    private int checkWinner(GameShape player, GameShape computer) {
        // Нічия
        if (player.getClass() == computer.getClass()) return 0;

        // Випадки, коли виграє гравець
        if (player instanceof Rock && computer instanceof Scissors) return 1;
        if (player instanceof Paper && computer instanceof Rock) return 1;
        if (player instanceof Scissors && computer instanceof Paper) return 1;

        // Інакше виграв комп’ютер
        return -1;
    }

    // --- 3. Обробка натискань кнопок ---
    @Override
    public void actionPerformed(ActionEvent e) {
        GameShape computerShape = generateShape();
        GameShape playerShape = null;

        switch (e.getActionCommand()) {
            case "rock":
                playerShape = new Rock();
                break;
            case "paper":
                playerShape = new Paper();
                break;
            case "scissors":
                playerShape = new Scissors();
                break;
        }

        int gameResult = checkWinner(playerShape, computerShape);

        String message = "Гравець: " + playerShape + ". Комп'ютер: " + computerShape + ". ";
        switch (gameResult) {
            case -1:
                message += "Переміг комп'ютер!";
                break;
            case 0:
                message += "Нічия!";
                break;
            case 1:
                message += "Переміг гравець!";
        }

        JOptionPane.showMessageDialog(null, message, "Результат гри", JOptionPane.INFORMATION_MESSAGE);
    }
}

