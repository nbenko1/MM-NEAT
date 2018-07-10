package edu.southwestern.networks.hyperneat.architecture;

import java.util.ArrayList;
import java.util.List;

import edu.southwestern.networks.hyperneat.HiddenSubstrateGroup;
import edu.southwestern.networks.hyperneat.HyperNEATTask;
import edu.southwestern.networks.hyperneat.SubstrateConnectivity;
import edu.southwestern.util.datastructures.Pair;

/**
 * Inputs are connected to the hidden layer by a 3x3. The inputs 
 * @author Devon Fulcher
 */
public class SpecificInputsPacman implements SubstrateArchitectureDefinition{

	@Override
	public List<HiddenSubstrateGroup> getNetworkHiddenArchitecture() {
		List<HiddenSubstrateGroup> networkHiddenArchitecture = new ArrayList<HiddenSubstrateGroup>();
		networkHiddenArchitecture.add(new HiddenSubstrateGroup(1, 8, 18, 0));
		//
		return networkHiddenArchitecture;
	}

	@Override
	public List<SubstrateConnectivity> getSubstrateConnectivity(HyperNEATTask hnt) {
		List<SubstrateConnectivity> substrateConnectivity = new ArrayList<SubstrateConnectivity>();
		Pair<List<String>, List<String>> io = FlexibleSubstrateArchitecture.getInputAndOutputNames(hnt);
		List<String> firstInput = new ArrayList<String>();
		firstInput.add(io.t1.get(0));
		List<String> secondInput = new ArrayList<String>();
		secondInput.add(io.t1.get(1));
		List<String> thirdInput = new ArrayList<String>();
		thirdInput.add(io.t1.get(2));
		List<String> fourthInput = new ArrayList<String>();
		fourthInput.add(io.t1.get(3));
		List<String> fifthInput = new ArrayList<String>();
		fifthInput.add(io.t1.get(4));
		List<HiddenSubstrateGroup> networkHiddenArchitecture = getNetworkHiddenArchitecture();
		FlexibleSubstrateArchitecture.connectInputToHidden(substrateConnectivity, firstInput, networkHiddenArchitecture, 3, 3, 0);
		FlexibleSubstrateArchitecture.connectInputToHidden(substrateConnectivity, secondInput, networkHiddenArchitecture, 3, 3, 1);
		FlexibleSubstrateArchitecture.connectInputToHidden(substrateConnectivity, thirdInput, networkHiddenArchitecture, 3, 3, 2);
		FlexibleSubstrateArchitecture.connectInputToHidden(substrateConnectivity, fourthInput, networkHiddenArchitecture, 3, 3, 3);
		FlexibleSubstrateArchitecture.connectInputToHidden(substrateConnectivity, fifthInput, networkHiddenArchitecture, 3, 3, 4);
		FlexibleSubstrateArchitecture.connectHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, -1, -1, 0);
		FlexibleSubstrateArchitecture.connectHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, -1, -1, 1);
		FlexibleSubstrateArchitecture.connectHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, -1, -1, 2);
		FlexibleSubstrateArchitecture.connectHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, -1, -1, 3);
		FlexibleSubstrateArchitecture.connectHiddenToOutput(substrateConnectivity, io.t2, networkHiddenArchitecture, -1, -1, 4);
		return substrateConnectivity;
	}

}
