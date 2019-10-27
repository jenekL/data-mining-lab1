import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tree {

    private Node root;
    private ArrayList<Node> Leafs;
    private double error = 0;
    private final Logger logger = Logger.getLogger(Tree.class.getName());

    public Tree() {
        this.root = null;

    }

    public void createRoot() {

        MainFrame.getInstance().showMessage("Создадим дерево: \n\nСчитаем данные из файла:\n\n");
        setRoot(new Node());

        ClassLoader classLoader = Tree.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("data.txt")).getFile());


        Scanner sc;
        String str;
        String[] arr;
        try {
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                str = sc.nextLine();
                System.out.println(str + "\n");
                arr = str.split(",");
                getRoot().getData().add(new Value(arr));
            }
            sc.close();
            buildingTree(root);
            MainFrame.getInstance().showMessage("\n\nПостроим дерево:\n\n");
            error = calculateError(root);
            setClassNamesToLeafs();
            setTree(root);

        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        MainFrame.getInstance().paintTree();
    }

    private void setTree(Node node) {
        MainFrame.getInstance().showMessage(node.getParameterValue() + "\n");
        MainFrame.getInstance().showMessage(node.determinateClass() + "\n");

        if (!node.getIsList()) {
            setTree(node.getLeft());
            setTree(node.getRight());
        }
    }

    public void buildingTree(final Node node) {
        double maxQ = 0;
        int maxType;
        maxType = 0;
        String maxValue;
        maxValue = null;
        double Q;

        for (int i = 0; i < 9; i++) {

            for (String p : Value.PARAMS) {
                Q = node.getQ(i, p);
                if (Q > maxQ) {
                    maxQ = Q;
                    maxType = i;
                    maxValue = p;
                }
            }
        }

        if (maxQ > 0) {
            node.setParameterIndex(maxType);
            node.setParameterValue(maxValue);
            node.createBranches(maxType, maxValue);
            System.out.println("node " + Value.SQUARES[maxType] + " " + maxValue + " " + node.determinateClass());
        } else {
            node.setIsList(true);
            int classification = node.getClassification();
            node.setParameterValue(Value.CLASSES[classification]);
            System.out.println("list " + classification);
            return;
        }
        System.out.println("left");
        buildingTree(node.getLeft());
        System.out.println("right");
        buildingTree(node.getRight());

    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private void setClassNamesToLeafs() {

        Leafs = new ArrayList<>();
        this.getLeafs(root);
        for (Node leaf : Leafs) {
            int[] count_class = getCounts(leaf);
            StringBuilder className = new StringBuilder(" Class: ");

            if (count_class[0] > 0) {
                className.append(" pos ");
            }
            if (count_class[1] > 0) {
                className.append(" neg ");
            }
            leaf.setClassName(className.toString());
        }
    }

    public double calculateError(Node node) {

        Leafs = new ArrayList<>();
        this.getLeafs(node);
        int count_error = 0;
        for (Node leaf : Leafs) {
            int[] count_class = getCounts(leaf);

            int index = count_class[0] > count_class[1] ? 0 : 1;

            for (int i = 0; i < 2; i++) {
                if (i != index) {
                    count_error += count_class[i];
                }
            }
        }
        double err = (double) count_error / node.getData().size();
        System.out.println("Ошибка = " + err);
        return err;

    }

    private int[] getCounts(Node leaf) {
        int[] count_class = new int[2];
        ArrayList<Value> values = leaf.getData();
        for (int i = 0; i < values.size(); i++) {
            switch (values.get(i).getClassValue()) {
                case ("positive"): {
                    count_class[0]++;
                    break;
                }
                case ("negative"): {
                    count_class[1]++;
                    break;
                }
            }
        }
        return count_class;
    }

    public void getLeafs(Node node) {
        if (node.getIsList()) {
            if (node.getIsVisible()) {
                Leafs.add(node);
            }
        } else {
            getLeafs(node.getLeft());
            getLeafs(node.getRight());
        }
    }

    public void prunning() {
        prunningOneNode(root);
        MainFrame.getInstance().rep();
    }

    private void prunningOneNode(Node node) {
        if (!node.getIsList()) {
            node.getLeft().setIsVisible(false);
            node.getRight().setIsVisible(false);
            node.setIsList(true);
            double er = calculateError(root);
            if ((error - er) < 0) {
                node.setIsList(false);
                node.getLeft().setIsVisible(true);
                node.getRight().setIsVisible(true);
                prunningOneNode(node.getLeft());
                prunningOneNode(node.getRight());
            } else {
                node.setLeft(null);
                node.setRight(null);
            }
        }
    }

    public void test() {
        ClassLoader classLoader = Tree.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("test.txt")).getFile());

        Scanner sc;
        String str;
        String[] arr;
        Value value;
        try {
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                str = sc.nextLine();
                arr = str.split(",");
                value = new Value(arr);
                this.testValue(value, root);

            }
            sc.close();

        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }


    }

    public void testValue(Value value, Node node) {
        node.setIsFill(true);
        if (node.getIsList()) {
            MainFrame.getInstance().showMessage(value.getClassValue() + node.getClassName());
        } else {
            MainFrame.getInstance().rep();
            switch (node.getParameterIndex()) {
                case (0): {
                    if (node.getParameterValue().equals(value.getTop_left())) {
                        this.testValue(value, node.getLeft());
                    } else {
                        testValue(value, node.getRight());
                    }

                    break;
                }
                case (1): {
                    if (node.getParameterValue().equals(value.getTop_middle())) {
                        this.testValue(value, node.getLeft());
                    } else {
                        testValue(value, node.getRight());
                    }

                    break;
                }
                case (2): {
                    if (node.getParameterValue().equals(value.getTop_right())) {
                        this.testValue(value, node.getLeft());
                    } else {
                        testValue(value, node.getRight());
                    }

                    break;
                }
                case (3): {
                    if (node.getParameterValue().equals(value.getMiddle_left())) {
                        this.testValue(value, node.getLeft());
                    } else {
                        testValue(value, node.getRight());
                    }

                    break;
                }
                case (4): {
                    if (node.getParameterValue().equals(value.getMiddle_middle())) {
                        this.testValue(value, node.getLeft());
                    } else {
                        testValue(value, node.getRight());
                    }

                    break;
                }
                case (5): {
                    if (node.getParameterValue().equals(value.getMiddle_right())) {
                        this.testValue(value, node.getLeft());
                    } else {
                        testValue(value, node.getRight());
                    }

                    break;
                }
                case (6): {
                    if (node.getParameterValue().equals(value.getBottom_left())) {
                        this.testValue(value, node.getLeft());
                    } else {
                        testValue(value, node.getRight());
                    }

                    break;
                }
                case (7): {
                    if (node.getParameterValue().equals(value.getBottom_middle())) {
                        this.testValue(value, node.getLeft());
                    } else {
                        testValue(value, node.getRight());
                    }
                    break;
                }
                case (8): {
                    if (node.getParameterValue().equals(value.getBottom_right())) {
                        this.testValue(value, node.getLeft());
                    } else {
                        testValue(value, node.getRight());
                    }
                    break;
                }
            }
        }
    }
}
