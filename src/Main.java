

public class Main {

    public static void main(String args[]) {

        Ambiente a = new Ambiente();
        while(true) {
            int hashAmbiente=a.hashCode();
            FinestraCampo f = new FinestraCampo(a);
            Field campo = new Field(a);
            int celleVive = 0;
            do {
                campo.newLoop();
                celleVive = campo.contaCelleVive();
                f.aggiornaGriglia(campo);

            }
            while (celleVive != 10000 & a.hashCode()==hashAmbiente);
            f.dispose();
            System.out.println(campo.getGeneration());
        }
    }

}