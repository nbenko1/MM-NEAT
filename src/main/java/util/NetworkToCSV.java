package util;

import edu.southwestern.evolution.genotypes.TWEANNGenotype;
import edu.southwestern.evolution.genotypes.TWEANNGenotype.LinkGene;
import wox.serial.Easy;

public class NetworkToCSV {

	public static void main(String[] args) {
		
		TWEANNGenotype[] list = new TWEANNGenotype[] {(TWEANNGenotype) Easy.load("bestPacMan0.xml"), (TWEANNGenotype) Easy.load("bestPacMan1.xml"),(TWEANNGenotype) Easy.load("bestPacMan2.xml"), (TWEANNGenotype) Easy.load("bestPacMan3.xml"),(TWEANNGenotype) Easy.load("bestPacMan4.xml"), (TWEANNGenotype) Easy.load("bestPacMan5.xml"),(TWEANNGenotype) Easy.load("bestPacMan6.xml"), (TWEANNGenotype) Easy.load("bestPacMan7.xml"),(TWEANNGenotype) Easy.load("bestPacMan8.xml"), (TWEANNGenotype) Easy.load("bestPacMan9.xml")};
		
		String CSV = "";
		
		for(TWEANNGenotype network : list) {
			for(LinkGene link : network.links) {
				CSV = CSV + link.weight + ", " + link.innovation + ", ";
			}
			CSV = CSV.substring(0, CSV.length() - 2) + "\n";
		}
		
		System.out.println(CSV);
		
	}
}
