package tf.bug.cats;

import java.util.function.Function;
import java.util.function.Supplier;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.data.Tuple2;
import tf.bug.〇;
import tf.bug.入;

public interface Functor<F extends 入<〇, 〇>, Mu extends Functor.Mu> extends IsKind<入<〇, 〇>, F, Mu>, Invariant<F, Mu> {

    public static <F extends 入<〇, 〇>, Mu extends Functor.Mu> Functor<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (Functor<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 〇>>, Invariant.Mu {}

    public <A, B> App<F, B> map(final App<F, A> fa, final Function<A, B> f);

    @Override
    public default <A, B> App<F, B> imap(final App<F, A> fa, final Function<A, B> f, final Function<B, A> g) {
        return this.map(fa, f);
    }

    @SuppressWarnings("unchecked")
    public default <A extends B, B> App<F, B> widen(final App<F, A> fa) {
        return (App<F, B>) fa;
    }

    public default <A, B> Function<App<F, A>, App<F, B>> lift(final Function<A, B> f) {
        return fa -> this.map(fa, f);
    }

    public default <A> App<F, Void> fvoid(final App<F, A> fa) {
        return this.as(fa, null);
    }

    public default <A, B> App<F, B> as(final App<F, A> fa, final B value) {
        return this.map(fa, ignored -> value);
    }

    public default <A, B> Tuple2<App<F, A>, App<F, B>> unzip(final App<F, Tuple2<A, B>> fab) {
        App<F, A> left = this.map(fab, Tuple2::_0);
        App<F, B> right = this.map(fab, Tuple2::_1);
        return new Tuple2<>(left, right);
    }

    public default <A> App<F, A> ifF(final App<F, Boolean> cond, final Supplier<A> ifT, final Supplier<A> ifF) {
        return this.map(cond, x -> (x ? ifT : ifF).get());
    }

    public static <F extends 入<〇, 〇>, FMu extends Functor.Mu, G extends 入<〇, 〇>, GMu extends Functor.Mu> Functor<AndThen.Mu<F, G>, AndThen.Instance.Mu<F, FMu, G, GMu>> andThen(final Functor<F, FMu> fFunctor, final Functor<G, GMu> gFunctor) {
        return new AndThen.Instance<>(fFunctor, gFunctor);
    }


    public static final record AndThen<F extends 入<〇, 〇>, G extends 入<〇, 〇>, A>(App<G, ? extends App<F, A>> gfa) implements App<AndThen.Mu<F, G>, A> {

        public static <F extends 入<〇, 〇>, G extends 入<〇, 〇>, A> AndThen<F, G, A> unbox(App<Mu<F, G>, A> proofBox) {
            return (AndThen<F, G, A>) proofBox;
        }

        public static final class Mu<F extends 入<〇, 〇>, G extends 入<〇, 〇>> implements 入<〇, 〇> {}

        public static final record Instance<F extends 入<〇, 〇>, FMu extends Functor.Mu, G extends 入<〇, 〇>, GMu extends Functor.Mu>(Functor<F, FMu> fFunctor, Functor<G, GMu> gFunctor) implements Functor<Mu<F, G>, Instance.Mu<F, FMu, G, GMu>> {

            public static final class Mu<F extends 入<〇, 〇>, FMu extends Functor.Mu, G extends 入<〇, 〇>, GMu extends Functor.Mu> implements IsKind.Mu<入<〇, 〇>>, Functor.Mu {}

            @Override
            public <A, B> App<AndThen.Mu<F, G>, B> map(App<AndThen.Mu<F, G>, A> fa, Function<A, B> f) {
                App<G, ? extends App<F, A>> unboxed = AndThen.unbox(fa).gfa;
                App<G, App<F, B>> mapped = gFunctor.map(unboxed, afa -> fFunctor.map(afa, f));
                return new AndThen<>(mapped);
            }
        }

    }

}

