package Interface;

import BusinessLogic.PolynomialOperations;
import DataModels.Polynomial;
import Exceptions.IllegalDivisionByZero;
import Exceptions.WrongPolynomial;
import Interface.Model;
import Interface.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
        private Model model;
        private View view;
        private boolean needsClearing;

    public Controller(Model model,View view)
    {
        this.model = model;
        this.view = view;
        needsClearing=false;

        view.addButtonListener(new PolynomialListener());
    }

    class PolynomialListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JButton b= (JButton) e.getSource();

            if(needsClearing){
                view.clearOutput();
                view.clearQuotient();
                view.clearRemainder();
                needsClearing=false;
            }

            if(b.getModel().isArmed())
            {
                Polynomial pol1 = null,pol2=null,pol3=null;
                try{
                 pol1=new Polynomial(view.getP1().getText());
                 pol2=new Polynomial(view.getP2().getText());
                 pol3=new Polynomial("");
                }catch(WrongPolynomial ex)
                {
                    String msg = "Polinomul NU este introdus corect!";

                    JOptionPane optionPane = new JOptionPane();
                    optionPane.setMessage(msg);
                    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog(null, "Info");
                    dialog.setVisible(true);
                }
                    if(pol1.getPolynomial().size()==0)
                        pol3=pol2;
                else
                    if(pol2.getPolynomial().size()==0)
                        pol3=pol1;
                try {
                    model.evaluatePolynomials(pol1, pol2, b.getText(), pol3);
                }catch (IllegalDivisionByZero ex)
                {
                    String msg = "Nu se poate imparti polinomul la 0!";
                    JOptionPane optionPane = new JOptionPane();
                    optionPane.setMessage(msg);
                    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog(null, "Info");
                    dialog.setVisible(true);
                }
                catch ( WrongPolynomial ex)
                {   String msg=null;
                    switch(b.getText()) {
                        case "+" -> {
                            msg = "Adunarea necesita 2 polinoame";break;
                        }
                        case "-" -> {
                            msg = "Scaderea necesita 2 polinoame";break;
                        }
                        case "*" -> {
                            msg = "Inmultirea necesita 2 polinoame";break;
                        }
                        case "/" -> {
                            msg = "Impartirea necesita 2 polinoame";break;
                        }
                        case "∫" -> {
                            msg = "Integrarea necesita 1 polinom";break;
                        }
                        case "’" -> {
                            msg = "Derivarea necesita 1 polinom";break;
                        }
                    }
                    JOptionPane optionPane = new JOptionPane();
                    optionPane.setMessage(msg);
                    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog(null, "Info");
                    dialog.setVisible(true);
                }


                if(b.getText().equals("/"))
                {
                    ArrayList<Polynomial>divideRes= model.getDivideResult();
                    view.setQuotient(divideRes.get(0).toString());
                    view.setRemainder(divideRes.get(1).toString());
                    needsClearing=true;
                }
                else
                 {
                    Polynomial r = model.getResult();
                    view.setOutput(r.toString());
                     needsClearing=true;
                }

            }
        }
    }


}
