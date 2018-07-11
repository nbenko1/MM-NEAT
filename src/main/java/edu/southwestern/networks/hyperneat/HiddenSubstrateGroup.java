package edu.southwestern.networks.hyperneat;

/**
 * This class defines a group of substrates that all have the same connectivity, activation function, substrate size, and layer
 * @author Devon Fulcher
 */
import edu.southwestern.util.datastructures.Pair;
import edu.southwestern.util.datastructures.Triple;
public class HiddenSubstrateGroup {
	// Default activation function is whatever the Parameter setting is
	public static final int DEFAULT_ACTIVATION_FUNCTION = -1;
	//(width,height)
	public Pair<Integer, Integer> substrateSize;
	public int numSubstrates;
	//the location in 3d vector space of the first substrate in this HiddenSubstrateGroup
	public Triple<Integer, Integer, Integer> hiddenSubstrateGroupStartLocation;
	int fType;
	
	public HiddenSubstrateGroup(int numSubstrates, int substrateWidth, int substrateHeight, int layerDepth) {
		Pair<Integer, Integer> size = new Pair<Integer, Integer>(substrateWidth, substrateHeight);
		this.substrateSize = size;
		this.numSubstrates = numSubstrates;
		this.hiddenSubstrateGroupStartLocation = new Triple<Integer, Integer, Integer>(0, layerDepth, 0);
	}
	
	public HiddenSubstrateGroup(Pair<Integer, Integer> size, int numSubstrates, int layerDepth) {
		this(size, numSubstrates, layerDepth, DEFAULT_ACTIVATION_FUNCTION);
	}

	public HiddenSubstrateGroup(Pair<Integer, Integer> size, int numSubstrates, int layerDepth, int fType) {
		this(size, numSubstrates, layerDepth, fType, null);
	}
	
	public HiddenSubstrateGroup(Pair<Integer, Integer> size, int numSubstrates, Triple<Integer, Integer, Integer> hiddenSubstrateGroupStartLocation, int fType) {
		this.substrateSize = size;
		this.numSubstrates = numSubstrates;
		this.hiddenSubstrateGroupStartLocation = hiddenSubstrateGroupStartLocation;
		this.fType = fType;
	}

	public HiddenSubstrateGroup(Pair<Integer, Integer> size, int numSubstrates, int layerDepth, int fType, HiddenSubstrateGroup leftNeighbor) {
		this.substrateSize = size;
		this.numSubstrates = numSubstrates;
		if (leftNeighbor != null) {
			hiddenSubstrateGroupStartLocation = new Triple<Integer, Integer, Integer>(leftNeighbor.hiddenSubstrateGroupStartLocation.t1 + leftNeighbor.numSubstrates, layerDepth, 0);
		} else {
			hiddenSubstrateGroupStartLocation = new Triple<Integer, Integer, Integer>(0, layerDepth, 0);
		}
		this.fType = fType;
	}

	public HiddenSubstrateGroup copy() {
		return new HiddenSubstrateGroup(this.substrateSize, this.numSubstrates, this.hiddenSubstrateGroupStartLocation, this.fType);
	}
}
