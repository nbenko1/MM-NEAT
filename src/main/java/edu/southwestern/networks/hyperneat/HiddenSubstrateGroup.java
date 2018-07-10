package edu.southwestern.networks.hyperneat;


import edu.southwestern.util.datastructures.Pair;
public class HiddenSubstrateGroup {
	public final static int INPUT_SUBSTRATE = 0;
	public final static int PROCCESS_SUBSTRATE = 1;
	public final static int OUTPUT_SUBSTRATE = 2;
	// Default activation function is whatever the Parameter setting is
	public static final int DEFAULT_ACTIVATION_FUNCTION = -1;
	Pair<Integer, Integer> substrateSize;
	int numSubstrates;
	int fType;

	public HiddenSubstrateGroup(Pair<Integer, Integer> size, int numSubstrates,  int fType) {
		this(size, numSubstrates, fType, null);
	}
	
	public HiddenSubstrateGroup(Pair<Integer, Integer> substrateSize, int numSubstrates, int fType, HiddenSubstrateGroup neighbor) {
		this.substrateSize = substrateSize;
		this.numSubstrates = numSubstrates;
		this.fType = fType;
	}
}
