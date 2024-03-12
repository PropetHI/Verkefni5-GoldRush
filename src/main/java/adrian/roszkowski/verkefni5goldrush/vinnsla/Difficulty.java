package adrian.roszkowski.verkefni5goldrush.vinnsla;

public enum Difficulty {
    EASY(90),
    MEDIUM(60),
    HARD(30);

    private int time;

    Difficulty(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
