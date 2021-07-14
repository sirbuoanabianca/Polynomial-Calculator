package Interface;

import BusinessLogic.PolynomialOperations;
import DataModels.Polynomial;
import Exceptions.IllegalDivisionByZero;
import Exceptions.WrongPolynomial;

import java.util.ArrayList;

public class Model {
    private Polynomial result;
    private ArrayList<Polynomial> divideResult;//divide.get(0)=quotient,divide.get(1)=remainder;

    public Model()
    {
        result=new Polynomial();
        divideResult=new ArrayList<>();
    }

    public void evaluatePolynomials(Polynomial a, Polynomial b, String operator, Polynomial c) throws IllegalDivisionByZero,WrongPolynomial
    {
        switch (operator)
        {
            case "+" -> {
                if(a.getPolynomial().size()==0 || b.getPolynomial().size()==0)
                    throw new WrongPolynomial("NU ai introdus 2 polinoame!");
                result = PolynomialOperations.addPolynomials(a, b);
                break;
            }
            case "-" -> {
                if(a.getPolynomial().size()==0 || b.getPolynomial().size()==0)
                    throw new WrongPolynomial("NU ai introdus 2 polinoame!");
                result = PolynomialOperations.subPolynomials(a, b);
                break;
            }
            case "*" -> {
                if(a.getPolynomial().size()==0 || b.getPolynomial().size()==0)
                    throw new WrongPolynomial("NU ai introdus 2 polinoame!");
                result = PolynomialOperations.multiplyPolynomials(a, b);
                break;
            }
            case "/" -> {
                if(a.getPolynomial().size()==0 || b.getPolynomial().size()==0)
                    throw new WrongPolynomial("NU ai introdus 2 polinoame");
                if(b.getPolynomial().size()==1 &&b.getPolynomial().get(0).getCoefficient()==0)
                    throw new IllegalDivisionByZero("Nu se poate imparti polinomul cu 0!");

                divideResult = PolynomialOperations.dividePolynomials(a, b);
                break;
            }
            case "∫" -> {
                if(a.getPolynomial().size()!=0 && b.getPolynomial().size()!=0)
                    throw new WrongPolynomial("integrarea necesita doar un polinom");
                result = PolynomialOperations.integratePolynomial(c);
                break;
            }
            case "’" -> {
                if(a.getPolynomial().size()!=0 && b.getPolynomial().size()!=0)
                    throw new WrongPolynomial("derivarea necesita doar un polinom");
                result = PolynomialOperations.derivatePolynomial(c);
                break;
            }
        }
    }

    public Polynomial getResult() {
        return result;
    }

    public ArrayList<Polynomial> getDivideResult() {
        return divideResult;
    }
}
