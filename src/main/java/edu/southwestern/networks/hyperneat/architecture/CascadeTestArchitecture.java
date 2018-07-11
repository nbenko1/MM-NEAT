package edu.southwestern.networks.hyperneat.architecture;

import java.util.ArrayList;
import java.util.List;

import edu.southwestern.networks.hyperneat.HiddenSubstrateGroup;
import edu.southwestern.networks.hyperneat.HyperNEATTask;
import edu.southwestern.networks.hyperneat.SubstrateConnectivity;
import edu.southwestern.networks.hyperneat.architecture.FlexibleSubstrateArchitecture;
import edu.southwestern.networks.hyperneat.architecture.SubstrateArchitectureDefinition;
import edu.southwestern.util.datastructures.Pair;

/**
 * Just a test to confirm cascade works properly
 * @author DevonFulcher
 *
 */
public class CascadeTestArchitecture implements SubstrateArchitectureDefinition{

	@Override
	public List<HiddenSubstrateGroup> getNetworkHiddenArchitecture() {
		List<HiddenSubstrateGroup> networkHiddenArchitecture = new ArrayList<HiddenSubstrateGroup>();
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 10, 20, 0));
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 10, 20, 1));
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(4, 10, 20, 2));
		return networkHiddenArchitecture;
	}
	
	@Override
	public List<SubstrateConnectivity> getSubstrateConnectivity(HyperNEATTask hnt) {
		List<SubstrateConnectivity> substrateConnectivity = new ArrayList<SubstrateConnectivity>();
		Pair<List<String>, List<String>> io = FlexibleSubstrateArchitecture.getInputAndOutputNames(hnt);
		List<HiddenSubstrateGroup> networkHiddenArchitecture = getNetworkHiddenArchitecture();
		FlexibleSubstrateArchitecture.connectInputToFirstHidden(substrateConnectivity, io.t1, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_CONVOLUTION);
		FlexibleSubstrateArchitecture.linearlyConnectAllGroups(substrateConnectivity, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_CONVOLUTION);
		FlexibleSubstrateArchitecture.connectHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, 3, 3, 0);
		FlexibleSubstrateArchitecture.connectHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, 3, 3, 1);
		FlexibleSubstrateArchitecture.connectLastHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_FULL);
		return substrateConnectivity;
	}
}
