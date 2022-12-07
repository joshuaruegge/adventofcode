package advent.utilities.utils2022;

import java.util.ArrayList;

public class DirectoryTreeNode {

    public String name;

    public DirectoryTreeNode parent;

    public ArrayList<DirectoryTreeNode> children = new ArrayList<>();

    public long filesTotal = 0;

    public DirectoryTreeNode(String s) {
        name = s;
    }
}
