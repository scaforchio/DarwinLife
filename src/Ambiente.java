public class Ambiente {
    private int numRows;
    private int numCols;
    private int tolleranzaAmbientaleMassima;
    private double sopportazioneAmbientale;
    private int scostamentoMassimoMutazioniAmbientali;
    private long frequenzaModificaAmbientale;
    private int etaMedia;
    private int cicliIniziali;
    private int tolleranzaAmbientalePerGenerazioneSpontanea;
    private float[] probNascitaConNConfinanti =new float[9];
    private float[] probMorteConNConfinanti =new float[9];

    private int scostamentoMassimoMutazioniLivbe;
    private int ritardoLoop;

    public Ambiente() {
        setNumRows(100);
        setNumCols(100);
        setRitardoLoop(0);
        //setTolleranzaAmbientaleMassima(75); // 5*5*5
        setTolleranzaAmbientaleMassima(16581375);
        setSopportazioneAmbientale(0.1); // 1 su 10 sopravvive a condizioni avverse
        setScostamentoMassimoMutazioniAmbientali(5); //(5); // massimo spostamento dalle condizioni precedenti
        setFrequenzaModificaAmbientale(999999999);//(1000);   // 1 volta su mille avvengono spostamenti ambientali
        setEtaMedia(1000000);
        setCicliIniziali(10000);// Eta media
        setTolleranzaAmbientalePerGenerazioneSpontanea(2);
        float probNasc[]={0,0,0,1,0,0,0,0,0};
        setProbNascitaConNConfinanti(probNasc);
        float probMorte []={1,1,0,0,1,1,1,1,1};
        setProbMorteConNConfinanti(probMorte);
    }

    public float[] getProbMorteConNConfinanti() {
        return probMorteConNConfinanti;
    }

    public void setProbMorteConNConfinanti(float[] probMorteConNConfinanti) {
        this.probMorteConNConfinanti = probMorteConNConfinanti;
    }

    public int getTolleranzaAmbientalePerGenerazioneSpontanea() {
        return tolleranzaAmbientalePerGenerazioneSpontanea;
    }

    public void setTolleranzaAmbientalePerGenerazioneSpontanea(int tolleranzaAmbientalePerGenerazioneSpontanea) {
        this.tolleranzaAmbientalePerGenerazioneSpontanea = tolleranzaAmbientalePerGenerazioneSpontanea;
    }

    public int getRitardoLoop() {
        return ritardoLoop;
    }

    public void setRitardoLoop(int ritardoLoop) {
        this.ritardoLoop = ritardoLoop;
    }

    public int getCicliIniziali() {
        return cicliIniziali;
    }

    public void setCicliIniziali(int cicliIniziali) {
        this.cicliIniziali = cicliIniziali;
    }

    public int getTolleranzaAmbientaleMassima() {
        return tolleranzaAmbientaleMassima;
    }

    public void setTolleranzaAmbientaleMassima(int tolleranzaAmbientaleMassima) {
        this.tolleranzaAmbientaleMassima = tolleranzaAmbientaleMassima;
    }

    public double getSopportazioneAmbientale() {
        return sopportazioneAmbientale;
    }

    public void setSopportazioneAmbientale(double sopportazioneAmbientale) {
        this.sopportazioneAmbientale = sopportazioneAmbientale;
    }

    public int getScostamentoMassimoMutazioniAmbientali() {
        return scostamentoMassimoMutazioniAmbientali;
    }

    public void setScostamentoMassimoMutazioniAmbientali(int scostamentoMassimoMutazioniAmbientali) {
        this.scostamentoMassimoMutazioniAmbientali = scostamentoMassimoMutazioniAmbientali;
    }

    public long getFrequenzaModificaAmbientale() {
        return frequenzaModificaAmbientale;
    }

    public void setFrequenzaModificaAmbientale(long frequanzaModificaAmbientale) {
        this.frequenzaModificaAmbientale = frequanzaModificaAmbientale;
    }

    public int getEtaMedia() {
        return etaMedia;
    }

    public void setEtaMedia(int etaMedia) {
        this.etaMedia = etaMedia;
    }

    public float[] getProbNascitaConNConfinanti() {
        return probNascitaConNConfinanti;
    }

    public void setProbNascitaConNConfinanti(float[] probNascitaConNConfinanti) {
        this.probNascitaConNConfinanti = probNascitaConNConfinanti;
    }

    public int getScostamentoMassimoMutazioniLivbe() {
        return scostamentoMassimoMutazioniLivbe;
    }

    public void setScostamentoMassimoMutazioniLivbe(int scostamentoMassimoMutazioniLivbe) {
        this.scostamentoMassimoMutazioniLivbe = scostamentoMassimoMutazioniLivbe;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }
}
