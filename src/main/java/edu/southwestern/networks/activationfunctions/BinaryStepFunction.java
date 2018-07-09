package edu.southwestern.networks.activationfunctions;

import org.nd4j.linalg.activations.Activation;

import edu.southwestern.networks.ActivationFunctions;

public class BinaryStepFunction implements ActivationFunction {

	@Override
	public double f(double x) {
		return x < 0? 0: 1;
	}

	@Override
	public Activation equivalentDL4JFunction() {
		throw new UnsupportedOperationException("No corresponding DL4J function for " + name());
	}

	@Override
	public String name() {
		return "binaryStep";
	}

}