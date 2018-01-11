package edu.southwestern.tasks.interactive.soundanimator;

import java.io.FileNotFoundException;

import edu.southwestern.MMNEAT.MMNEAT;
import edu.southwestern.networks.Network;
import edu.southwestern.tasks.interactive.animationbreeder.AnimationBreederTask;

public class SoundAnimatorTask<T extends Network> extends AnimationBreederTask<T> {

	public SoundAnimatorTask() throws IllegalAccessException {
		super();
	}

	/**
	 * Allows for quick and easy launching without saving any files
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MMNEAT.main(new String[]{"runNumber:5","randomSeed:5","trials:1","mu:16","maxGens:500","io:false","netio:false","mating:true", "simplifiedInteractiveInterface:false", "fs:false", "task:edu.southwestern.tasks.interactive.soundanimator.SoundAnimatorTask","allowMultipleFunctions:true","ftype:0","netChangeActivationRate:0.3","cleanFrequency:-1","recurrency:false","ea:edu.southwestern.evolution.selectiveBreeding.SelectiveBreedingEA","imageWidth:500","imageHeight:500","imageSize:200","includeFullSigmoidFunction:true","includeFullGaussFunction:true","includeCosineFunction:true","includeGaussFunction:false","includeIdFunction:true","includeTriangleWaveFunction:false","includeSquareWaveFunction:false","includeFullSawtoothFunction:false","includeSigmoidFunction:false","includeAbsValFunction:false","includeSawtoothFunction:false"});
		} catch (FileNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
