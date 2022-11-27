package tf.bug.cats;

import java.util.AbstractMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;
import tf.bug.data.Either;
import tf.bug.data.Option;
import tf.bug.data.Tuple2;

public interface FlatMap<F extends K〇〇入, Mu extends FlatMap.Mu> extends Kind〇〇入<F, Mu>, Apply<F, Mu> {

    public static <F extends K〇〇入, Mu extends FlatMap.Mu> FlatMap<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (FlatMap<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇入.Mu, Apply.Mu {}

    public <A, B> App〇〇入<F, B> flatMap(App〇〇入<F, A> fa, Function<A, App〇〇入<F, B>> f);

    public default <A> App〇〇入<F, A> flatten(App〇〇入<F, App〇〇入<F, A>> ffa) {
        return this.flatMap(ffa, Function.identity());
    }

    @Override
    public default <A, B> App〇〇入<F, B> ap(App〇〇入<F, A> fa, App〇〇入<F, Function<A, B>> fab) {
        return this.flatMap(fab, f -> this.map(fa, f));
    }

    @Override
    public default <A, B> App〇〇入<F, Tuple2<A, B>> product(App〇〇入<F, A> fa, App〇〇入<F, B> fb) {
        return this.flatMap(fa, a -> this.map(fb, b -> new Tuple2<>(a, b)));
    }

    @Override
    public default <A, B, Z> App〇〇入<F, Z> ap2(App〇〇入<F, A> fa, App〇〇入<F, B> fb, App〇〇入<F, BiFunction<A, B, Z>> ff) {
        return this.flatMap(fa, a -> this.flatMap(fb, b -> this.map(ff, f -> f.apply(a, b))));
    }

    @Override
    public default <A, B, Z> App〇〇入<F, Z> map2(App〇〇入<F, A> fa, App〇〇入<F, B> fb, BiFunction<A, B, Z> f) {
        return this.flatMap(fa, a -> this.map(fb, b -> f.apply(a, b)));
    }

    public default <A, B> App〇〇入<F, Tuple2<A, B>> mproduct(App〇〇入<F, A> fa, Function<A, App〇〇入<F, B>> f) {
        return this.flatMap(fa, a -> this.map(f.apply(a), b -> new Tuple2<>(a, b)));
    }

    public default <A> App〇〇入<F, A> ifM(App〇〇入<F, Boolean> cond, App〇〇入<F, A> ifT, App〇〇入<F, A> ifF) {
        return this.flatMap(cond, x -> (x ? ifT : ifF));
    }

    public <A, B> App〇〇入<F, B> tailRecM(A a, Function<A, App〇〇入<F, Either<A, B>>> f);

    public default <A, B> App〇〇入<F, A> flatTap(App〇〇入<F, A> fa, Function<A, App〇〇入<F, B>> f) {
        return this.flatMap(fa, a -> this.as(f.apply(a), a));
    }

    public default <A, B> App〇〇入<F, B> foreverM(App〇〇入<F, A> fa) {
        Either<Void, B> leftUnit = new Either.Left<>(null);
        App〇〇入<F, Either<Void, B>> stepResult = this.as(fa, leftUnit);
        return this.tailRecM((Void) null, ignored -> stepResult);
    }

    public default <A, B> App〇〇入<F, B> iterateForeverM(A a, Function<A, App〇〇入<F, A>> f) {
        return this.tailRecM(a, f.andThen(fa -> this.map(fa, Either.Left::new)));
    }

    public default <A> App〇〇入<F, A> untilDefinedM(App〇〇入<F, Option<A>> foa) {
        Either<Void, A> leftUnit = new Either.Left<>(null);
        App〇〇入<F, Either<Void, A>> fEither = this.map(foa, o -> o.fold(() -> leftUnit, Either.Right::new));
        return this.tailRecM((Void) null, ignored -> fEither);
    }
}
