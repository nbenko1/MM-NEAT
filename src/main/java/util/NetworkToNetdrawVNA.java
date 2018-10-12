package util;

import edu.southwestern.evolution.genotypes.TWEANNGenotype;
import edu.southwestern.evolution.genotypes.TWEANNGenotype.LinkGene;
import edu.southwestern.evolution.genotypes.TWEANNGenotype.NodeGene;
import wox.serial.Easy;

public class NetworkToNetdrawVNA {

	public static String networkToNetdrawVNA(String filename) {
		
		TWEANNGenotype network = (TWEANNGenotype) Easy.load("bestPacMan.xml");
		String NetdrawVNA = "";
		NetdrawVNA = NetdrawVNA + "*node data\n";
		NetdrawVNA = NetdrawVNA + "ID Type\n";
		for(NodeGene node : network.nodes) {
			NetdrawVNA = NetdrawVNA + Math.abs(node.innovation) + " ";
			switch(node.ntype) {
				case 0:
					NetdrawVNA = NetdrawVNA + "input\n";
					break;
				case 1:
					NetdrawVNA = NetdrawVNA + "hidden\n";
					break;
				case 2:
					NetdrawVNA = NetdrawVNA + "output\n";
					break;
				default:
					NetdrawVNA = NetdrawVNA + "undefined\n";
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
	
}
