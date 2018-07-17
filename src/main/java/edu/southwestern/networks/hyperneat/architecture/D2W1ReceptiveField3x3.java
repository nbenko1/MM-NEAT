package edu.southwestern.networks.hyperneat.architecture;

import java.util.ArrayList;
import java.util.List;

import edu.southwestern.networks.hyperneat.HiddenSubstrateGroup;
import edu.southwestern.networks.hyperneat.HyperNEATTask;
import edu.southwestern.networks.hyperneat.SubstrateConnectivity;
import edu.southwestern.util.datastructures.Pair;

public class D2W1ReceptiveField3x3 implements SubstrateArchitectureDefinition{

	@Override
	public List<HiddenSubstrateGroup> getNetworkHiddenArchitecture() {
		List<HiddenSubstrateGroup> networkHiddenArchitecture = new ArrayList<HiddenSubstrateGroup>();
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 8, 18, 0));
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 6, 16, 1));
		return networkHiddenArchitecture;
	}

	@Override
	public List<SubstrateConnectivity> getSubstrateConnectivity(HyperNEATTask hnt) {
		List<SubstrateConnectivity> substrateConnectivity = new ArrayList<SubstrateConnectivity>();
		Pair<List<String>, List<String>> io = FlexibleSubstrateArchitecture.getInputAndOutputNames(hnt);
		List<HiddenSubstrateGroup> networkHiddenArchitecture = getNetworkHiddenArchitecture();
		FlexibleSubstrateArchitecture.connectInputToFirstHidden(substrateConnectivity, io.t1, networkHiddenArchitecture, 3, 3);
		FlexibleSubstrateArchitecture.linearlyConnectAllGroups(substrateConnectivity, networkHiddenArchitecture, 3, 3);
		FlexibleSubstrateArchitecture.connectLastHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_FULL);
		return substrateConnectivity;
	}

}
