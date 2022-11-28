package tf.bug.data;

import java.util.function.Function;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.cats.Monad;
import tf.bug.〇;
import tf.bug.入;

public sealed interface List<A> extends App<List.Mu, A> permits List.Nil, List.Cons {

    public static <A> List<A> unbox(App<List.Mu, A> proofBox) {
        return (List<A>) proofBox;
    }

    public static final record Mu() implements 入<〇, 〇> {}

    public <B> List<B> map(final Function<A, B> f);

    public static final record Nil<A>() implements List<A> {
        @Override
        public <B> Nil<B> map(final Function<A, B> f) {
            return new Nil<>();
        }
    }

    public static final record Cons<A>(A head, List<A> tail) implements List<A> {
        @Override
        public <B> Cons<B> map(final Function<A, B> f) {
            return new Cons<>(f.apply(head), tail.map(f));
        }
    }

    public static enum Instance implements IsKind<入<〇, 〇>, Mu, Instance.Mu>, Monad<Mu, Instance.Mu> {
        INSTANCE;

        public static final record Mu() implements IsKind.Mu<入<〇, 〇>>, Monad.Mu {}

        @Override
        public <A> App<List.Mu, A> pure(final A a) {
            return new List.Cons<>(a, new List.Nil<>());
        }

        @Override
        public <A, B> App<List.Mu, B> map(final App<List.Mu, A> fa, final Function<A, B> f) {
            return List.unbox(fa).map(f);
        }

        @Override
        public <A, B> App<List.Mu, B> flatMap(final App<List.Mu, A> fa, final Function<A, App<List.Mu, B>> f) {
            return null;
        }

        @Override
        public <A, B> App<List.Mu, B> tailRecM(final A a, final Function<A, App<List.Mu, Either<A, B>>> f) {
            return null;
        }
    }

}
