package tf.bug.cats;

import java.util.function.Function;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.〇;
import tf.bug.入;

public interface Monad<F extends 入<〇, 〇>, Mu extends Monad.Mu> extends IsKind<入<〇, 〇>, F, Mu>, FlatMap<F, Mu>, Applicative<F, Mu> {

    public static <F extends 入<〇, 〇>, Mu extends Monad.Mu> Monad<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (Monad<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 〇>>, FlatMap.Mu, Applicative.Mu {}

    @Override
    default <A, B> App<F, B> map(final App<F, A> fa, final Function<A, B> f) {
        return this.flatMap(fa, a -> this.pure(f.apply(a)));
    }

}
