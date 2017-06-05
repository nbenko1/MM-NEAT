package edu.utexas.cs.nn.tasks.microrts;

//TODO: Comment

// TODO: Also, this should be an interface rather than a static class
public class MapSequence {

	private static final int gensPerMap = 1;
	private static final String[] maps = new String[]{
			"8x8/basesWorkers8x8.xml",
			"12x12/basesWorkers12x12.xml",
			"16x16/basesWorkers16x16.xml",
			"24x24/basesWorkers24x24.xml",
			"BWDistantResources32x32.xml",
			//jump from 32x32 to 120x120 seems stark, maybe eventually create intermediate map
			"BroodWar/(4)BloodBath.scmA.xml",
	};

	// Once this is made into an interface, this will not be static.
	public static String getAppropriateMap(int generation){
		return maps[Math.min(generation / gensPerMap, maps.length-1)];

	}
}