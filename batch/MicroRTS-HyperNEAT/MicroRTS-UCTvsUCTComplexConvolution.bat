cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:microRTS trials:3 maxGens:500 mu:25 io:true netio:true mating:true task:edu.southwestern.tasks.microrts.MicroRTSTask cleanOldNetworks:true fs:false log:MicroRTS-UCTvsUCTComplexConvolution saveTo:UCTvsUCTComplexConvolution microRTSAgent:micro.ai.mcts.uct.UCT microRTSOpponent:micro.ai.mcts.uct.UCT microRTSMapSequence:edu.southwestern.tasks.microrts.iterativeevolution.DecceleratingMapSequence mRTSMobileUnits:false mRTSBuildings:false mRTSAll:false mRTSResources:true hyperNEAT:true genotype:edu.southwestern.evolution.genotypes.HyperNEATCPPNGenotype allowMultipleFunctions:true ftype:1 netChangeActivationRate:0.3 extraHNLinks:true convolution:true mRTSAllSqrt3MobileUnits:true mRTSMyBuildingGradientMobileUnits:true watch:false stepByStep:false monitorSubstrates:false HNProcessWidth:3