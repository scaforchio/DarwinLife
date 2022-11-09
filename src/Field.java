import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Field {
    private Ambiente a;

    Field oldField;
    // Probabilità che avvenga modifica ambientale
    private Cell grid[][]; // = new Cell[a.getNumRows()][a.getNumCols()];
    ;
    private long generation;

    // costruttore di copia
    public Field(@NotNull Field f) {
        setA(f.getA());
        setGeneration(f.getGeneration());
        setGrid(f.getGrid());
    }

    public Field(@NotNull Ambiente a) {
        this.a = a;

        grid = new Cell[a.getNumRows()][a.getNumCols()];
        for (int i = 0; i < a.getNumRows(); i++)
            for (int j = 0; j < a.getNumCols(); j++)
                grid[i][j] = new Cell();

    }

    public Cell estraiCella(int i, int j) {
        return grid[i][j];
    }

    public long getGeneration() {
        return generation;
    }

    public void newLoop() {
        int cicliIniziali = a.getCicliIniziali();
        generation++;

        Field oldField = new Field(this);
        //    System.out.println("Vecchio " + oldField.contaCelleVive());

        cambiaTerritorio();
        if (cicliIniziali > generation) {
            generazioneSpontanea();

        } else {
            try {
                Thread.sleep(a.getRitardoLoop());
            } catch (Exception e) {
                System.out.println("Errore");
            }

            riproduzione(oldField);
            //  morteSpontanea(oldField);
        }
        oldField = null;

    }

    private void cambiaTerritorio() {
        int maxRange = a.getScostamentoMassimoMutazioniAmbientali();
        long modProb = a.getFrequenzaModificaAmbientale();
        int numVal = maxRange * 2 + 1;
        for (int i = 0; i < a.getNumRows(); i++)
            for (int j = 0; j < a.getNumCols(); j++) {
                if ((int) (Math.random() * modProb) == 0) {
                    int diffB = (int) (Math.random() * numVal) - maxRange;
                    int diffG = (int) (Math.random() * numVal) - maxRange;
                    int diffR = (int) (Math.random() * numVal) - maxRange;
                    grid[i][j].setBlueEnv(grid[i][j].getBlueEnv() + diffB);
                    grid[i][j].setGreenEnv(grid[i][j].getGreenEnv() + diffG);
                    grid[i][j].setRedEnv(grid[i][j].getRedEnv() + diffR);
                }
            }


    }

    private void riproduzione(Field oldField) {

        Cell oldGrid[][] = oldField.getGrid();
        float probNasc[] = a.getProbNascitaConNConfinanti();
        float probMorte[] = a.getProbMorteConNConfinanti();

        ArrayList<LivBe> confinanti = new ArrayList<LivBe>();
        for (int i = 0; i < a.getNumRows(); i++)
            for (int j = 0; j < a.getNumCols(); j++) {
                if (oldGrid[i][j].getResident() == null) {
                    //Estraggo tutti residenti vicini
                    confinanti.clear();
                    for (int k = -1; k <= 1; k++)
                        for (int l = -1; l <= 1; l++) {
                            int posX = i + k;
                            int posY = j + l;
                            if (!(k == 0 & l == 0))
                                if (posX >= 0 & posY >= 0 & posX < a.getNumCols() & posY < a.getNumRows()) {
                                    LivBe confinante = oldGrid[posX][posY].getResident();
                                    if (confinante != null)
                                        confinanti.add(confinante);
                                }
                        }
                    int numConfinanti = confinanti.size();
                    boolean nuovoLivBe = false;
                    if (Math.random() < probNasc[numConfinanti])
                        nuovoLivBe = true;
                    confinanti.sort(LivBe::compareTo);
                    // Verificare se qualche confinante si riproduce
                    if (nuovoLivBe)
                        grid[i][j].setResident(new LivBe(confinanti.get(0), a));
                    // TTTT Prevedere scelta casuale del geinitore
                   /* if (confinanti.size() > 0) {
                        for (int k = 0; k < confinanti.size(); k++) {
                            // TTTT verificare produzione nuovo LivBe
                            if (Math.random() < a.getProbNascitaConConfinanteN()[k]) {
                                oldGrid[i][j].setResident(new LivBe(confinanti.get(k),a));
                            }
                        } */
                    //System.out.println(confinanti.size());
                } else {
                    //Estraggo tutti residenti vicini
                    confinanti.clear();
                    // System.out.println(confinanti.size());
                    for (int k = -1; k <= 1; k++)
                        for (int l = -1; l <= 1; l++) {
                            int posX = i + k;
                            int posY = j + l;
                            if (!(k == 0 & l == 0))
                                if (posX >= 0 & posY >= 0 & posX < a.getNumCols() & posY < a.getNumRows()) {
                                    LivBe confinante = oldGrid[posX][posY].getResident();
                                    if (confinante != null)
                                        confinanti.add(confinante);
                                }
                        }
                    int numConfinanti = confinanti.size();
                    //System.out.println("dopo "+confinanti.size());
                    boolean morteLivBe = false;

                    if (Math.random() < probMorte[numConfinanti])
                        morteLivBe = true;
                    if (morteLivBe) {
                        //System.out.println("Morte: "+numConfinanti);
                        grid[i][j].setResident(null);

                    }
                    else {
                        grid[i][j].setResident(oldGrid[i][j].getResident());
                    }
                }
            }
    }

    /*
        private void morteSpontanea(Field oldField) {

            final Cell oldGrid[][] = oldField.getGrid();
            float probMorte[] = a.getProbMorteConNConfinanti();
            ArrayList<LivBe> confinanti = new ArrayList<LivBe>();
            for (int i = 0; i < a.getNumRows(); i++)
                for (int j = 0; j < a.getNumCols(); j++) {
                    if (oldGrid[i][j].getResident() != null) {
                        //Estraggo tutti residenti vicini
                        confinanti.clear();
                       // System.out.println(confinanti.size());
                        for (int k = -1; k <= 1; k++)
                            for (int l = -1; l <= 1; l++) {
                                int posX = i + k;
                                int posY = j + l;
                                if (!(k==0 & l==0))
                                if (posX >= 0 & posY >= 0 & posX < a.getNumCols() & posY < a.getNumRows()) {
                                    LivBe confinante = oldGrid[posX][posY].getResident();
                                    if (confinante != null)
                                        confinanti.add(confinante);
                                }
                            }
                        int numConfinanti = confinanti.size();
                        //System.out.println("dopo "+confinanti.size());
                        boolean morteLivBe = false;

                        if (Math.random() < probMorte[numConfinanti])
                            morteLivBe = true;
                        if (morteLivBe) {
                            //System.out.println("Morte: "+numConfinanti);
                            grid[i][j].setResident(null);
                        }

                    }

                }
        }
    */
    private void generazioneSpontanea() {
        int toll = a.getTolleranzaAmbientalePerGenerazioneSpontanea();
        for (int i = 0; i < a.getNumRows(); i++)
            for (int j = 0; j < a.getNumCols(); j++) {
                int levDNABlue = (int) (Math.random() * 255);
                int levDNAGreen = (int) (Math.random() * 255);
                int levDNARed = (int) (Math.random() * 255);
                if (grid[i][j].getResident() == null) {
                    if (Math.abs(grid[i][j].getBlueEnv() - levDNABlue) <= toll)
                        if (Math.abs(grid[i][j].getGreenEnv() - levDNAGreen) <= toll)
                            if (Math.abs(grid[i][j].getRedEnv() - levDNARed) <= toll) {
                                grid[i][j].setResident(new LivBe(levDNABlue, levDNAGreen, levDNARed, a));
                                // System.out.println("Generazione spontanea " + i + " " + j + " Ciclo:" + generation);
                            }
                }
            }
    }

    public int contaCelleVive() {
        int numeroLivBe = 0;
        for (int i = 0; i < a.getNumRows(); i++)
            for (int j = 0; j < a.getNumCols(); j++) {
                if (!(grid[i][j].getResident() == null))
                    numeroLivBe++;
            }
        return numeroLivBe;
    }

    public Ambiente getA() {
        return a;
    }

    public void setA(Ambiente a) {
        this.a = a;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public void setGeneration(long generation) {
        this.generation = generation;
    }
}
