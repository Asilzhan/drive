import java.util.ArrayList;
import java.util.List;

// State of board as list of 8 integer
// each element in the list counts as a row on chess board
// each value of those elements is the chess board square in that row (column)
public class Individual {

    public List<Integer> list = new ArrayList<Integer>();

    public Individual() {
        for (int i = 8; i > 0; i--) {
            list.add((int) (Math.random() * 8));
        }
    }
}
