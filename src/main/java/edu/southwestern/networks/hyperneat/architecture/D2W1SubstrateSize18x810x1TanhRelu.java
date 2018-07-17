package edu.southwestern.networks.hyperneat.architecture;

import java.util.ArrayList;
import java.util.List;

import edu.southwestern.networks.ActivationFunctions;
import edu.southwestern.networks.hyperneat.HiddenSubstrateGroup;
import edu.southwestern.networks.hyperneat.HyperNEATTask;
import edu.southwestern.networks.hyperneat.SubstrateConnectivity;
import edu.southwestern.util.datastructures.Pair;

public class D2W1SubstrateSize18x810x1TanhRelu implements SubstrateArchitectureDefinition{

	@Override
	public List<HiddenSubstrateGroup> getNetworkHiddenArchitecture() {
		List<HiddenSubstrateGroup> networkHiddenArchitecture = new ArrayList<HiddenSubstrateGroup>();
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(new Pair<Integer, Integer>(8, 18), 1, 0, ActivationFunctions.FTYPE_TANH));
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(new Pair<Integer, Integer>(1, 10), 1, 1, ActivationFunctions.FTYPE_RE_LU));
		return networkHiddenArchitecture;
	}

	@Override
	public List<SubstrateConnectivity> getSubstrateConnectivity(HyperNEATTask hnt) {
		List<SubstrateConnectivity> substrateConnectivity = new ArrayList<SubstrateConnectivity>();
		Pair<List<String>, List<String>> io = FlexibleSubstrateArchitecture.getInputAndOutputNames(hnt);
		List<HiddenSubstrateGroup> networkHiddenArchitecture = getNetworkHiddenArchitecture();
		FlexibleSubstrateArchitecture.connectInputToFirstHidden(substrateConnectivity, io.t1, networkHiddenArchitecture, 3, 3);
		FlexibleSubstrateArchitecture.linearlyConnectAllGroups(substrateConnectivity, networkHiddenArchitecture, -1, -1);
		FlexibleSubstrateArchitecture.connectLastHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_FULL);
		return substrateConnectivity;
	}

}
