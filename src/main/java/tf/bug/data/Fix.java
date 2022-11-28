package tf.bug.data;

import java.util.function.Function;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.cats.Functor;
import tf.bug.〇;
import tf.bug.入;

public final record Fix<F extends 入<〇, 〇>>(App<F, Fix<F>> unfix) implements App<Fix.Mu, F> {

    public static <F extends 入<〇, 〇>> Fix<F> unbox(final App<Fix.Mu, F> proofBox) {
        return (Fix<F>) proofBox;
    }

    public static final record Mu() implements 入<入<〇, 〇>, 〇> {}

    public static <F extends 入<〇, 〇>, A> A cata(final Functor<F, ?> functor, final Fix<F> fix, final Function<App<F, A>, A> algebra) {
        return hylo(functor, fix, Fix::unfix, algebra);
    }

    public static <F extends 入<〇, 〇>, A> Fix<F> ana(final Functor<F, ?> functor, final A initial, final Function<A, App<F, A>> coalgebra) {
        return hylo(functor, initial, coalgebra, Fix::new);
    }

    public static <F extends 入<〇, 〇>, A, B> B hylo(final Functor<F, ?> functor, final A initial, final Function<A, App<F, A>> coalgebra, final Function<App<F, B>, B> algebra) {
        final Function<A, B> inner = new Function<>() {
            @Override
            public B apply(final A a) {
                return algebra.apply(functor.map(coalgebra.apply(a), this));
            }
        };
        return inner.apply(initial);
    }

    public static enum Instance implements IsKind<入<入<〇, 〇>, 〇>, Mu, Instance.Mu> {
        INSTANCE;

        public static final record Mu() implements IsKind.Mu<入<入<〇, 〇>, 〇>> {}
    }

}
