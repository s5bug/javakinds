package tf.bug.data;

import java.util.function.Function;
import java.util.function.Supplier;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.cats.Monad;
import tf.bug.〇;
import tf.bug.入;

public sealed interface Option<A> extends App<Option.Mu, A> permits Option.None, Option.Some {

    public static <A> Option<A> unbox(App<Option.Mu, A> proofBox) {
        return (Option<A>) proofBox;
    }

    public static final record Mu() implements 入<〇, 〇> {}

    public <B> B fold(final Supplier<B> none, final Function<A, B> some);

    public <B> Option<B> map(final Function<A, B> f);

    public <B> Option<B> flatMap(final Function<A, Option<B>> f);

    public static final record None<A>() implements Option<A> {
        @Override
        public <B> B fold(final Supplier<B> none, final Function<A, B> some) {
            return none.get();
        }

        @Override
        public <B> None<B> map(final Function<A, B> f) {
            return new None<>();
        }

        @Override
        public <B> None<B> flatMap(final Function<A, Option<B>> f) {
            return new None<>();
        }
    }
    public static final record Some<A>(A get) implements Option<A> {
        @Override
        public <B> B fold(final Supplier<B> none, final Function<A, B> some) {
            return some.apply(get);
        }

        @Override
        public <B> Some<B> map(final Function<A, B> f) {
            return new Some<>(f.apply(get));
        }

        @Override
        public <B> Option<B> flatMap(final Function<A, Option<B>> f) {
            return f.apply(get);
        }
    }

    public static enum Instance implements IsKind<入<〇, 〇>, Mu, Instance.Mu>, Monad<Mu, Instance.Mu> {
        INSTANCE;

        public static final record Mu() implements IsKind.Mu<入<〇, 〇>>, Monad.Mu {}

        @Override
        public <A> App<Option.Mu, A> pure(final A value) {
            return new Option.Some<>(value);
        }

        @Override
        public <A> App<Option.Mu, A> flatten(final App<Option.Mu, App<Option.Mu, A>> ffa) {
            return Option.unbox(ffa).fold(None::new, Option::unbox);
        }

        @Override
        public <A, B> App<Option.Mu, B> map(final App<Option.Mu, A> fa, final Function<A, B> f) {
            return Option.unbox(fa).map(f);
        }

        @Override
        public <A, B> App<Option.Mu, B> flatMap(final App<Option.Mu, A> fa, final Function<A, App<Option.Mu, B>> f) {
            return Option.unbox(fa).flatMap(f.andThen(Option::unbox));
        }

        @Override
        public <A, B> App<Option.Mu, B> tailRecM(final A a, final Function<A, App<Option.Mu, Either<A, B>>> f) {
            Either<A, B> current = new Either.Left<>(a);
            while(current instanceof Either.Left<A, B> el) {
                Option<Either<A, B>> r = Option.unbox(f.apply(el.get()));
                switch(r) {
                    case None<Either<A, B>> n -> { return new None<>(); }
                    case Some<Either<A, B>> s -> current = s.get();
                }
            }
            return new Some<>(((Either.Right<A, B>) current).get());
        }
    }

}
