package agent;


import exceptions.WrongGenotypeException;
import fitness_evaluator.IFitnessProxy;
import fitness_evaluator.SimpleFunctionFitnessProxy;
import function.IFunction;
import genotype.Genotype;


public class Agent{
	private double energy; 
	private double fitness;
	private Genotype genotype;
	
	private static double energyLossFactor=50;
	private static double genotypeRandomnessFactor=0.2; //factor which defines the probability of mutation
	private static double energyOnStart=100.0;
	
	public static void setEnergyOnStart(double energyOnStart) {
		Agent.energyOnStart = energyOnStart;
	}


	private IFitnessProxy fitnessEvaluator;
	
	public Agent(IFitnessProxy fitnessEvaluator) throws WrongGenotypeException {
		super();
		this.energy = energyOnStart;
		 
		genotype = fitnessEvaluator.createGenotype();
		 
		this.fitnessEvaluator = fitnessEvaluator;
		fitness=fitnessEvaluator.evaluateFitness(genotype);
	}

	public Agent(double energy, IFitnessProxy fitnessEvaluator) throws WrongGenotypeException {
		super();
		this.energy = energy;
		 
		genotype = fitnessEvaluator.createGenotype();
		 
		this.fitnessEvaluator = fitnessEvaluator;
		fitness=fitnessEvaluator.evaluateFitness(genotype);
	}

	public Agent(double energy, Genotype genotype, IFitnessProxy fitnessEvaluator) throws WrongGenotypeException {
		super();
		this.energy = energy;
		this.genotype = genotype;
		this.fitnessEvaluator = fitnessEvaluator;
		fitness=fitnessEvaluator.evaluateFitness(genotype);
	}

	
	
	public static void setEnergyLossFactor(double energyLossFactor) {
		Agent.energyLossFactor = energyLossFactor;
	}



	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}



	public Genotype getGenotype() {
		return genotype;
	}

	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}

	public double getEnergy() {
		return energy;
	}

	public double getFitness() {
		return fitness;
	}

	public void fight(Agent agent2) {
		if(agent2.getFitness()<fitness){
			
			agent2.energy=agent2.energy-energyLossFactor;
			energy=energy+energyLossFactor;
		}
		else{
		
			agent2.energy=agent2.energy+energyLossFactor;
			energy=energy-energyLossFactor;
		}
		
	}

	
	public Agent hybridize(Agent agent2) throws WrongGenotypeException {
		Genotype gen= genotype.hybridize(agent2.getGenotype(), genotypeRandomnessFactor);
		Agent agent3 = new Agent(energyOnStart, gen, fitnessEvaluator);
		
		energy=energy-energyOnStart/3;
		agent2.energy=agent2.energy-energyOnStart/3;
		return agent3;
	}

}
