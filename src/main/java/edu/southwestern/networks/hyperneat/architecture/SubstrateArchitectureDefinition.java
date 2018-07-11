package edu.southwestern.networks.hyperneat.architecture;

import java.util.List;

import edu.southwestern.networks.hyperneat.HiddenSubstrateGroup;
import edu.southwestern.networks.hyperneat.HyperNEATTask;
import edu.southwestern.networks.hyperneat.SubstrateConnectivity;

public interface SubstrateArchitectureDefinition {

		
		/**
		 *	Output and input layers are excluded because those are defined by the task. 
		 */
		public List<HiddenSubstrateGroup> getNetworkHiddenArchitecture();
		
		
		/**
		 *  @param the HyperNEATTask
		 *  @return List of SubstrateConnectivity of the network
		 */
		public List<SubstrateConnectivity> getSubstrateConnectivity(HyperNEATTask hnt);
		
};
