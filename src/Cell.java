
public class Cell {
    // variabili d'istanza - sostituisci l'esempio che segue con il tuo
    private int blueEnv;
    private int greenEnv;
    private int redEnv;
    private LivBe resident;

    public Cell() {
        blueEnv = 128;
        greenEnv = 128;
        redEnv = 128;
    }

    public int getBlueEnv() {
        return blueEnv;
    }

    public int getGreenEnv() {
        return greenEnv;
    }

    public int getRedEnv() {
        return redEnv;
    }

    public void setBlueEnv(int be) {
        blueEnv = be;
    }

    public void setGreenEnv(int ge) {
        greenEnv = ge;
    }

    public void setRedEnv(int re) {
        redEnv = re;
    }

    public void setResident(LivBe r) {
        resident = r;
    }

    public LivBe getResident() {
        return resident;
    }
}