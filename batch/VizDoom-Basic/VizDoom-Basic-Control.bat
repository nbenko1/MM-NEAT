cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:vizdoombasic trials:5 maxGens:100 mu:10 io:true netio:true mating:true task:edu.southwestern.tasks.vizdoom.VizDoomBasicShootTask cleanOldNetworks:true fs:false noisyTaskStat:edu.southwestern.util.stats.Average log:Basic-Control saveTo:Control gameWad:freedoom2.wad watch:false stepByStep:false printFitness:true