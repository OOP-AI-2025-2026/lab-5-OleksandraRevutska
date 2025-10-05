package ua.opnu.view;

import ua.opnu.model.DrawShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Поверхня для малювання фігур
 */
public class PaintSurface extends JComponent {

    private final List<DrawShape> shapes = new ArrayList<>();
    private int shapeType;
    private Point startDrag;
    private Point endDrag;

    private final List<Color> colors = Arrays.asList(
            Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.RED, Color.BLUE, Color.PINK
    );

    public PaintSurface() {
        shapeType = 0;
        super.setPreferredSize(new Dimension(400, 400));

        // --- Події миші ---
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                DrawShape shape = DrawShape.newInstance(shapeType);
                if (shape != null) {
                    shape.setStartPoint(startDrag);
                    shape.setEndPoint(endDrag);
                    shapes.add(shape);
                }
                startDrag = null;
                endDrag = null;
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }

    // --- Встановлення типу фігури ---
    public void setShapeType(int type) {
        this.shapeType = type;
    }

    // --- Очищення всіх фігур ---
    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Антиаліасінг (згладжування)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Малюємо фонову сітку
        paintBackgroundGrid(g2);

        g2.setStroke(new BasicStroke(2));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        // Малюємо збережені фігури
        for (DrawShape s : shapes) {
            g2.setPaint(Color.BLACK);
            g2.draw(s.getShape());
            g2.setPaint(colors.get(shapes.indexOf(s) % 6));
            g2.fill(s.getShape());
        }

        // Малюємо поточну фігуру під час drag
        if (startDrag != null && endDrag != null) {
            g2.setPaint(Color.LIGHT_GRAY);
            DrawShape shape = DrawShape.newInstance(shapeType);
            if (shape != null) {
                g2.draw(shape.getShape(startDrag, endDrag));
            }
        }
    }

    private void paintBackgroundGrid(Graphics2D g2) {
        g2.setPaint(Color.LIGHT_GRAY);
        for (int i = 0; i < getSize().width; i += 10) {
            g2.draw(new Line2D.Float(i, 0, i, getSize().height));
        }
        for (int i = 0; i < getSize().height; i += 10) {
            g2.draw(new Line2D.Float(0, i, getSize().width, i));
        }
    }
}
