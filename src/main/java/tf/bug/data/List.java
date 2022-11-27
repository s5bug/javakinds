package tf.bug.data;

import java.util.function.Function;
import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;
import tf.bug.cats.Monad;

public sealed interface List<A> extends App〇〇入<List.Mu, A> permits List.Nil, List.Cons {

    public static <A> List<A> unbox(App〇〇入<List.Mu, A> proofBox) {
        return (List<A>) proofBox;
    }

    public static final class Mu implements K〇〇入 {}

    public <B> List<B> map(Function<A, B> f);

    public static final record Nil<A>() implements List<A> {
        @Override
        public <B> Nil<B> map(Function<A, B> f) {
            return new Nil<>();
        }
    }

    public static final record Cons<A>(A head, List<A> tail) implements List<A> {
        @Override
        public <B> Cons<B> map(Function<A, B> f) {
            return new Cons<>(f.apply(head), tail.map(f));
        }
    }

    public static enum Instance implements Kind〇〇入<Mu, Instance.Mu>, Monad<Mu, Instance.Mu> {
        INSTANCE;

        public static final class Mu implements Kind〇〇入.Mu, Monad.Mu {}

        @Override
        public <A> App〇〇入<List.Mu, A> pure(A a) {
            return new List.Cons<>(a, new List.Nil<>());
        }

        @Override
        public <A, B> App〇〇入<List.Mu, B> map(App〇〇入<List.Mu, A> fa, Function<A, B> f) {
            return List.unbox(fa).map(f);
        }

        @Override
        public <A, B> App〇〇入<List.Mu, B> flatMap(App〇〇入<List.Mu, A> fa, Function<A, App〇〇入<List.Mu, B>> f) {
            return null;
        }

        @Override
        public <A, B> App〇〇入<List.Mu, B> tailRecM(A a, Function<A, App〇〇入<List.Mu, Either<A, B>>> f) {
            return null;
        }
    }

}
