package tf.bug.cats;

import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.data.Tuple2;
import tf.bug.〇;
import tf.bug.入;

public interface Semigroupal<F extends 入<〇, 〇>, Mu extends Semigroupal.Mu> extends IsKind<入<〇, 〇>, F, Mu> {

    public static <F extends 入<〇, 〇>, Mu extends Semigroupal.Mu> Semigroupal<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (Semigroupal<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 〇>> {}

    public <A, B> App<F, Tuple2<A, B>> product(final App<F, A> fa, final App<F, B> fb);

}
