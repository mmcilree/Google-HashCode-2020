package common;

/**
 * Since most of the hashcode problems seem to be optimisation problems, the actual
 * interesting part will take a table of inputs and produce a table of outputs, optimising in some way.
 */
public interface Optimiser {
    TabularData optimise(TabularData inputData);
}
