package tf.bug.data;

import java.util.function.Function;
import tf.bug.App〇〇〇入入;
import tf.bug.Kind〇〇〇入入;
import tf.bug.K〇〇〇入入;
import tf.bug.cats.Bifunctor;

public sealed interface Either<L, R> extends App〇〇〇入入<Either.Mu, L, R> permits Either.Left, Either.Right {

    public static <A, B> Either<A, B> unbox(App〇〇〇入入<Either.Mu, A, B> proofBox) {
        return (Either<A, B>) proofBox;
    }

    public static final class Mu implements K〇〇〇入入 {}

    public <A> A fold(Function<L, A> left, Function<R, A> right);

    public static final record Left<L, R>(L get) implements Either<L, R> {
        @Override
        public <A> A fold(Function<L, A> left, Function<R, A> right) {
            return left.apply(this.get);
        }
    }
    public static final record Right<L, R>(R get) implements Either<L, R> {
        @Override
        public <A> A fold(Function<L, A> left, Function<R, A> right) {
            return right.apply(this.get);
        }
    }

    public static enum Instance implements Kind〇〇〇入入<Mu, Instance.Mu>, Bifunctor<Mu, Instance.Mu> {
        INSTANCE;

        public static final class Mu implements Kind〇〇〇入入.Mu, Bifunctor.Mu {}

        @Override
        public <A, B, C, D> App〇〇〇入入<Either.Mu, C, D> bimap(App〇〇〇入入<Either.Mu, A, B> fab, Function<A, C> f, Function<B, D> g) {
            return Either.unbox(fab).fold(f.andThen(Either.Left::new), g.andThen(Either.Right::new));
        }
    }

}
