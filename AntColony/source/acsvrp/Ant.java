/**
 * 
 */
package acsvrp;

import java.util.ArrayList;

/**
 * @author ivan.panasiuk
 *
 */
public class Ant {
	
	//Total cost for ant
	Cost cost = new Cost();
	int cp;
        
	// Ukupni broj gradova koji je mrav posetio
	int num_nodes;
	ArrayList<AEdge> antPathEdges = new ArrayList<AEdge>();

	Ant() {
		cost.reset();
		num_nodes = 0;
	}
	
	public int addPath (ANodes anodes, int i, int j) {
		this.cost.incDistance(anodes.get(i).getCost2Node(anodes.get(j)).getDistance());
		this.cost.incTime(anodes.get(i).getCost2Node(anodes.get(j)).getTime());
                
		this.antPathEdges.add(anodes.getEdge(i, j));
		num_nodes++;
		if (AntColony.LOCAL_UPDATE) {
			anodes.getEdge(i,j).setPheromon(Process.localUpdate(anodes.getEdge(i,j).getPheromon()));
		}
//		Dbg.prnl("Ant.java:addPath: "+i+","+j+" d:"+this.dist+" ("+anodes.getEdge(i, j).len+")");
		//Dbg.delay(10);
		// TODO proveriti ovo
		int yyy=anodes.get(j).getDemand();
		return anodes.get(j).getDemand();
	}
	
	/**
	 * @return Returns the num_nodes.
	 */
	public int getNumOfNodes() {
		return num_nodes;
	}

	/**
	 * @return Returns the dist.
	 */
	public double getCost() {
		return cost.getValue();
	}
    

	/**
	 * @return Returns the path.
	 */
	public ArrayList<AEdge> getPath() {
		return antPathEdges;
	}
	/**
	 * @return Returns the capacity.
	 */
	public int getCap()
	{ 
		return cp;
	}

	/**
	 * @param path The path to set.
	 */
	void setPath(ArrayList<AEdge> antPathEdges) {
		this.antPathEdges = antPathEdges;
	}
}