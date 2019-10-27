import java.util.ArrayList;

public class Node {

    private ArrayList<Value> data = new ArrayList<>();
    private Node left = null;
    private Node right = null;
    private Node parent = null;
    private Boolean isLeaf = false;
    private Boolean isVisible = true;
    private int parameterIndex = -1;
    private String parameterValue = null;
    private String className = null;
    private Boolean isFill = false;

    public Node() {


    }

    public Node(Node p, ArrayList<Value> d) {
        parent = p;
        data = d;
        left = null;
        right = null;
        isLeaf = false;
    }

    public ArrayList<Value> getData() {
        return this.data;
    }

    public void setData(ArrayList<Value> data) {
        this.data = data;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Object getParametr(int i, int type) {
        switch (type) {
            case (0): {
                return data.get(i).getTop_left();
            }
            case (1): {
                return (data.get(i)).getTop_middle();
            }
            case (2): {
                return (data.get(i)).getTop_right();
            }
            case (3): {
                return (data.get(i)).getMiddle_left();
            }
            case (4): {
                return (data.get(i)).getMiddle_middle();
            }
            case (5): {
                return (data.get(i)).getMiddle_right();
            }
            case (6): {
                return (data.get(i)).getBottom_left();
            }
            case (7): {
                return (data.get(i)).getBottom_middle();
            }
            case (8): {
                return (data.get(i)).getBottom_right();
            }
            default:
                return null;
        }
    }

    public double getQ(int type, String standartValue) {
        ArrayList<Value> leftValues;
        leftValues = new ArrayList<>();
        ArrayList<Value> rightValues;
        rightValues = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {

            String b = String.valueOf(getParametr(i, type));
            if (b.equals(standartValue)) {
                leftValues.add((data.get(i)));
            } else {
                rightValues.add((data.get(i)));
            }

        }

        return calculateQ(leftValues, rightValues);
    }

    private int[] getCounts(ArrayList<Value> values) {
        int[] counts = new int[2];

        for (Value i : values) {
            switch (i.getClassValue()) {
                case ("positive"): {
                    counts[0]++;
                    break;
                }
                case ("negative"): {
                    counts[1]++;
                    break;
                }
            }
        }
        return counts;
    }

    public double calculateQ(ArrayList<Value> left, ArrayList<Value> right) {
        double Pl = (double) left.size() / (left.size() + right.size());
        double Pr = (double) right.size() / (left.size() + right.size());

        double pos_left, neg_left, pos_right, neg_right;
        int[] counts = getCounts(left);
        pos_left = (double) counts[0] / left.size();
        neg_left = (double) counts[1] / left.size();

        counts = getCounts(right);
        pos_right = (double) counts[0] / right.size();
        neg_right = (double) counts[1] / right.size();

        double W = Math.abs(pos_left - pos_right) + Math.abs(neg_left - neg_right);

        return 2 * Pl * Pr * W;
    }

    public void createBranches(int type, String value) {
        ArrayList<Value> leftValues = new ArrayList<>();
        ArrayList<Value> rightValues = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (String.valueOf(getParametr(i, type)).equals(value)) {
                leftValues.add((data.get(i)));
            } else {
                rightValues.add((data.get(i)));
            }
        }

        left = new Node(this, leftValues);
        right = new Node(this, rightValues);
    }

    public int getClassification() {
        int[] counts = getCounts(data);
        return counts[0] > counts[1] ? 0 : 1;
    }

    public String determinateClass() {
        int[] counts = getCounts(data);

        StringBuilder answer = new StringBuilder();
        if (counts[0] != 0) {
            answer.append(" pos = ");
            answer.append(counts[0]);
        }
        if (counts[1] != 0) {
            answer.append(" neg = ");
            answer.append(counts[1]);
        }

        return answer.toString();
    }

    public Boolean getIsList() {
        return isLeaf;
    }

    public void setIsList(Boolean isList) {
        this.isLeaf = isList;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }


    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
        if (left != null) left.setIsVisible(isVisible);
        if (right != null) right.setIsVisible(isVisible);

    }


    public int getParameterIndex() {
        return parameterIndex;
    }


    public void setParameterIndex(int parameterIndex) {
        this.parameterIndex = parameterIndex;
    }


    public String getParameterValue() {
        return parameterValue;
    }


    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }


    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public Boolean getIsFill() {
        return isFill;
    }


    public void setIsFill(Boolean isFill) {
        this.isFill = isFill;
    }
}
