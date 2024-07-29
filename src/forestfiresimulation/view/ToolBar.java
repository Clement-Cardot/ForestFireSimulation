package forestfiresimulation.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import forestfiresimulation.controller.actions.ActionReset;
import forestfiresimulation.controller.actions.ActionStep;

public class ToolBar extends JPanel {
    
    private final ForestPanel forestPanel;

    public ToolBar(ForestPanel forestPanel) {
        this.forestPanel = forestPanel;
        this.initComponents();
    }

    private void initComponents() {
		// RESET
		JButton resetBtn = new JButton(new ActionReset(this.forestPanel));
		this.add(ActionReset.ACTION_NAME, resetBtn);
		resetBtn.setName(ActionReset.ACTION_NAME);
				
		// STEP BUTTON
		JButton stepBtn = new JButton(new ActionStep(this.forestPanel));
		this.add(ActionStep.ACTION_NAME, stepBtn);
		stepBtn.setName(ActionStep.ACTION_NAME);
	}

}
