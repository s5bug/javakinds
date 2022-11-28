package tf.bug.cats;

import java.util.function.Function;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.〇;
import tf.bug.入;

public interface Applicative<F extends 入<〇, 〇>, Mu extends Applicative.Mu> extends IsKind<入<〇, 〇>, F, Mu>, Apply<F, Mu>, InvariantMonoidal<F, Mu> {

    public static <F extends 入<〇, 〇>, Mu extends Applicative.Mu> Applicative<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (Applicative<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 〇>>, Apply.Mu, InvariantMonoidal.Mu {}

    public <A> App<F, A> pure(final A a);

    @Override
    public default App<F, Void> unit() {
        return this.pure(null);
    }

    @Override
    public default <A, B> App<F, B> map(final App<F, A> fa, final Function<A, B> f) {
        return this.ap(fa, this.pure(f));
    }

}
