package edu.southwestern.networks.hyperneat.architecture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.southwestern.networks.hyperneat.HiddenSubstrateGroup;
import edu.southwestern.networks.hyperneat.HyperNEATTask;
import edu.southwestern.networks.hyperneat.Substrate;
import edu.southwestern.networks.hyperneat.SubstrateConnectivity;
import edu.southwestern.parameters.Parameters;
import edu.southwestern.util.datastructures.Pair;
import edu.southwestern.util.datastructures.Triple;

/**
 * @author Devon Fulcher
 */

public class FlexibleSubstrateArchitecture {

	/**
	 * @param hnt the hyperNEATTask
	 * @return A list of triples where the length of the list is equal to the depth of the number of hidden layers.
	 *  Each index of the list is a new layer. Index 0 is the first hidden layer.
	 *	The first param of each triple is the number of substrates in that corresponding layer. 
	 *	The second param is the width of each substrate. The third param is the height of each substrate.
	 *	Output and input layers are excluded because those are defined by the task. 
	 */
	public static List<HiddenSubstrateGroup> getHiddenArchitecture(HyperNEATTask hnt) {
		List<HiddenSubstrateGroup> hiddenArchitecture = new ArrayList<HiddenSubstrateGroup>();
		List<Substrate> substrateInformation = hnt.getSubstrateInformation();	
		int numSubstratesPerLayer = 0; //this will equal the number of substrates per layer when it is added to hiddenArchitecture
		int layerCount = 0;
		int previousSubstrateType = Substrate.INPUT_SUBSTRATE;
		int previousYCoordinate = -1, previousWidth = -1, previousHeight = -1; //these are invalid values. they are just here to initialize.
		int currentSubstrateType, currentWidth, currentHeight, currentYCoordinate;
		Iterator<Substrate> it_substrateInformation = substrateInformation.iterator();
		while (it_substrateInformation.hasNext()) {
			Substrate currentSubstrate = it_substrateInformation.next(); 
			currentSubstrateType = currentSubstrate.getStype();
			currentYCoordinate = currentSubstrate.getSubLocation().t2;
			currentWidth = currentSubstrate.getSize().t1;
			currentHeight = currentSubstrate.getSize().t2;
			if (previousSubstrateType == Substrate.PROCCESS_SUBSTRATE && currentYCoordinate != previousYCoordinate) {
				assert previousWidth > 0 && previousHeight > 0 && numSubstratesPerLayer > 0;
				hiddenArchitecture.add(new HiddenSubstrateGroup(new Pair<Integer, Integer>(previousWidth, previousHeight), numSubstratesPerLayer, layerCount));
				numSubstratesPerLayer = 0;
				layerCount++;
			}
			if (currentSubstrateType == Substrate.PROCCESS_SUBSTRATE) {
				numSubstratesPerLayer++;
			}
			previousSubstrateType = currentSubstrateType;
			previousYCoordinate = currentYCoordinate;
			previousWidth = currentWidth;
			previousHeight = currentHeight;
		}
		assert hiddenArchitecture.size() > 0;
		return hiddenArchitecture;
	}

	/**
	 * gets the input and output names from the hyperNEATTask
	 * @param hnt theHyperNEATTask
	 * @return the input and output names stored in a pair. looks like (input names, output names)
	 */
	public static Pair<List<String>,List<String>> getInputAndOutputNames(HyperNEATTask hnt) {
		List<Substrate> substrateInformation = hnt.getSubstrateInformation();
		List<String> inputSubstrateNames = new ArrayList<String>();
		List<String> outputSubstrateNames = new ArrayList<String>();
		for (Substrate substrate: substrateInformation) {
			if (substrate.getStype() == Substrate.INPUT_SUBSTRATE) {
				inputSubstrateNames.add(substrate.getName());
			} else if (substrate.getStype() == Substrate.OUTPUT_SUBSTRATE) {
				outputSubstrateNames.add(substrate.getName());
			}
		}
		return new Pair<List<String>, List<String>>(inputSubstrateNames, outputSubstrateNames);
	}
	
	/**
	 * @param hnt the HyperNEATTask
	 * @return List of the SubstrateConnectivity of the network
	 */
	public static List<SubstrateConnectivity> getDefaultConnectivity(HyperNEATTask hnt) {
		Pair<List<String>,List<String>> inputAndOutputNames = getInputAndOutputNames(hnt);
		return getDefaultConnectivity(inputAndOutputNames.t1, inputAndOutputNames.t2, getHiddenArchitecture(hnt));
	}

	/**
	 * @param inputSubstrateNames List of input substrate names
	 * @param outputSubstrateNames List of output substrate names
	 * @return List of SubstrateConnectivity of the network
	 */
	public static List<SubstrateConnectivity> getDefaultConnectivity(
			List<String> inputSubstrateNames, 
			List<String> outputSubstrateNames, 
			List<HiddenSubstrateGroup> networkHiddenArchitecture) {
		List<SubstrateConnectivity> networkConnectivity = new ArrayList<SubstrateConnectivity>();
		//connects input layer to first hidden/process layer
		connectInputToFirstHidden(networkConnectivity, inputSubstrateNames, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_CONVOLUTION);

		//connects adjacent, possibly convolutional hidden layers
		linearlyConnectAllGroups(networkConnectivity, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_CONVOLUTION);

		//connects last hidden/process layer to output layer
		connectLastHiddenToOutput(networkConnectivity, outputSubstrateNames, networkHiddenArchitecture, SubstrateConnectivity.CTYPE_FULL);

		return networkConnectivity;
	}

	/**
	 * connects input layer to first hidden/process layer
	 * @param networkConnectivity list that connectivity is appended to
	 * @param inputSubstrateNames list of each input substrate name
	 * @param networkHiddenArchitecture architecture of hidden layers
	 * @param connectivityType how these two substrates are connected(i.e. full, convolutional,...)
	 */
	public static void connectInputToFirstHidden(
			List<SubstrateConnectivity> networkConnectivity, 
			List<String> inputSubstrateNames, 
			List<HiddenSubstrateGroup> networkHiddenArchitecture, 
			int connectivityType) {
		if (connectivityType == SubstrateConnectivity.CTYPE_CONVOLUTION) {
			connectInputToFirstHidden(networkConnectivity, inputSubstrateNames, networkHiddenArchitecture,
					Parameters.parameters.integerParameter("receptiveFieldWidth"), Parameters.parameters.integerParameter("receptiveFieldHeight"));
		} else {
			connectInputToFirstHidden(networkConnectivity, inputSubstrateNames, networkHiddenArchitecture, -1 , -1);
		}
	}

	/**
	 * connects input layer to first hidden/process layer with specified receptive field
	 * @param networkConnectivity list that connectivity is appended to
	 * @param inputSubstrateNames list of each input substrate name
	 * @param networkHiddenArchitecture architecture of hidden layers
	 * @param receptiveFieldWidth width of receptive field window, -1 if nonconvolutional
	 * @param receptiveFieldHeight height of receptive field window, - 1 if nonconvolutional
	 */
	public static void connectInputToFirstHidden(
			List<SubstrateConnectivity> networkConnectivity, 
			List<String> inputSubstrateNames, 
			List<HiddenSubstrateGroup> networkHiddenArchitecture, 
			int receptiveFieldWidth, int receptiveFieldHeight) {
		if (networkHiddenArchitecture.size() > 0) {
			connectInputToHidden(networkConnectivity, inputSubstrateNames, networkHiddenArchitecture,
					receptiveFieldWidth, receptiveFieldHeight, 0);
		}
	}
	
	/**
	 * connects input layer to first hidden/process layer with specified receptive field
	 * @param networkConnectivity list that connectivity is appended to
	 * @param inputSubstrateNames list of each input substrate name
	 * @param networkHiddenArchitecture architecture of hidden layers
	 * @param receptiveFieldWidth width of receptive field window, -1 if nonconvolutional
	 * @param receptiveFieldHeight height of receptive field window, - 1 if nonconvolutional
	 * @param locationOfLayer the index of the hidden layer that will be connected from the input and the y position of this hidden layer in vector space
	 */
	public static void connectInputToHidden(
			List<SubstrateConnectivity> networkConnectivity, 
			List<String> inputSubstrateNames,
			List<HiddenSubstrateGroup> networkHiddenArchitecture,
			int receptiveFieldWidth, int receptiveFieldHeight, int locationOfLayer) {
		HiddenSubstrateGroup hiddenSubstrateGroup = networkHiddenArchitecture.get(locationOfLayer);
		Triple<Integer, Integer, Integer> hiddenSubstrateGroupStartLocation = hiddenSubstrateGroup.hiddenSubstrateGroupStartLocation;
		int numSubstrates = networkHiddenArchitecture.get(locationOfLayer).numSubstrates; 
		for (String in: inputSubstrateNames) {
			for (int x = 0; x < numSubstrates; x++) {
				networkConnectivity.add(new SubstrateConnectivity
						(in, "process(" + (hiddenSubstrateGroupStartLocation.t1 + x) + "," + hiddenSubstrateGroupStartLocation.t2 + ")", receptiveFieldWidth, receptiveFieldHeight));
			}
		}
	}

	/**
	 * connects all adjacent hidden substrate layers
	 * @param networkConnectivity
	 * @param networkHiddenArchitecture
	 * @param connectivityType how these two substrates are connected (i.e. full, convolutional,...)
	 */
	public static void linearlyConnectAllGroups(
			List<SubstrateConnectivity> networkConnectivity, 
			List<HiddenSubstrateGroup> networkHiddenArchitecture,
			int connectivityType) {
		if (connectivityType == SubstrateConnectivity.CTYPE_CONVOLUTION) {
			linearlyConnectAllGroups(networkConnectivity, networkHiddenArchitecture,
					Parameters.parameters.integerParameter("receptiveFieldWidth"), Parameters.parameters.integerParameter("receptiveFieldHeight"));
		} else {
			linearlyConnectAllGroups(networkConnectivity, networkHiddenArchitecture, -1, -1);
		}
	}

	/**
	 * linearly connects all HiddenSubstrateGroups. Equivalent to 
	 * connecting all adjacent hidden substrate layers if each group exists in its own layer
	 * @param networkConnectivity list that connectivity is appended to
	 * @param networkHiddenArchitecture architecture of hidden layers
	 * @param receptiveFieldWidth width of receptive field window, -1 if nonconvolutional
	 * @param receptiveFieldHeight height of receptive field window, - 1 if nonconvolutional
	 */
	public static void linearlyConnectAllGroups(
			List<SubstrateConnectivity> networkConnectivity, 
			List<HiddenSubstrateGroup> networkHiddenArchitecture,
			int receptiveFieldWidth, int receptiveFieldHeight) {
		int numGroups = networkHiddenArchitecture.size();
		for (int i = 0; i < numGroups - 1; i++) {
			HiddenSubstrateGroup sourceGroup = networkHiddenArchitecture.get(i);
			HiddenSubstrateGroup targetGroup = networkHiddenArchitecture.get(i = 1);
			Triple<Integer, Integer, Integer> sourceSubstrateGroupStartLocation = sourceGroup.hiddenSubstrateGroupStartLocation;
			Triple<Integer, Integer, Integer> targetSubstrateGroupStartLocation = targetGroup.hiddenSubstrateGroupStartLocation;
			for (int src = 0; src < sourceGroup.numSubstrates; src++) {
				for (int target = 0; target < targetGroup.numSubstrates; target++) {
					networkConnectivity.add(new SubstrateConnectivity(
							"process(" + (sourceSubstrateGroupStartLocation.t1 + src) + "," + sourceSubstrateGroupStartLocation.t2 + ")", 
							"process(" + (targetSubstrateGroupStartLocation.t1 + target) + "," + targetSubstrateGroupStartLocation.t2 + ")", 
							receptiveFieldWidth, receptiveFieldHeight));
				}
			}
		}
	}
	
	/**
	 * connects two hidden substrate layers with specified receptive field
	 * @param networkConnectivity list that connectivity is appended to
	 * @param networkHiddenArchitecture architecture of hidden layers
	 * @param receptiveFieldWidth width of receptive field window, -1 if nonconvolutional
	 * @param receptiveFieldHeight height of receptive field window, - 1 if nonconvolutional
	 * @param sourceLayerLocation the index of the source hidden layer in networkHiddenArchitecture and the y position of this hidden layer in vector space
	 * @param targetLayerLocationthe index of the target hidden layer in networkHiddenArchitecture and the y position of this hidden layer in vector space
	 */
	public static void connectHiddenToHidden (
			List<SubstrateConnectivity> networkConnectivity, 
			List<Triple<Integer, Integer, Integer>> networkHiddenArchitecture,
			int receptiveFieldWidth, int receptiveFieldHeight, int sourceLayerLocation, int targetLayerLocation) {
		int sourceLayerWidth = networkHiddenArchitecture.get(sourceLayerLocation).t1;
		int targetLayerWidth = networkHiddenArchitecture.get(targetLayerLocation).t1;
		for (int i = 0; i < sourceLayerWidth; i++) {
			for (int j = 0; j < targetLayerWidth; j++) {
				networkConnectivity.add(new SubstrateConnectivity("process(" + i + "," + sourceLayerLocation + ")", "process(" + j + "," + targetLayerLocation + ")", 
						receptiveFieldWidth, receptiveFieldHeight));
			}
		}
	}


	/**
	 * TODO: addPoolingHiddenLayerConnectivity
	 */


	/**
	 * connects last hidden layer to output layer
	 * @param networkConnectivity list that connectivity is appended to
	 * @param outputSubstrateNames list of each output substrate name
	 * @param networkHiddenArchitecture architecture of hidden layers
	 * @param connectivityType how these two substrates are connected (i.e. full, convolutional,...)
	 */
	public static void connectLastHiddenToOutput(
			List<SubstrateConnectivity> networkConnectivity, 
			List<String> outputSubstrateNames,
			List<HiddenSubstrateGroup> networkHiddenArchitecture,
			int connectivityType) {
		if (connectivityType == SubstrateConnectivity.CTYPE_CONVOLUTION) {
			connectLastHiddenToOutput(networkConnectivity, outputSubstrateNames, networkHiddenArchitecture,
					Parameters.parameters.integerParameter("receptiveFieldWidth"), Parameters.parameters.integerParameter("receptiveFieldHeight"));
		} else {
			connectLastHiddenToOutput(networkConnectivity, outputSubstrateNames, networkHiddenArchitecture, -1, -1);
		}
	}

	/**
	 * connects last hidden layer to output layer
	 * @param networkConnectivity list that connectivity is appended to
	 * @param outputSubstrateNames list of each output substrate name
	 * @param networkHiddenArchitecture architecture of hidden layers
	 * @param receptiveFieldWidth width of receptive field window, -1 if nonconvolutional
	 * @param receptiveFieldHeight height of receptive field window, - 1 if nonconvolutional
	 */
	public static void connectLastHiddenToOutput(
			List<SubstrateConnectivity> networkConnectivity, 
			List<String> outputSubstrateNames,
			List<HiddenSubstrateGroup> networkHiddenArchitecture,
			int receptiveFieldWidth, int receptiveFieldHeight) {
		if (networkHiddenArchitecture.size() > 0) {
			connectHiddenToOutput(networkConnectivity, outputSubstrateNames, networkHiddenArchitecture, receptiveFieldWidth, receptiveFieldHeight, networkHiddenArchitecture.size() - 1);
		}
	}

	/**
	 * connects hidden layer to output layer
	 * @param networkConnectivity list that connectivity is appended to
	 * @param outputSubstrateNames list of each output substrate name
	 * @param networkHiddenArchitecture architecture of hidden layers
	 * @param receptiveFieldWidth width of receptive field window, -1 if nonconvolutional
	 * @param receptiveFieldHeight height of receptive field window, - 1 if nonconvolutional
	 * @param locationOfLayer the index of the hidden layer that will be connected to the output and the y position of this hidden layer in vector space
	 */
	public static void connectHiddenToOutput(
			List<SubstrateConnectivity> networkConnectivity, 
			List<String> outputSubstrateNames,
			List<HiddenSubstrateGroup> networkHiddenArchitecture,
			int receptiveFieldWidth, int receptiveFieldHeight, int locationOfLayer) {
		HiddenSubstrateGroup hiddenSubstrateGroup = networkHiddenArchitecture.get(locationOfLayer);
		for (int i = 0; i < hiddenSubstrateGroup.numSubstrates; i++) {
			Triple<Integer, Integer, Integer> hiddenSubstrateGroupStartLocation = hiddenSubstrateGroup.hiddenSubstrateGroupStartLocation;
			for (String out: outputSubstrateNames) {
				networkConnectivity.add(new SubstrateConnectivity
						("process(" + (hiddenSubstrateGroupStartLocation.t1 + i) + "," + hiddenSubstrateGroupStartLocation.t2 + ")", 
								out, receptiveFieldWidth, receptiveFieldHeight));
			}
		}
	}
}

