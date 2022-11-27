package tf.bug.cats;

import java.util.function.Function;
import tf.bug.*;

public interface Bifunctor<F extends K〇〇〇入入, Mu extends Bifunctor.Mu> extends Kind〇〇〇入入<F, Mu> {

    public static <F extends K〇〇〇入入, Mu extends Bifunctor.Mu> Bifunctor<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (Bifunctor<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇〇入入.Mu {}

    public <A, B, C, D> App〇〇〇入入<F, C, D> bimap(App〇〇〇入入<F, A, B> fab, Function<A, C> f, Function<B, D> g);

    public default <A, B, C> App〇〇〇入入<F, C, B> leftMap(App〇〇〇入入<F, A, B> fab, Function<A, C> f) {
        return this.bimap(fab, f, Function.identity());
    }

    public default <A, B, D> App〇〇〇入入<F, A, D> rightMap(App〇〇〇入入<F, A, B> fab, Function<B, D> g) {
        return this.bimap(fab, Function.identity(), g);
    }

}
