package hr.fer.projektr.ai;

import org.ejml.simple.SimpleMatrix;

/**
 * Interface to apply activation function to each element of the matrix
 */
@FunctionalInterface
public interface ActivationFunctionApplier {
    SimpleMatrix apply(SimpleMatrix matrix);
}
