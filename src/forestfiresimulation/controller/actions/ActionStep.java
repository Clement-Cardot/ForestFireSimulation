package forestfiresimulation.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import forestfiresimulation.view.ForestPanel;

public class ActionStep extends AbstractAction {

    public static final String ACTION_NAME = "Step";
    private final ForestPanel forestPanel;

    public ActionStep(ForestPanel forestPanel) {
		super(ACTION_NAME);
		this.forestPanel = forestPanel;
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        this.forestPanel.step();
    }
    
}