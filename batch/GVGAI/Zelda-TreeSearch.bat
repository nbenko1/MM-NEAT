cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:gvgai trials:10 maxGens:500 mu:100 io:true netio:true mating:true task:edu.southwestern.tasks.gvgai.GVGAISinglePlayerTask cleanOldNetworks:true fs:false log:Zelda-ZeldaTreeSearch saveTo:ZeldaTreeSearch gvgaiGame:zelda gvgaiLevel:0 gvgaiPlayer:edu.southwestern.tasks.gvgai.GVGAITreeSearchNNPlayer