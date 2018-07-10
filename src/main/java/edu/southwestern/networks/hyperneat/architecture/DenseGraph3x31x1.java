package edu.southwestern.networks.hyperneat.architecture;

import java.util.ArrayList;
import java.util.List;

import edu.southwestern.networks.hyperneat.HiddenSubstrateGroup;
import edu.southwestern.networks.hyperneat.HyperNEATTask;
import edu.southwestern.networks.hyperneat.SubstrateConnectivity;
import edu.southwestern.util.datastructures.Pair;
import edu.southwestern.util.datastructures.Triple;

/**
 * D2W1 Convolutional architecture where each hidden substrate is connected to each input and output. The inputs
 * are connected to the first hidden layer by a 3x3 receptive field and the first hidden layer is connected to the second hidden layer
 * by a 1x1 receptive field.
 * @author Devon Fulcher
 */
public class DenseGraph3x31x1 implements SubstrateArchitectureDefinition{

	@Override
	public List<HiddenSubstrateGroup> getNetworkHiddenArchitecture() {
		List<HiddenSubstrateGroup> networkHiddenArchitecture = new ArrayList<HiddenSubstrateGroup>();
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 8, 18, 0));
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 8, 18, 1));
		return networkHiddenArchitecture;
	}

	@Override
	public List<SubstrateConnectivity> getSubstrateConnectivity(HyperNEATTask hnt) {
		List<SubstrateConnectivity> substrateConnectivity = new ArrayList<SubstrateConnectivity>();
		Pair<List<String>, List<String>> io = FlexibleSubstrateArchitecture.getInputAndOutputNames(hnt);
		List<HiddenSubstrateGroup> networkHiddenArchitecture = getNetworkHiddenArchitecture();
		FlexibleSubstrateArchitecture.connectInputToFirstHidden(substrateConnectivity, io.t1, networkHiddenArchitecture, 3, 3);
		FlexibleSubstrateArchitecture.linearlyConnectAllGroups(substrateConnectivity, networkHiddenArchitecture, 1, 1);
		FlexibleSubstrateArchitecture.connectInputToHidden(substrateConnectivity, io.t1, networkHiddenArchitecture, 3, 3, 1);
		FlexibleSubstrateArchitecture.connectLastHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_FULL);
		FlexibleSubstrateArchitecture.connectHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, -1, -1, 0);
		return substrateConnectivity;
	}

}
