package cz.cvut.fit.niadp.mvcgame.visitor.audio;

public interface IAudioVisitable {
    void acceptVisitor(IAudioVisitor visitor);
}
