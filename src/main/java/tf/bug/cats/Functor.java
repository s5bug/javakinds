package tf.bug.cats;

import java.util.AbstractMap;
import java.util.function.Function;
import java.util.function.Supplier;
import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;
import tf.bug.data.Tuple2;

public interface Functor<F extends K〇〇入, Mu extends Functor.Mu> extends Kind〇〇入<F, Mu>, Invariant<F, Mu> {

    public static <F extends K〇〇入, Mu extends Functor.Mu> Functor<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (Functor<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇入.Mu, Invariant.Mu {}

    public <A, B> App〇〇入<F, B> map(final App〇〇入<F, A> fa, final Function<A, B> f);

    @Override
    public default <A, B> App〇〇入<F, B> imap(final App〇〇入<F, A> fa, final Function<A, B> f, final Function<B, A> g) {
        return this.map(fa, f);
    }

    @SuppressWarnings("unchecked")
    public default <A extends B, B> App〇〇入<F, B> widen(final App〇〇入<F, A> fa) {
        return (App〇〇入<F, B>) fa;
    }

    public default <A, B> Function<App〇〇入<F, A>, App〇〇入<F, B>> lift(final Function<A, B> f) {
        return fa -> this.map(fa, f);
    }

    public default <A> App〇〇入<F, Void> fvoid(final App〇〇入<F, A> fa) {
        return this.as(fa, null);
    }

    public default <A, B> App〇〇入<F, B> as(final App〇〇入<F, A> fa, B value) {
        return this.map(fa, ignored -> value);
    }

    public default <A, B> Tuple2<App〇〇入<F, A>, App〇〇入<F, B>> unzip(App〇〇入<F, Tuple2<A, B>> fab) {
        App〇〇入<F, A> left = this.map(fab, Tuple2::_0);
        App〇〇入<F, B> right = this.map(fab, Tuple2::_1);
        return new Tuple2<>(left, right);
    }

    public default <A> App〇〇入<F, A> ifF(final App〇〇入<F, Boolean> cond, Supplier<A> ifT, Supplier<A> ifF) {
        return this.map(cond, x -> (x ? ifT : ifF).get());
    }

}
