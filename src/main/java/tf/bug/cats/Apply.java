package tf.bug.cats;

import java.util.AbstractMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;
import tf.bug.data.Tuple2;

public interface Apply<F extends K〇〇入, Mu extends Apply.Mu> extends Kind〇〇入<F, Mu>, Functor<F, Mu>, InvariantSemigroupal<F, Mu> {

    public static <F extends K〇〇入, Mu extends Apply.Mu> Apply<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (Apply<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇入.Mu, Functor.Mu, InvariantSemigroupal.Mu {}

    public <A, B> App〇〇入<F, B> ap(App〇〇入<F, A> fa, App〇〇入<F, Function<A, B>> fab);

    @Override
    public default <A, B> App〇〇入<F, Tuple2<A, B>> product(App〇〇入<F, A> fa, App〇〇入<F, B> fb) {
        return this.ap(fb, this.map(fa, (A a) -> (B b) -> new Tuple2<>(a, b)));
    }

    public default <A, B, Z> App〇〇入<F, Z> ap2(App〇〇入<F, A> fa, App〇〇入<F, B> fb, App〇〇入<F, BiFunction<A, B, Z>> ff) {
        return this.map(this.product(ff, this.product(fa, fb)), fab -> fab._0().apply(fab._1()._0(), fab._1()._1()));
    }

    public default <A, B, Z> App〇〇入<F, Z> map2(App〇〇入<F, A> fa, App〇〇入<F, B> fb, BiFunction<A, B, Z> f) {
        return this.map(this.product(fa, fb), ab -> f.apply(ab._0(), ab._1()));
    }
}
