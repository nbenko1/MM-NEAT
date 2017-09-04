cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:vizdoomhealthgather trials:5 maxGens:100 mu:10 io:true netio:true mating:true task:edu.southwestern.tasks.vizdoom.VizDoomHealthGatherTask cleanOldNetworks:true fs:false noisyTaskStat:edu.southwestern.util.stats.Average log:HealthGather-moHNSmudgeRow saveTo:moHNSmudgeRow gameWad:freedoom2.wad doomEpisodeLength:10000 doomInputStartX:0 doomInputStartY:75 doomInputHeight:75 doomInputWidth:200 doomInputPixelSmudge:9 doomSmudgeStat:edu.southwestern.util.stats.MostExtreme moVizDoom:true doomInputColorVal:0 hyperNEAT:true genotype:edu.southwestern.evolution.genotypes.HyperNEATCPPNGenotype allowMultipleFunctions:true ftype:1 netChangeActivationRate:0.3 printFitness:true