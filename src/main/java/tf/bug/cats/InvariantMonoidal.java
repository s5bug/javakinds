package tf.bug.cats;

import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;

public interface InvariantMonoidal<F extends K〇〇入, Mu extends InvariantMonoidal.Mu> extends Kind〇〇入<F, Mu>, InvariantSemigroupal<F, Mu> {

    public static <F extends K〇〇入, Mu extends InvariantMonoidal.Mu> InvariantMonoidal<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (InvariantMonoidal<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇入.Mu, InvariantSemigroupal.Mu {}

    public App〇〇入<F, Void> unit();

}
