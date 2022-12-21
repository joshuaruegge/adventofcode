package advent.utilities.utils2022;

import java.util.ArrayList;

public class TreeMonkey {

    //static list of all monkeys
    public static ArrayList<TreeMonkey> monkeys = new ArrayList<TreeMonkey>();

    public String id;

    public String source1;
    public String source2;

    public int operation;

    public boolean hasValue = false;
    public long value = 0;

    //operator constructor
    public TreeMonkey(String id, String source1, String source2, int operation) {
        this.id = id;
        this.source1 = source1;
        this.source2 = source2;
        this.operation = operation;
        monkeys.add(this);
    }

    //single-value constructor
    public TreeMonkey(String id, long value) {
        this.id = id;
        this.value = value;
        hasValue = true;
        monkeys.add(this);
    }

    //either returns monkey's value, or calculates it recursively based on sources and operation
    public long get() {
        if(hasValue)
            return value;
        else {
            switch (operation) {
                case 0 -> value = find(source1).get() + find(source2).get();
                case 1 -> value = find(source1).get() - find(source2).get();
                case 2 -> value = find(source1).get() * find(source2).get();
                case 3 -> value = find(source1).get() / find(source2).get();
            }
            hasValue = true;
            return value;
        }
    }

    //finds treemonkey with given ID
    public static TreeMonkey find(String searchId) {
        for(TreeMonkey b : monkeys) {
            if(b.id.equals(searchId)) {
                return b;
            }
        }
        return null;
    }
}
