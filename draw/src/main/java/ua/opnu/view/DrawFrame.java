package ua.opnu.view;

import ua.opnu.model.DrawShape;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Головне вікно програми для малювання
 */
public class DrawFrame extends JFrame {

    private final PaintSurface surface;

    public DrawFrame(String title) {
        super(title);

        // Закриваємо програму при виході
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Верхня панель з кнопками
        this.add(createButtonPanel(), BorderLayout.NORTH);

        // Поверхня для малювання
        surface = new PaintSurface();
        this.add(surface, BorderLayout.CENTER);

        // Розміри і відображення
        this.pack();
        this.setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(true);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.CYAN);
        buttonPanel.setBorder(new LineBorder(Color.BLACK, 2));

        // --- Кнопка Rectangle ---
        BigTextButton rect = new BigTextButton("Rectangle");
        rect.addActionListener(e -> surface.setShapeType(DrawShape.SHAPE_RECTANGLE));
        buttonPanel.add(rect);

        // --- Кнопка Rounded Rectangle ---
        BigTextButton roundedRect = new BigTextButton("Rounded rect.");
        roundedRect.addActionListener(e -> surface.setShapeType(DrawShape.SHAPE_ROUNDED_RECT));
        buttonPanel.add(roundedRect);

        // --- Кнопка Ellipse ---
        BigTextButton ellipse = new BigTextButton("Ellipse");
        ellipse.addActionListener(e -> surface.setShapeType(DrawShape.SHAPE_ELLIPSE));
        buttonPanel.add(ellipse);

        // --- Кнопка Clear ---
        BigTextButton clear = new BigTextButton("Clear");
        clear.addActionListener(e -> surface.clearShapes());
        buttonPanel.add(clear);

        return buttonPanel;
    }
}
