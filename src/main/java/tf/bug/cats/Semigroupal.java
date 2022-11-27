package tf.bug.cats;

import java.util.AbstractMap;
import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;
import tf.bug.data.Tuple2;

public interface Semigroupal<F extends K〇〇入, Mu extends Semigroupal.Mu> extends Kind〇〇入<F, Mu> {

    public static <F extends K〇〇入, Mu extends Semigroupal.Mu> Semigroupal<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (Semigroupal<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇入.Mu {}

    public <A, B> App〇〇入<F, Tuple2<A, B>> product(App〇〇入<F, A> fa, App〇〇入<F, B> fb);

}
