import java.util.Comparator;

/**
 * Aggiungi qui una descrizione della classe LivBe
 *
 * @author Pietro Tamburrano
 * @version (un numero di versione o una data)
 */
public class LivBe implements Comparable {
    // variabili d'istanza - sostituisci l'esempio che segue con il tuo
    private int blueDNA; // Resistenza ai cambiamenti ambientali
    private int greenDNA; // Resistenza alla degenerazione
    private int redDNA; // Facilità di riproduzione
    private int age;

    private Ambiente a;


    public LivBe(int bd, int gd, int rd, Ambiente a) {
        this.a = a;
        blueDNA = bd;
        greenDNA = gd;
        redDNA = rd;
        age = 0;
    }
    // Produce un un nuovo LivBe a partire da uno esistente
    public LivBe(LivBe l, Ambiente a) {
        setAge(0);
        int scostBlue=(int)(Math.random()*(a.getScostamentoMassimoMutazioniLivbe()*2+1)-a.getScostamentoMassimoMutazioniLivbe());
        int scostGreen=(int)(Math.random()*(a.getScostamentoMassimoMutazioniLivbe()*2+1)-a.getScostamentoMassimoMutazioniLivbe());
        int scostRed=(int)(Math.random()*(a.getScostamentoMassimoMutazioniLivbe()*2+1)-a.getScostamentoMassimoMutazioniLivbe());
        setBlueDNA(l.getBlueDNA()+scostBlue);
        setGreenDNA(l.getGreenDNA()+scostGreen);
        setRedDNA(l.getRedDNA()+scostRed);
    }
    public int getBlueDNA() {
        return blueDNA;
    }

    public int getGreenDNA() {
        return greenDNA;
    }

    public int getRedDNA() {
        return redDNA;
    }

    public int getAge() {
        return age;
    }

    public void setBlueDNA(int bd) {
        blueDNA = bd;
    }

    public void setGreenDNA(int gd) {
        greenDNA = gd;
    }

    public void setRedDNA(int rd) {
        redDNA = rd;
    }

    public void setAge(int eta) {
        age = eta;
    }

    public int morte(Cell c) {
        int tolleranzaAmbientaleMassima = a.getTolleranzaAmbientaleMassima();
        double sopportazioneAmbientale = a.getSopportazioneAmbientale();
        int etaMedia = a.getEtaMedia();


        // Morte per età
       // System.out.println("Sono qui");
        double casoMorte = Math.random();
        //System.out.println(casoMorte);
        if (casoMorte > (1.0 / age) * etaMedia)
            return 1;
        // Morte per incompatibilità ambientale
        // si misura la sitanza tra il DNA e le condizioni ambientali
        int distBlue = (blueDNA - c.getBlueEnv()) * (blueDNA - c.getBlueEnv());
        int distGreen = (greenDNA - c.getGreenEnv()) * (greenDNA - c.getGreenEnv());
        int distRed = (redDNA - c.getRedEnv()) * (redDNA - c.getRedEnv());
        int dist = distBlue + distGreen + distRed;
        if (dist > tolleranzaAmbientaleMassima)
            if (Math.random() > sopportazioneAmbientale)
                return 2;
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        LivBe a=(LivBe) o;

        if (getRedDNA() > a.getRedDNA())
            return 1;
        if (getRedDNA() < a.getRedDNA())
            return -1;
        return 0;
    }
}

