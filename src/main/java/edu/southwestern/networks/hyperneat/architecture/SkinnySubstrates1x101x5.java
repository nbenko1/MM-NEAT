package edu.southwestern.networks.hyperneat.architecture;

import java.util.ArrayList;
import java.util.List;

import edu.southwestern.networks.hyperneat.HiddenSubstrateGroup;
import edu.southwestern.networks.hyperneat.HyperNEATTask;
import edu.southwestern.networks.hyperneat.SubstrateConnectivity;
import edu.southwestern.util.datastructures.Pair;

/**
 * Input connected to 1 first hidden substrate with 3x3 convolutional. First hidden connected to a 1x10 second hidden with full. 
 * Second hidden connected to 1x5 third hidden with full. Last hidden connected to output with full.
 * @author Devon Fulcher
 */
public class SkinnySubstrates1x101x5 implements SubstrateArchitectureDefinition{

	@Override
	public List<HiddenSubstrateGroup> getNetworkHiddenArchitecture() {
		List<HiddenSubstrateGroup> networkHiddenArchitecture = new ArrayList<HiddenSubstrateGroup>();
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 8, 18, 0));
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 10, 1, 1));
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 5, 1, 2));
		return networkHiddenArchitecture;
	}

	@Override
	public List<SubstrateConnectivity> getSubstrateConnectivity(HyperNEATTask hnt) {
		List<SubstrateConnectivity> substrateConnectivity = new ArrayList<SubstrateConnectivity>();
		Pair<List<String>, List<String>> io = FlexibleSubstrateArchitecture.getInputAndOutputNames(hnt);
		List<HiddenSubstrateGroup> networkHiddenArchitecture = getNetworkHiddenArchitecture();
		FlexibleSubstrateArchitecture.connectInputToFirstHidden(substrateConnectivity, io.t1, networkHiddenArchitecture, 3, 3);
		FlexibleSubstrateArchitecture.linearlyConnectAllGroups(substrateConnectivity, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_FULL);
		FlexibleSubstrateArchitecture.connectLastHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_FULL);
		return substrateConnectivity;
	}

}
