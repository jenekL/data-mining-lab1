import javax.swing.*;
import java.awt.*;

public class TreePanel extends JPanel {
    private int h = 10;
    private int dx = 200;
    private int dy = 50;
    private Node root;

    public TreePanel(Node root) {
        this.root = root;
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public void paint(Graphics g) {
        int x = getWidth() / 2;
        int y = 15;
        int color_rgb = 55;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, new Color(250, 250, 250), 0, getHeight(), new Color(238, 238, 238)));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        if (root != null) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLUE);
            g2d.setFont(new Font("Arial", Font.BOLD, 8));
            g2d.fillOval(x, y, h, h);
            g2d.setColor(new Color(color_rgb, color_rgb, color_rgb));
            g2d.drawString("Корень " + Value.SQUARES[root.getParameterIndex()] + " = " + root.getParameterValue(), x + 10, y);
            drawNode(g2d, root, x, y, dx);
        }
    }

    public void drawNode(Graphics2D g, Node node, int x, int y, int d) {
        Color color = Color.BLACK;
        g.setColor(color);
        if (node.getLeft() != null) {
            g.drawLine(x + h / 2, y + h, x - d + h / 2, y + dy);
            if (node.getLeft().getIsFill()) {
                g.setColor(Color.BLUE);
                g.fillOval(x - d, y + dy, h, h);
            } else {
            }
            g.setColor(Color.BLACK);
            if (node.getLeft().getParameterIndex() != -1) {
                g.drawOval(x - d, y + dy, h, h);

                g.drawString(Value.SQUARES[node.getLeft().getParameterIndex()] + " = " + node.getLeft().getParameterValue(), x - d + h + 5, y + dy + h);
            } else {
                //g.drawOval(x - d, y + dy, 5, 5);

                g.drawString("leaf " + node.getRight().getParameterValue(), x - d + h + 5, y + dy + h);
            }
        }
        g.setColor(color);
        if (node.getRight() != null) {
            g.drawLine(x + h / 2, y + h, x + d + h / 2, y + dy);
            if (node.getRight().getIsFill()) {
                g.setColor(Color.BLUE);
                g.fillOval(x + d, y + dy, h, h);
            } else {
            }
            g.setColor(Color.BLACK);
            if (node.getRight().getParameterIndex() != -1) {
                g.drawOval(x + d, y + dy, h, h);
                g.drawString(Value.SQUARES[node.getRight().getParameterIndex()] + " = " + node.getRight().getParameterValue(), x + d + h + 5, y + dy + h);
            } else {
                //g.drawOval(x + d, y + dy, 5, 5);
                g.drawString("leaf " + node.getRight().getParameterValue(), x + d + h + 5, y + dy + h);
            }
        }
        if (node.getLeft() != null) {
            drawNode(g, node.getLeft(), x - d, y + dy, d - 30);
        }
        if (node.getRight() != null) {
            drawNode(g, node.getRight(), x + d, y + dy, d - 30);
        }
    }
}
