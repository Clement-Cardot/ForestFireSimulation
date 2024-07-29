package forestfiresimulation.controller.tools;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import forestfiresimulation.view.ForestPanel;

public class ToggleTreeTool implements MouseInputListener {

    private final ForestPanel forestPanel;

    public ToggleTreeTool (ForestPanel forestPanel) {
        this.forestPanel = forestPanel;
    }

    private ForestPanel getForestPanel() {
        return this.forestPanel;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if(this.getForestPanel().getTreeViews().isEmpty()) {
            System.out.println("Seems we have a problem !");
        }
        else {
            for(int i = this.getForestPanel().getTreeViews().size() - 1; i >= 0; i--) {
                if(this.getForestPanel().getTreeViews().get(i).contains(event.getX(), event.getY())) {
                    this.getForestPanel().getTreeViews().get(i).getTree().switchState();
                    break;
                }
            }
        }
        this.getForestPanel().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
        
    
}
