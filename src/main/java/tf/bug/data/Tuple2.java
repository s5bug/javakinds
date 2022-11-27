package tf.bug.data;

import java.util.function.Function;
import tf.bug.App〇〇〇入入;
import tf.bug.Kind〇〇〇入入;
import tf.bug.K〇〇〇入入;
import tf.bug.cats.Bifunctor;

public final record Tuple2<A, B>(A _0, B _1) implements App〇〇〇入入<Tuple2.Mu, A, B> {

    public static <A, B> Tuple2<A, B> unbox(App〇〇〇入入<Tuple2.Mu, A, B> proofBox) {
        return (Tuple2<A, B>) proofBox;
    }

    public static final class Mu implements K〇〇〇入入 {}

    public static enum Instance implements Kind〇〇〇入入<Mu, Instance.Mu>, Bifunctor<Mu, Instance.Mu> {
        INSTANCE;

        public static final class Mu implements Kind〇〇〇入入.Mu, Bifunctor.Mu {}

        @Override
        public <A, B, C, D> App〇〇〇入入<Tuple2.Mu, C, D> bimap(App〇〇〇入入<Tuple2.Mu, A, B> fab, Function<A, C> f, Function<B, D> g) {
            Tuple2<A, B> tab = Tuple2.unbox(fab);
            return new Tuple2<>(f.apply(tab._0), g.apply(tab._1));
        }
    }

}
