package forestfiresimulation.view;

import java.awt.Color;
import java.awt.Graphics2D;

import forestfiresimulation.model.Tree;

public class TreeView {

    private final Tree tree;
    private final int treeSize;

    public TreeView(Tree tree, int treeSize) {
        this.tree = tree;
        this.treeSize = treeSize;
    }

    public Tree getTree() {
        return this.tree;
    }

    public boolean contains(int x, int y) {
        return  x >= this.tree.getCoordinates().getX()*treeSize && 
                x <= this.tree.getCoordinates().getX()*treeSize + treeSize && 
                y >= this.tree.getCoordinates().getY()*treeSize && 
                y <= this.tree.getCoordinates().getY()*treeSize + treeSize;
    }

    public void draw(Graphics2D g2d){
        Color previousColor = g2d.getColor();
		g2d.setColor(this.tree.getColor());
		g2d.fillRect(
            this.tree.getCoordinates().getX()*treeSize, 
            this.tree.getCoordinates().getY()*treeSize, 
            treeSize, 
            treeSize
        );
		g2d.setColor(previousColor);
        g2d.drawRect(
            this.tree.getCoordinates().getX()*treeSize, 
            this.tree.getCoordinates().getY()*treeSize, 
            treeSize, 
            treeSize
        );
    }
    
}
