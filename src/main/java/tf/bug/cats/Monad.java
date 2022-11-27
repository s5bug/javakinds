package tf.bug.cats;

import java.util.function.Function;
import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;

public interface Monad<F extends K〇〇入, Mu extends Monad.Mu> extends Kind〇〇入<F, Mu>, FlatMap<F, Mu>, Applicative<F, Mu> {

    public static <F extends K〇〇入, Mu extends Monad.Mu> Monad<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (Monad<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇入.Mu, FlatMap.Mu, Applicative.Mu {}

    @Override
    default <A, B> App〇〇入<F, B> map(final App〇〇入<F, A> fa, final Function<A, B> f) {
        return this.flatMap(fa, a -> this.pure(f.apply(a)));
    }

}
