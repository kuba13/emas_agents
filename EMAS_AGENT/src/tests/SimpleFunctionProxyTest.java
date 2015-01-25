package tests;

import static org.junit.Assert.*;

import org.easymock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.WrongGenotypeException;
import fitness_evaluator.SimpleFunctionFitnessProxy;
import function.SimpleFunction;
import genotype.FunctionGenotype;
import genotype.Genotype;

public class SimpleFunctionProxyTest {

	SimpleFunction f;
	SimpleFunctionFitnessProxy proxy;

	@Before
	public void setUp() throws Exception {
		f = new SimpleFunction();
		proxy = new SimpleFunctionFitnessProxy(f);
	}

	@Test(expected = WrongGenotypeException.class)
	public void evaluateFitnessThrowingExceptionTest()
			throws WrongGenotypeException {
		Genotype gen = null;
		proxy.evaluateFitness(gen);

	}

	@Test(expected = WrongGenotypeException.class)
	public void evaluateFitnessThrowingExceptionSecondTest()
			throws WrongGenotypeException {

		Genotype gen = EasyMock.createMock(Genotype.class);
		EasyMock.replay(gen);
		proxy.evaluateFitness(gen);

	}

	@Test
	public void evaluateFitnesTest() throws WrongGenotypeException {
		FunctionGenotype gen = new FunctionGenotype(f);
		double[][] P = new double[2][1];
		P[0][0] = 0;
		P[1][0] = 1;
		gen.setP(P);
		double fitness = proxy.evaluateFitness(gen);
		assertEquals(0.0, 0.0, 0.0);
	}

}
