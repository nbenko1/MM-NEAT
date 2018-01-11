package edu.southwestern.tasks.interactive.soundanimator;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import edu.southwestern.MMNEAT.MMNEAT;
import edu.southwestern.networks.Network;
import edu.southwestern.parameters.Parameters;
import edu.southwestern.tasks.interactive.animationbreeder.AnimationBreederTask;
import edu.southwestern.util.graphics.AnimationUtil;
import edu.southwestern.util.sound.SoundToArray;

public class SoundAnimatorTask<T extends Network> extends AnimationBreederTask<T> {

	// Save sound amplitudes here
	private double[] soundArray;
	
	public SoundAnimatorTask() throws IllegalAccessException {
		super();
		
		// TODO: Replace this with interesting values loaded from a file.
		soundArray = new double[50];
		// TODO: Something like this:
		//soundArray = SoundToArray.readDoubleArrayFromStringAudio(<relative path to WAV file>);

	}
	
	protected BufferedImage[] getAnimationImages(T cppn, int startFrame, int endFrame, boolean beingSaved) {
		// TODO: Redefine this to use modified version of imagesFromCPPN that takes sound array input
		return AnimationUtil.imagesFromCPPN(cppn, picSize, picSize, startFrame, endFrame, getInputMultipliers());
	}

	@Override
	public int numCPPNInputs() {
		return 0; // define 
	}

	@Override
	public int numCPPNOutputs() {
		return 0; // define
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
