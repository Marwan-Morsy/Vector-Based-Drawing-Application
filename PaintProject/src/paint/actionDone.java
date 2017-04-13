package paint;
import paint.shape;
public class actionDone {
    private shape x;
    private shape y;
    int index;

    public actionDone(shape x,shape y, int index) {
        this.x = x;
        this.y= y;
        this.index = index;

    }
    public int getIndex() {
        return index;
    }   public shape getNew() {
        return x;
    }
    public shape getOld() {
        return y;
    }

    public void setIndex(int s) {
         index=s;
    }   public void setNew(shape s) {
         x=s;
    }
    public void setOld(shape s) {
         y=s;
    }
}
