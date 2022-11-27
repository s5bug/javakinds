package tf.bug.cats;

import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;

public interface InvariantSemigroupal<F extends K〇〇入, Mu extends InvariantSemigroupal.Mu> extends Kind〇〇入<F, Mu>, Invariant<F, Mu>, Semigroupal<F, Mu> {

    public static <F extends K〇〇入, Mu extends InvariantSemigroupal.Mu> InvariantSemigroupal<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (InvariantSemigroupal<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇入.Mu, Invariant.Mu, Semigroupal.Mu {}

}
