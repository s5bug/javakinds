package tf.bug.cats;

import java.util.function.Function;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.〇;
import tf.bug.入;

public interface Invariant<F extends 入<〇, 〇>, Mu extends Invariant.Mu> extends IsKind<入<〇, 〇>, F, Mu> {

    public static <F extends 入<〇, 〇>, Mu extends Invariant.Mu> Invariant<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (Invariant<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 〇>> {}

    public <A, B> App<F, B> imap(final App<F, A> fa, final Function<A, B> f, final Function<B, A> g);

}
