package tf.bug.cats;

import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.〇;
import tf.bug.入;

public interface InvariantSemigroupal<F extends 入<〇, 〇>, Mu extends InvariantSemigroupal.Mu> extends IsKind<入<〇, 〇>, F, Mu>, Invariant<F, Mu>, Semigroupal<F, Mu> {

    public static <F extends 入<〇, 〇>, Mu extends InvariantSemigroupal.Mu> InvariantSemigroupal<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (InvariantSemigroupal<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 〇>>, Invariant.Mu, Semigroupal.Mu {}

}
