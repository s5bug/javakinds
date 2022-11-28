package tf.bug.data;

import java.util.function.Function;
import tf.bug.App;
import tf.bug.IsKind;
import tf.bug.cats.Bifunctor;
import tf.bug.〇;
import tf.bug.入;

public final record Tuple2<A, B>(A _0, B _1) implements App<App<Tuple2.Mu, A>, B> {

    public static <A, B> Tuple2<A, B> unbox(final App<App<Tuple2.Mu, A>, B> proofBox) {
        return (Tuple2<A, B>) proofBox;
    }

    public static final record Mu() implements 入<〇, 入<〇, 〇>> {}

    public static enum Instance implements IsKind<入<〇, 入<〇, 〇>>, Mu, Instance.Mu>, Bifunctor<Mu, Instance.Mu> {
        INSTANCE;

        public static final record Mu() implements IsKind.Mu<入<〇, 入<〇, 〇>>>, Bifunctor.Mu {}

        @Override
        public <A, B, C, D> App<App<Tuple2.Mu, C>, D> bimap(final App<App<Tuple2.Mu, A>, B> fab, final Function<A, C> f, final Function<B, D> g) {
            Tuple2<A, B> tab = Tuple2.unbox(fab);
            return new Tuple2<>(f.apply(tab._0), g.apply(tab._1));
        }
    }

}
