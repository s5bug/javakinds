package tf.bug.cats;

import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.〇;
import tf.bug.入;

public interface InvariantMonoidal<F extends 入<〇, 〇>, Mu extends InvariantMonoidal.Mu> extends IsKind<入<〇, 〇>, F, Mu>, InvariantSemigroupal<F, Mu> {

    public static <F extends 入<〇, 〇>, Mu extends InvariantMonoidal.Mu> InvariantMonoidal<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (InvariantMonoidal<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 〇>>, InvariantSemigroupal.Mu {}

    public App<F, Void> unit();

}
