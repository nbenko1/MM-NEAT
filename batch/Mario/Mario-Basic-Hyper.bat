cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:mario trials:10 maxGens:500 mu:100 io:true netio:true mating:true task:edu.southwestern.tasks.mario.MarioTask cleanOldNetworks:true fs:false log:Mario-Hyper saveTo:Hyper watch:false marioInputStartX:-3 marioInputStartY:-2 marioInputWidth:12 marioInputHeight:5 showMarioInputs:false hyperNEAT:true genotype:edu.southwestern.evolution.genotypes.HyperNEATCPPNGenotype allowMultipleFunctions:true ftype:1 netChangeActivationRate:0.3 moMario:true marioJumpTimeout:175 marioStuckTimeout:150