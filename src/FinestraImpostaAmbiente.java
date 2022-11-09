import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FinestraImpostaAmbiente extends JFrame implements WindowListener {
    Ambiente a;
    JTextField JTDimensioneX;
    JTextField JTDimensioneY;
    public FinestraImpostaAmbiente(Ambiente a)
    {
        this.a=a;
        setLayout(new GridLayout(2,2));
        JLabel JLDimensioneX=new JLabel("Dimensione X");
        add(JLDimensioneX);
        JTDimensioneX=new JTextField(""+a.getNumCols());
        add(JTDimensioneX);
        JLabel JLDimensioneY=new JLabel("Dimensione X");
        add(JLDimensioneY);
        JTDimensioneY=new JTextField(""+a.getNumRows());
        add(JTDimensioneY);
        setSize(500,600);
        addWindowListener(this);
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        System.out.println("sono qui");
        a.setNumRows(Integer.parseInt(JTDimensioneY.getText()));
        a.setNumCols(Integer.parseInt(JTDimensioneX.getText()));
        System.out.println(a.getNumCols());
        System.out.println(a.getNumRows());

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
