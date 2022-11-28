package tf.bug.cats;

import java.util.function.Function;
import tf.bug.*;

public interface Bifunctor<F extends 入<〇, 入<〇, 〇>>, Mu extends Bifunctor.Mu> extends IsKind<入<〇, 入<〇, 〇>>, F, Mu> {

    public static <F extends 入<〇, 入<〇, 〇>>, Mu extends Bifunctor.Mu> Bifunctor<F, Mu> unbox(final App<Mu, F> proofBox) {
        return (Bifunctor<F, Mu>) proofBox;
    }

    public static interface Mu extends IsKind.Mu<入<〇, 入<〇, 〇>>> {}

    public <A, B, C, D> App<App<F, C>, D> bimap(final App<App<F, A>, B> fab, final Function<A, C> f, final Function<B, D> g);

    public default <A, B, C> App<App<F, C>, B> leftMap(final App<App<F, A>, B> fab, final Function<A, C> f) {
        return this.bimap(fab, f, Function.identity());
    }

    public default <A, B, D> App<App<F, A>, D> rightMap(final App<App<F, A>, B> fab, final Function<B, D> g) {
        return this.bimap(fab, Function.identity(), g);
    }

}
