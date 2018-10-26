package util;

import edu.southwestern.evolution.genotypes.TWEANNGenotype;
import edu.southwestern.evolution.genotypes.TWEANNGenotype.LinkGene;
import edu.southwestern.evolution.genotypes.TWEANNGenotype.NodeGene;
import wox.serial.Easy;

public class NetworkToNetdrawVNA {

	//HEIGHT and WIDTH are used to evenly space the nodes
	static double HEIGHT = 100;
	static double WIDTH = 100;
	static TWEANNGenotype network = (TWEANNGenotype) Easy.load("bestPacMan.xml");
	
	public static String networkToNetdrawVNA(String filename) {
		
		//We keep count of how many nodes of a type because we are not guarenteed 
		//that the network is in any specific order
		double inputCount = 1;
		double hiddenCount = 1;
		double outputCount = 1;
		
		double totalInput = getNumNodes(0);
		double totalHidden = getNumNodes(1);
		double totalOutput = getNumNodes(2);
		
		String NetdrawVNA = "";
		NetdrawVNA = NetdrawVNA + "*node data\n";
		NetdrawVNA = NetdrawVNA + "ID Type X Y\n";
		for(NodeGene node : network.nodes) {
			NetdrawVNA = NetdrawVNA + Math.abs(node.innovation) + " ";
			switch(node.ntype) {
				case 0:
					NetdrawVNA = NetdrawVNA + "input " + WIDTH * (inputCount / totalInput) + " " + HEIGHT + "\n";
					inputCount++;
					break;
				case 1:
					NetdrawVNA = NetdrawVNA + "hidden " + WIDTH * (hiddenCount / totalHidden) + " " + HEIGHT * (2.0/3.0) + "\n";
					hiddenCount++;
					break;
				case 2:
					NetdrawVNA = NetdrawVNA + "output " + WIDTH * (outputCount / totalOutput) + " " + HEIGHT * (1.0/3.0) + "\n";
					outputCount++;
					break;
				default:
					NetdrawVNA = NetdrawVNA + "undefined 0 0";
					break;
			}
		}
		NetdrawVNA = NetdrawVNA + "*tie data\n";
		NetdrawVNA = NetdrawVNA + "from to stength\n";
		for(LinkGene link : network.links) {
			NetdrawVNA = NetdrawVNA + Math.abs(link.sourceInnovation) + " ";
			NetdrawVNA = NetdrawVNA + Math.abs(link.targetInnovation) + " ";
			NetdrawVNA = NetdrawVNA + Math.abs(link.weight) + "\n";
		}
		
		return NetdrawVNA;
	}
	
	private static int getNumNodes(int type) {
		int count = 0;
		for(NodeGene node : network.nodes) {
			if(node.ntype == type) {
				count++;
			}
		}
		return count;
	}
}
