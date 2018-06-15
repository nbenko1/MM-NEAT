cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:tetris trials:3 maxGens:500 mu:20 io:true netio:true mating:true task:edu.southwestern.tasks.rlglue.tetris.HyperNEATTetrisTask  rlGlueEnvironment:org.rlcommunity.environments.tetris.Tetris rlGlueExtractor:edu.southwestern.tasks.rlglue.featureextractors.tetris.RawTetrisStateExtractor tetrisTimeSteps:true tetrisBlocksOnScreen:false  rlGlueAgent:edu.southwestern.tasks.rlglue.tetris.TetrisAfterStateAgent splitRawTetrisInputs:true senseHolesDifferently:true hyperNEAT:true genotype:edu.southwestern.evolution.genotypes.HyperNEATCPPNGenotype allowMultipleFunctions:true ftype:1 netChangeActivationRate:0.3 substrateMapping:edu.southwestern.networks.hyperneat.BottomSubstrateMapping steps:500000 perLinkMutateRate:0.05 netLinkRate:0.4 netSpliceRate:0.2 crossoverRate:0.5 log:Tetris-ConvolutionDepth1Width1IntelligentNetworkCharacterizationSubCoordsSyl5 saveTo:ConvolutionDepth1Width1IntelligentNetworkCharacterizationSubCoordsSyl5 HNProcessDepth:1 HNProcessWidth:1 convolution:true ea:edu.southwestern.evolution.nsga2.bd.BDNSGA2 behaviorCharacterization:edu.southwestern.evolution.nsga2.bd.characterizations.GeneralNetworkCharacterization syllabusSize:5 substrateLocationInputs:true substrateBiasLocationInputs:true