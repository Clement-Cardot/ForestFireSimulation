package forestfiresimulation;

import java.awt.BorderLayout;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import forestfiresimulation.model.Coordinates;
import forestfiresimulation.view.ForestPanel;
import forestfiresimulation.view.ToolBar;

public class ForestFireSimulation {

    private JFrame window;

    private int forestHeight;
    private int forestWidth;
    private int treeSize;
    private double fireProbability;
    private List<Coordinates> burningTrees = new ArrayList<>();

    private ForestFireSimulation() {
		this.window = new JFrame("Forest Fire Simulation");

        this.readConfig();
		
        ForestPanel forestPanel = new ForestPanel(forestWidth, forestHeight, treeSize, fireProbability, burningTrees);
		ToolBar toolBar = new ToolBar(forestPanel);
		
		this.window.add(forestPanel, BorderLayout.CENTER);
        this.window.add(toolBar, BorderLayout.NORTH);
		this.window.setLocationRelativeTo( window );
		this.window.pack();
		this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.window.setVisible(true);
	}

    private void readConfig() {
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) new JSONParser().parse(new FileReader("resources/config.json"));
        } catch (IOException | ParseException e) {
            System.out.println("Error reading config file");
            e.printStackTrace();
            return;
        }
        this.forestHeight = ((Long) jsonObject.getOrDefault("forestHeight", DefaultConfig.FOREST_HEIGHT)).intValue();
        this.forestWidth = ((Long) jsonObject.getOrDefault("forestWidth", DefaultConfig.FOREST_WIDTH)).intValue();
        this.treeSize = ((Long) jsonObject.getOrDefault("treeSize", DefaultConfig.TREE_SIZE)).intValue();
        this.fireProbability = (double) jsonObject.getOrDefault("fireProbability", DefaultConfig.FIRE_PROBABILITY);
        JSONArray arr = (JSONArray) jsonObject.getOrDefault("burningTrees", new ArrayList<>());
        arr.forEach(item -> {
            JSONObject burningTree = (JSONObject) item;
            this.burningTrees.add(
                new Coordinates(
                    ((Long) burningTree.get("x")).intValue(), 
                    ((Long) burningTree.get("y")).intValue()
                )
            );
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                new ForestFireSimulation();
			}
		});
    };
};