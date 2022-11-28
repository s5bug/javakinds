package tf.bug.data;

import java.util.function.Function;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.cats.Bifunctor;
import tf.bug.〇;
import tf.bug.入;

public sealed interface Either<L, R> extends App<App<Either.Mu, L>, R> permits Either.Left, Either.Right {

    public static <A, B> Either<A, B> unbox(final App<App<Either.Mu, A>, B> proofBox) {
        return (Either<A, B>) proofBox;
    }

    public static final record Mu() implements 入<〇, 入<〇, 〇>> {}

    public <A> A fold(final Function<L, A> left, final Function<R, A> right);

    public static final record Left<L, R>(L get) implements Either<L, R> {
        @Override
        public <A> A fold(final Function<L, A> left, final Function<R, A> right) {
            return left.apply(this.get);
        }
    }
    public static final record Right<L, R>(R get) implements Either<L, R> {
        @Override
        public <A> A fold(final Function<L, A> left, final Function<R, A> right) {
            return right.apply(this.get);
        }
    }

    public static enum Instance implements IsKind<入<〇, 入<〇, 〇>>, Mu, Instance.Mu>, Bifunctor<Mu, Instance.Mu> {
        INSTANCE;

        public static final record Mu() implements IsKind.Mu<入<〇, 入<〇, 〇>>>, Bifunctor.Mu {}

        @Override
        public <A, B, C, D> App<App<Either.Mu, C>, D> bimap(final App<App<Either.Mu, A>, B> fab, final Function<A, C> f, final Function<B, D> g) {
            return Either.unbox(fab).fold(f.andThen(Either.Left::new), g.andThen(Either.Right::new));
        }
    }

}
