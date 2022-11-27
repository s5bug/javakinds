package tf.bug.cats;

import java.util.function.Function;
import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;

public interface Applicative<F extends K〇〇入, Mu extends Applicative.Mu> extends Kind〇〇入<F, Mu>, Apply<F, Mu>, InvariantMonoidal<F, Mu> {

    public static <F extends K〇〇入, Mu extends Applicative.Mu> Applicative<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (Applicative<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇入.Mu, Apply.Mu, InvariantMonoidal.Mu {}

    public <A> App〇〇入<F, A> pure(A a);

    @Override
    public default App〇〇入<F, Void> unit() {
        return this.pure(null);
    }

    @Override
    public default <A, B> App〇〇入<F, B> map(final App〇〇入<F, A> fa, final Function<A, B> f) {
        return this.ap(fa, this.pure(f));
    }

}
