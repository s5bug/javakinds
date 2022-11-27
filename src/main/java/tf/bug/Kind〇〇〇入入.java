package tf.bug;

public interface Kind〇〇〇入入<F extends K〇〇〇入入, Mu extends Kind〇〇〇入入.Mu> extends App〇〇入<Mu, F> {

    public static <F extends K〇〇〇入入, Proof extends Kind〇〇〇入入.Mu> Kind〇〇〇入入<F, Proof> unbox(final App〇〇入<Proof, F> proofBox) {
        return (Kind〇〇〇入入<F, Proof>) proofBox;
    }

    public static interface Mu extends K〇〇入 {}

}
