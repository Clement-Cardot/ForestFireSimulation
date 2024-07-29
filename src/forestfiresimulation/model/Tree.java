package forestfiresimulation.model;

import java.awt.Color;

public class Tree {

    private final Coordinates coordinates;
    private TreeState state = TreeState.HEALTHY;

    public Tree(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Color getColor() {
		switch (this.state) {
            case HEALTHY:
                return new Color(0, 255, 0);
            case BURNING:
                return new Color(255, 0, 0);
            case BURNED:
                return new Color(120, 120, 120);
            default:
                return new Color(0, 0, 0);
        }
	}

    public TreeState getState() {
        return this.state;
    }

    public void setState(TreeState state) {
        this.state = state;
    }

    public void switchState() {
        if(this.state == TreeState.HEALTHY) {
            this.state = TreeState.BURNING;
        } else if(this.state == TreeState.BURNING) {
            this.state = TreeState.HEALTHY;
        }
    }
    
}
