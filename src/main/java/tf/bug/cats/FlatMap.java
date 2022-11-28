package tf.bug.cats;

import java.util.function.BiFunction;
import java.util.function.Function;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.data.Either;
import tf.bug.data.Option;
import tf.bug.data.Tuple2;
import tf.bug.〇;
import tf.bug.入;

public interface FlatMap<F extends 入<〇, 〇>, Mu extends FlatMap.Mu> extends IsKind<入<〇, 〇>, F, Mu>, Apply<F, Mu> {

    public static <F extends 入<〇, 〇>, Mu extends FlatMap.Mu> FlatMap<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (FlatMap<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 〇>>, Apply.Mu {}

    public <A, B> App<F, B> flatMap(final App<F, A> fa, final Function<A, App<F, B>> f);

    public default <A> App<F, A> flatten(final App<F, App<F, A>> ffa) {
        return this.flatMap(ffa, Function.identity());
    }

    @Override
    public default <A, B> App<F, B> ap(final App<F, A> fa, final App<F, Function<A, B>> fab) {
        return this.flatMap(fab, f -> this.map(fa, f));
    }

    @Override
    public default <A, B> App<F, Tuple2<A, B>> product(final App<F, A> fa, final App<F, B> fb) {
        return this.flatMap(fa, a -> this.map(fb, b -> new Tuple2<>(a, b)));
    }

    @Override
    public default <A, B, Z> App<F, Z> ap2(final App<F, A> fa, final App<F, B> fb, final App<F, BiFunction<A, B, Z>> ff) {
        return this.flatMap(fa, a -> this.flatMap(fb, b -> this.map(ff, f -> f.apply(a, b))));
    }

    @Override
    public default <A, B, Z> App<F, Z> map2(final App<F, A> fa, final App<F, B> fb, BiFunction<A, B, Z> f) {
        return this.flatMap(fa, a -> this.map(fb, b -> f.apply(a, b)));
    }

    public default <A, B> App<F, Tuple2<A, B>> mproduct(final App<F, A> fa, final Function<A, App<F, B>> f) {
        return this.flatMap(fa, a -> this.map(f.apply(a), b -> new Tuple2<>(a, b)));
    }

    public default <A> App<F, A> ifM(final App<F, Boolean> cond, final App<F, A> ifT, final App<F, A> ifF) {
        return this.flatMap(cond, x -> (x ? ifT : ifF));
    }

    public <A, B> App<F, B> tailRecM(A a, Function<A, App<F, Either<A, B>>> f);

    public default <A, B> App<F, A> flatTap(final App<F, A> fa, final Function<A, App<F, B>> f) {
        return this.flatMap(fa, a -> this.as(f.apply(a), a));
    }

    public default <A, B> App<F, B> foreverM(final App<F, A> fa) {
        Either<Void, B> leftUnit = new Either.Left<>(null);
        App<F, Either<Void, B>> stepResult = this.as(fa, leftUnit);
        return this.tailRecM((Void) null, ignored -> stepResult);
    }

    public default <A, B> App<F, B> iterateForeverM(A a, Function<A, App<F, A>> f) {
        return this.tailRecM(a, f.andThen(fa -> this.map(fa, Either.Left::new)));
    }

    public default <A> App<F, A> untilDefinedM(App<F, Option<A>> foa) {
        Either<Void, A> leftUnit = new Either.Left<>(null);
        App<F, Either<Void, A>> fEither = this.map(foa, o -> o.fold(() -> leftUnit, Either.Right::new));
        return this.tailRecM((Void) null, ignored -> fEither);
    }
}
