package tf.bug.cats;

import java.util.function.Function;
import tf.bug.App〇〇入;
import tf.bug.Kind〇〇入;
import tf.bug.K〇〇入;

public interface Invariant<F extends K〇〇入, Mu extends Invariant.Mu> extends Kind〇〇入<F, Mu> {

    public static <F extends K〇〇入, Mu extends Invariant.Mu> Invariant<F, Mu> unbox(final App〇〇入<Mu, F> proofBox) {
        return (Invariant<F, Mu>) proofBox;
    }

    public static interface Mu extends Kind〇〇入.Mu {}

    public <A, B> App〇〇入<F, B> imap(final App〇〇入<F, A> fa, final Function<A, B> f, final Function<B, A> g);

}
