import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private Tree tree = new Tree();
    private JMenuItem jMenu1;
    private JMenuItem jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private TreePanel jPanel1;
    private JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private final Logger logger = Logger.getLogger(MainFrame.class.getName());

    public static synchronized MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public void startLearning() {
        tree.createRoot();
    }

    public MainFrame() {
        initComponents();

        setTitle("Лабораторная № 1");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new TreePanel(tree.getRoot());
        JScrollPane areaScrollPane = new JScrollPane(jPanel1);
        jPanel1.setPreferredSize(new Dimension(2000, 2000));

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new JMenuItem();
        jMenu2 = new JMenuItem();
        JMenuItem jMenu3 = new JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 565, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));

        jMenu1.setText("Обучение");
        jMenu1.addActionListener(e -> {
            jMenu2.setEnabled(true);
            startLearning();
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Тестирование");
        jMenu2.setToolTipText("");
        jMenu2.setEnabled(false);
        jMenu2.addActionListener(e -> tree.test());
        jMenu3.setText("Почистить");
        jMenu3.addActionListener(e -> tree.prunning());
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu3);
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(areaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                                        .addComponent(areaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));

        pack();
    }

    public void paintTree() {
        this.jPanel1.setVisible(true);
        jPanel1.setRoot(tree.getRoot());
        jPanel1.repaint();

    }

    public void showMessage(String text) {
        Document doc = this.jTextPane1.getDocument();
        try {
            doc.insertString(doc.getLength(), text, null);
        } catch (BadLocationException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    public void rep() {
        jPanel1.repaint();
    }
}
