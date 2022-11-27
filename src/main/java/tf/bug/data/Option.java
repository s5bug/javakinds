package tf.bug.data;

import java.util.function.Function;
import java.util.function.Supplier;
import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;
import tf.bug.cats.Monad;

public sealed interface Option<A> extends App〇〇入<Option.Mu, A> permits Option.None, Option.Some {

    public static <A> Option<A> unbox(App〇〇入<Option.Mu, A> proofBox) {
        return (Option<A>) proofBox;
    }

    public static final class Mu implements K〇〇入 {}

    public <B> B fold(Supplier<B> none, Function<A, B> some);

    public <B> Option<B> map(Function<A, B> f);

    public <B> Option<B> flatMap(Function<A, Option<B>> f);

    public static final record None<A>() implements Option<A> {
        @Override
        public <B> B fold(Supplier<B> none, Function<A, B> some) {
            return none.get();
        }

        @Override
        public <B> None<B> map(Function<A, B> f) {
            return new None<>();
        }

        @Override
        public <B> None<B> flatMap(Function<A, Option<B>> f) {
            return new None<>();
        }
    }
    public static final record Some<A>(A get) implements Option<A> {
        @Override
        public <B> B fold(Supplier<B> none, Function<A, B> some) {
            return some.apply(get);
        }

        @Override
        public <B> Some<B> map(Function<A, B> f) {
            return new Some<>(f.apply(get));
        }

        @Override
        public <B> Option<B> flatMap(Function<A, Option<B>> f) {
            return f.apply(get);
        }
    }

    public static enum Instance implements Kind〇〇入<Mu, Instance.Mu>, Monad<Mu, Instance.Mu> {
        INSTANCE;

        public static final class Mu implements Kind〇〇入.Mu, Monad.Mu {}

        @Override
        public <A> App〇〇入<Option.Mu, A> pure(A value) {
            return new Option.Some<>(value);
        }

        @Override
        public <A> App〇〇入<Option.Mu, A> flatten(App〇〇入<Option.Mu, App〇〇入<Option.Mu, A>> ffa) {
            return Option.unbox(ffa).fold(None::new, Option::unbox);
        }

        @Override
        public <A, B> App〇〇入<Option.Mu, B> map(App〇〇入<Option.Mu, A> fa, Function<A, B> f) {
            return Option.unbox(fa).map(f);
        }

        @Override
        public <A, B> App〇〇入<Option.Mu, B> flatMap(App〇〇入<Option.Mu, A> fa, Function<A, App〇〇入<Option.Mu, B>> f) {
            return Option.unbox(fa).flatMap(f.andThen(Option::unbox));
        }

        @Override
        public <A, B> App〇〇入<Option.Mu, B> tailRecM(A a, Function<A, App〇〇入<Option.Mu, Either<A, B>>> f) {
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
