/**
 * 1. top-left-square: {x,o,b}
 * 2. top-middle-square: {x,o,b}
 * 3. top-right-square: {x,o,b}
 * 4. middle-left-square: {x,o,b}
 * 5. middle-middle-square: {x,o,b}
 * 6. middle-right-square: {x,o,b}
 * 7. bottom-left-square: {x,o,b}
 * 8. bottom-middle-square: {x,o,b}
 * 9. bottom-right-square: {x,o,b}
 * 10. Class: {positive,negative}
 */
public class Value {

    public static final String[] PARAMS = {"x", "o", "b"};
    public static final String[] CLASSES = {"pos", "neg"};
    public static final String[] SQUARES = {"top_left", "top_middle", "top_right", "middle_left",
            "middle_middle", "middle_right", "bottom_left", "bottom_middle", "bottom_right"};

    private String top_left;
    private String top_middle;
    private String top_right;
    private String middle_left;
    private String middle_middle;
    private String middle_right;
    private String bottom_left;
    private String bottom_middle;
    private String bottom_right;
    private String classValue;

    public Value() {

    }

    public Value(String[] value) {
        top_left = value[0];
        top_middle = value[1];
        top_right = value[2];
        middle_left = value[3];
        middle_middle = value[4];
        middle_right = value[5];
        bottom_left = value[6];
        bottom_middle = value[7];
        bottom_right = value[8];
        classValue = value[9];
    }

    public String getTop_left() {
        return top_left;
    }

    public void setTop_left(String top_left) {
        this.top_left = top_left;
    }

    public String getTop_middle() {
        return top_middle;
    }

    public void setTop_middle(String top_middle) {
        this.top_middle = top_middle;
    }

    public String getTop_right() {
        return top_right;
    }

    public void setTop_right(String top_right) {
        this.top_right = top_right;
    }

    public String getMiddle_left() {
        return middle_left;
    }

    public void setMiddle_left(String middle_left) {
        this.middle_left = middle_left;
    }

    public String getMiddle_middle() {
        return middle_middle;
    }

    public void setMiddle_middle(String middle_middle) {
        this.middle_middle = middle_middle;
    }

    public String getMiddle_right() {
        return middle_right;
    }

    public void setMiddle_right(String middle_right) {
        this.middle_right = middle_right;
    }

    public String getBottom_left() {
        return bottom_left;
    }

    public void setBottom_left(String bottom_left) {
        this.bottom_left = bottom_left;
    }

    public String getBottom_middle() {
        return bottom_middle;
    }

    public void setBottom_middle(String bottom_middle) {
        this.bottom_middle = bottom_middle;
    }

    public String getBottom_right() {
        return bottom_right;
    }

    public void setBottom_right(String bottom_right) {
        this.bottom_right = bottom_right;
    }

    public String getClassValue() {
        return classValue;
    }

    public void setClassValue(String classValue) {
        this.classValue = classValue;
    }
}
