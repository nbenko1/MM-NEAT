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
		super(false, false, soundLength());
		soundArray = SoundToArray.readDoubleArrayFromStringAudio(Parameters.parameters.stringOptions.get("soundAnimationWAVFile"));
	}

	/**
	 * This length value is required for the super constructor, so the sound file
	 * gets loaded here just to get the length of the sound, and again in the actual
	 * constructor.
	 * @return Length of sound clip for animations
	 */
	public static int soundLength() {
		double[] temp = SoundToArray.readDoubleArrayFromStringAudio(Parameters.parameters.stringOptions.get("soundAnimationWAVFile"));
		return temp.length;
	}
	
	@Override
	protected BufferedImage getButtonImage(T phenotype, int width, int height, double[] inputMultipliers) {
		// Just get first frame for button. Slightly inefficent though, since all animation frames were pre-computed
		return AnimationUtil.imagesFromCPPN(phenotype, picSize, picSize, 0, 1, getInputMultipliers(), new double[] {soundArray[0]})[0];
	}

	
	protected BufferedImage[] getAnimationImages(T cppn, int startFrame, int endFrame, boolean beingSaved) {
		//System.out.println("start:"+startFrame + ",end:"+endFrame);
		return AnimationUtil.imagesFromCPPN(cppn, picSize, picSize, startFrame, endFrame, getInputMultipliers(), soundArray);
	}
	
	@Override
	public String[] sensorLabels() {
		return new String[] { "X-coordinate", "Y-coordinate", "distance from center", "time", "Sound Amplitude", "bias" };
	}
	
	@Override
	public int numCPPNInputs() {
		return sensorLabels().length;
	}

	@Override
	public int numCPPNOutputs() {
		return outputLabels().length;
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
