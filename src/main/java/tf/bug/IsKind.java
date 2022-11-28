package tf.bug;

public interface IsKind<X, F extends X, Mu extends IsKind.Mu<X>> extends App<Mu, F> {

    public static <X, F extends X, Proof extends IsKind.Mu<X>> IsKind<X, F, Proof> unbox(final App<Proof, F> proofBox) {
        return (IsKind<X, F, Proof>) proofBox;
    }

    public static interface Mu<X> extends 入<X, 〇> {}

}
