cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:vizdoomhealthgathersupreme trials:5 maxGens:500 mu:10 io:true netio:true mating:true task:edu.southwestern.tasks.vizdoom.VizDoomHealthGatherSupremeTask cleanOldNetworks:true fs:false noisyTaskStat:edu.southwestern.util.stats.Average log:HealthSupreme-Control saveTo:Control gameWad:freedoom2.wad doomEpisodeLength:10000 doomInputStartX:0 doomInputStartY:75 doomInputHeight:75 doomInputWidth:200 doomInputPixelSmudge:9 doomSmudgeStat:edu.southwestern.util.stats.MostExtreme moVizDoom:true doomInputColorVal:0 printFitness:true