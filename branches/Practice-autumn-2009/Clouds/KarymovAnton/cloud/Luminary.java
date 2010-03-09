package cloud;
public class Luminary implements ILuminary {
    LuminaryType luminaryNow = LuminaryType.values()[MyRandom.getRandom().nextInt(LuminaryType.values().length)];

    public LuminaryType isShining() {
      return luminaryNow ;
    }

}
