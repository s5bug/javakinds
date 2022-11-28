package tf.bug.cats;

import java.util.function.BiFunction;
import java.util.function.Function;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.data.Tuple2;
import tf.bug.〇;
import tf.bug.入;

public interface Apply<F extends 入<〇, 〇>, Mu extends Apply.Mu> extends IsKind<入<〇, 〇>, F, Mu>, Functor<F, Mu>, InvariantSemigroupal<F, Mu> {

    public static <F extends 入<〇, 〇>, Mu extends Apply.Mu> Apply<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (Apply<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 〇>>, Functor.Mu, InvariantSemigroupal.Mu {}

    public <A, B> App<F, B> ap(final App<F, A> fa, final App<F, Function<A, B>> fab);

    @Override
    public default <A, B> App<F, Tuple2<A, B>> product(final App<F, A> fa, final App<F, B> fb) {
        return this.ap(fb, this.map(fa, (A a) -> (B b) -> new Tuple2<>(a, b)));
    }

    public default <A, B, Z> App<F, Z> ap2(final App<F, A> fa, final App<F, B> fb, final App<F, BiFunction<A, B, Z>> ff) {
        return this.map(this.product(ff, this.product(fa, fb)), fab -> fab._0().apply(fab._1()._0(), fab._1()._1()));
    }

    public default <A, B, Z> App<F, Z> map2(final App<F, A> fa, final App<F, B> fb, final BiFunction<A, B, Z> f) {
        return this.map(this.product(fa, fb), ab -> f.apply(ab._0(), ab._1()));
    }
}
