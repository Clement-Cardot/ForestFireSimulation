package forestfiresimulation.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import forestfiresimulation.controller.tools.ToggleTreeTool;
import forestfiresimulation.model.Coordinates;
import forestfiresimulation.model.Tree;
import forestfiresimulation.model.TreeState;

public class ForestPanel extends JPanel {

    private final List<Coordinates> initialBurningTrees;
    private final List<TreeView> treeViews = new ArrayList<>();
    private final double probability;
    private final Random random = new Random();
    
    public ForestPanel(int forestWidth, int forestHeight, int treeSize, double probability, List<Coordinates> burningTrees) {
		super();
        int panelWidth = forestWidth * treeSize;
        int panelHeight = forestHeight * treeSize;
		super.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.createForest(forestWidth, forestHeight, treeSize, burningTrees);
        this.addMouseListener(new ToggleTreeTool(this));
        this.probability = probability;
        this.initialBurningTrees = burningTrees;
	}

    public List<TreeView> getTreeViews() {
        return this.treeViews;
    }

    private void createForest(int forestWidth, int forestHeight, int treeSize, List<Coordinates> burningTrees) {
        for (int i = 0; i < forestWidth; i++) {
            for (int j = 0; j < forestHeight; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                TreeView treeView = new TreeView(new Tree(coordinates), treeSize);
                if (burningTrees.stream().anyMatch(burningTree -> burningTree.equals(coordinates))) {
                    treeView.getTree().setState(TreeState.BURNING);
                }
                this.treeViews.add(treeView);
            }
        }
    }

    public void reset() {
        for(TreeView treeView : this.getTreeViews()) {
            if (this.initialBurningTrees.stream().anyMatch(burningTree -> burningTree.equals(treeView.getTree().getCoordinates()))) {
                treeView.getTree().setState(TreeState.BURNING);
            } else {
                treeView.getTree().setState(TreeState.HEALTHY);
            }
        }
        this.repaint();
    }

    public void step() {
        for(TreeView treeView : this.getTreeViews()) {
            if (treeView.getTree().getState() == TreeState.BURNING) {
                Tree tree = treeView.getTree();
                for (Tree neighbor : this.getNeighbors(tree)) {
                    if (neighbor.getState() == TreeState.HEALTHY && this.random.nextDouble() < this.probability) {
                        neighbor.setState(TreeState.WILL_BURN);
                    }
                }
                tree.setState(TreeState.BURNED);
            }
        }
        this.getTreeViews().forEach(treeView -> {
            if (treeView.getTree().getState() == TreeState.WILL_BURN) {
                treeView.getTree().setState(TreeState.BURNING);
            }
        });
        this.repaint();
    }

    private List<Tree> getNeighbors(Tree tree) {
        List<Tree> neighbors = new ArrayList<>();
        for(TreeView treeView : this.getTreeViews()) {
            Tree potentialNeighbor = treeView.getTree();
            if ((Math.abs(tree.getCoordinates().getX() - potentialNeighbor.getCoordinates().getX()) + Math.abs(tree.getCoordinates().getY() - potentialNeighbor.getCoordinates().getY())) == 1) {
                neighbors.add(potentialNeighbor);
            }
        }
        return neighbors;
    }

    @Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g.create();
		for(TreeView treeView : this.getTreeViews()) {
			treeView.draw(g2D);
		}
		
		g2D.dispose();
	}

}
