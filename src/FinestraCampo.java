import javax.swing.*;
import java.awt.*;

public class FinestraCampo extends JFrame {
    JLabel griglia[][];
    JLabel JLabelCelleVive = new JLabel("0");
    JLabel JLabelGenerazione = new JLabel("0");

    Ambiente a;

    public FinestraCampo(Ambiente a) {
        this.a=a;

        griglia = new JLabel[a.getNumRows()][a.getNumCols()];


        for (int i = 0; i < a.getNumRows(); i++)
            for (int j = 0; j < a.getNumCols(); j++) {
                griglia[i][j] = new JLabel(" ");
                griglia[i][j].setBounds(i * 10+50, j * 10, 10, 10);
            }
        setSize(10*a.getNumRows()+100, 10*a.getNumCols()+100);
        JPanel p = (JPanel) getContentPane();
        p.setLayout(new BorderLayout());
        JPanel campo = new JPanel();
        JPanel controllo = new JPanel();
        campo.setLayout(null);
        controllo.setLayout(new FlowLayout());

        controllo.add(JLabelCelleVive);
        controllo.add(JLabelGenerazione);
        for (int i = 0; i < a.getNumRows(); i++)
            for (int j = 0; j < a.getNumCols(); j++)
                campo.add(griglia[i][j]);

        p.add(campo, BorderLayout.CENTER);
        p.add(controllo, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void aggiornaGriglia(Field f) {
        for (int i = 0; i < a.getNumRows(); i++)
            for (int j = 0; j < a.getNumCols(); j++) {

                Cell cella = f.estraiCella(i, j);
                LivBe residente = cella.getResident();
                griglia[i][j].setBackground(new Color(cella.getRedEnv(), cella.getGreenEnv(), cella.getBlueEnv()));
                if (residente != null) {
                    griglia[i][j].setForeground(new Color(residente.getRedDNA(), residente.getGreenDNA(), residente.getBlueDNA()));
                    griglia[i][j].setText("X");
                } else  {
                    griglia[i][j].setText(" ");
                }
            }
        JLabelGenerazione.setText("" + f.getGeneration());
        JLabelCelleVive.setText(""+ f.contaCelleVive());
        repaint();
    }
}
