cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:picbreeder trials:1 mu:16 maxGens:500 io:false netio:false mating:true fs:true task:edu.southwestern.tasks.interactive.picbreeder.PicbreederTask log:Picbreeder-Advanced saveTo:Advanced allowMultipleFunctions:true ftype:0 watch:false netChangeActivationRate:0.3 cleanFrequency:-1 recurrency:false saveInteractiveSelections:false simplifiedInteractiveInterface:false saveAllChampions:false cleanOldNetworks:false logTWEANNData:false logMutationAndLineage:false ea:edu.southwestern.evolution.selectiveBreeding.SelectiveBreedingEA imageWidth:2000 imageHeight:2000 imageSize:200
