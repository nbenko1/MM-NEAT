package util;

import edu.southwestern.evolution.genotypes.TWEANNGenotype;
import edu.southwestern.evolution.genotypes.TWEANNGenotype.LinkGene;
import edu.southwestern.evolution.genotypes.TWEANNGenotype.NodeGene;
import wox.serial.Easy;

public class NetworkToJSON {
	
	static String FILENAME = "bestPacMan0.xml";
	
	public static String networkToJson(String filename) {
		TWEANNGenotype network = (TWEANNGenotype) Easy.load(FILENAME);
		String JSON = "";
		
		JSON = JSON + "{\n";
		JSON = JSON + "\t\"graph\": {\n";
		JSON = JSON + "\t\t\"directed\": true,\n";
		JSON = JSON + "\t\t\"nodes\": [\n";
		for(NodeGene node : network.nodes) {
			JSON = JSON + "\t\t\t{\n";
			JSON = JSON + "\t\t\t\t\"id\": " + "\"" + Math.abs(node.innovation) + "\"\n";
			switch(node.ntype) {
				case 0:
					JSON = JSON + "\t\t\t\t\"type\": \"input\"\n";
					break;
				case 1:
					JSON = JSON + "\t\t\t\t\"type\": \"hidden\"\n";
					break;
				case 2:
					JSON = JSON + "\t\t\t\t\"type\": \"output\"\n";
					break;
				default:
					JSON = JSON + "\t\t\t\t\"type\": \"undefined\"\n";
					break;
			}
			JSON = JSON + "\t\t\t},\n";
		}
		JSON = JSON.substring(0, JSON.length() - 2);
		JSON = JSON + "\n";
		JSON = JSON + "\t\t],\n";
		JSON = JSON + "\t\t\"edges\": [\n";
		for(LinkGene link : network.links) {
			JSON = JSON + "\t\t\t{\n";
			JSON = JSON + "\t\t\t\t\"id\": " + "\"" + Math.abs(link.innovation) + "\"\n";
			JSON = JSON + "\t\t\t\t\"source\": " + "\"" + Math.abs(link.sourceInnovation) + "\"\n";
			JSON = JSON + "\t\t\t\t\"target\": " + "\"" + Math.abs(link.targetInnovation) + "\"\n";
			JSON = JSON + "\t\t\t\t\"weight\": " + "\"" + Math.abs(link.weight) + "\"\n";
			JSON = JSON + "\t\t\t},\n";
		}
		JSON = JSON.substring(0, JSON.length() - 2);
		JSON = JSON + "\n";
		JSON = JSON + "\t\t]\n";
		JSON = JSON + "\t}\n";
		JSON = JSON + "}";
		
		return JSON;
	}
}

