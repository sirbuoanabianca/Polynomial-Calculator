package BusinessLogic;

import DataModels.Monomial;
import DataModels.Polynomial;
import Exceptions.WrongPolynomial;

import java.util.ArrayList;

public class PolynomialOperations {
/*    adunarea,scaderea, inmultirea si imparirea se folosesc de metodele:
        addZeros() :modifica polinomul pentru a face sa coincida exponentul cu pozitia din arraylist
        sortAscByExponent():sorteaza termenii polinomului crescator dupa exponent*/

    public static Polynomial addPolynomials(Polynomial a,Polynomial b)
    {
        a.sortAscByExponent();
        a.addZeros();

        b.sortAscByExponent();
        b.addZeros();
        int minSize;
        Polynomial maxP;
        if(a.getPolynomial().size()<b.getPolynomial().size())
        {
            minSize=a.getPolynomial().size();
            maxP=b;
        }
        else
        {
            minSize=b.getPolynomial().size();
            maxP=a;
        }

        Polynomial c=new Polynomial();
        if(a.getPolynomial().size()==0 && b.getPolynomial().size()==0)
            c.addToThisPolynomial(new Monomial(0,0));

        for(int i=0;i<minSize;i++)
        {   double coef=a.getPolynomial().get(i).getCoefficient()+b.getPolynomial().get(i).getCoefficient();
            Monomial res=new Monomial(coef,i);
            c.getPolynomial().add(res);
        }

        for(int i=minSize;i<maxP.getPolynomial().size();i++)
            c.getPolynomial().add(maxP.getPolynomial().get(i));

        return c;
    }

    public static Polynomial subPolynomials(Polynomial a,Polynomial b)
    {
        a.sortAscByExponent();
        a.addZeros();

        b.sortAscByExponent();
        b.addZeros();

        int minSize;
        Polynomial maxP;
        if(a.getPolynomial().size()<b.getPolynomial().size())
        {
            minSize=a.getPolynomial().size();
            maxP=b;
        }
        else
        {
            minSize=b.getPolynomial().size();
            maxP=a;
        }

        Polynomial c=new Polynomial();
        if(a.getPolynomial().size()==0 && b.getPolynomial().size()==0)
            c.addToThisPolynomial(new Monomial(0,0));

        for(int i=0;i<minSize;i++)
        {   double coef=a.getPolynomial().get(i).getCoefficient()-b.getPolynomial().get(i).getCoefficient();
            Monomial res=new Monomial(coef,i);
            c.getPolynomial().add(res);
        }

        for(int i=minSize;i<maxP.getPolynomial().size();i++)
        {
            int ex=maxP.getPolynomial().get(i).getExponent();
            double r=maxP.getPolynomial().get(i).getCoefficient();
            if(maxP==b)
             r*=-1;
            Monomial m=new Monomial(r,ex);
            c.getPolynomial().add(m);
        }

        return c;
    }

    public static Polynomial derivatePolynomial(Polynomial a)
    {
        a.sortAscByExponent();

        Polynomial c=new Polynomial();
        if(a.getPolynomial().size()==0)
            c.addToThisPolynomial(new Monomial(0,0));

        for(Monomial m:a.getPolynomial())
        {
            double coef;int exp;
        coef=m.getCoefficient()*m.getExponent();
        exp=m.getExponent()-1;
            c.addToThisPolynomial(new Monomial(coef,exp));
        }
        return c;
    }

    public static Polynomial integratePolynomial(Polynomial a)
    {   a.sortAscByExponent();

        Polynomial c=new Polynomial();
        if(a.getPolynomial().size()==0)
            c.addToThisPolynomial(new Monomial(0,0));

        for(Monomial m:a.getPolynomial())
        {
            double coef;int exp;
            exp=m.getExponent()+1;
            coef=m.getCoefficient()/exp;
            c.addToThisPolynomial(new Monomial(coef,exp));
        }
        return c;
    }

    public static Polynomial multiplyPolynomials(Polynomial a,Polynomial b)
    {
        a.sortAscByExponent();
        a.addZeros();

        b.sortAscByExponent();
        b.addZeros();
        Polynomial c=new Polynomial();
        int na=a.getPolynomial().size();
        int nb=b.getPolynomial().size();
        double coef=0;

        if(a.getPolynomial().size()==0 || b.getPolynomial().size()==0)
        {
            c.addToThisPolynomial(new Monomial(0, 0));
            return c;
        }

        for (int i=0; i<na; i++)
        {
            //inmulteste elemenul curent al primului polinom
            // cu toate elementele celui de-al doilea polinom

            for (int j=0; j<nb; j++)
            {
                coef=a.getPolynomial().get(i).getCoefficient() *
                                                            b.getPolynomial().get(j).getCoefficient();
                if(i+j<c.getPolynomial().size()) //adun coeficientul la coeficientul anterior de pe poz i+j
                {                                      //pentru a minimiza nr-ul de termeni
                    double init=c.getPolynomial().get(i+j).getCoefficient();
                    coef+=init;
                    c.getPolynomial().remove(i+j); //sterg elementul existent anterior pentru a-l inlocui
                }
                Monomial m=new Monomial(coef,i+j);
                c.getPolynomial().add(i + j,m); //se adauga in polinomul rezultat pe poz i+j pt ca se aduna exponentii
                                                        //si pentru ca exponentul sa coincida cu pozitia din arraylist
            }

        }
        return c;
    }

    public static ArrayList<Polynomial> dividePolynomials(Polynomial a, Polynomial b) {
        a.sortAscByExponent();
        a.addZeros();

        b.sortAscByExponent();
        b.addZeros();

        Polynomial big, small;
        Polynomial quotient=new Polynomial();
        quotient.addToThisPolynomial(new Monomial(0,0));
        Polynomial remainder=new Polynomial();
       ArrayList<Polynomial> polList =new ArrayList<>();

        big=a;small=b;
        if(big.getPolynomial().size()<small.getPolynomial().size())
        {   //daca polinomul de la numarator are grad mai mic si cel de la numitor grad mai mare
            quotient=new Polynomial();
            quotient.addToThisPolynomial(new Monomial(0,0));
            remainder=big;
        }
        else {
            while (big.getPolynomial().size() >= small.getPolynomial().size()) {
                Monomial m = Monomial.divide2Monomials(big.getLastTermDiffFromZero(), small.getLastTermDiffFromZero());
                Polynomial qTemp = new Polynomial();
                qTemp.addToThisPolynomial(m);


                quotient = PolynomialOperations.addPolynomials(quotient, qTemp);
                remainder = PolynomialOperations.subPolynomials(big,
                        PolynomialOperations.multiplyPolynomials(small, qTemp));
                big = remainder;
                big.removeLastZeros();
                small.removeLastZeros();

                if (big.getPolynomial().size() == small.getPolynomial().size() &&    //atunci cand remainder=0
                        big.getPolynomial().get(0).getCoefficient() == 0)
                    break;
            }
        }
        polList.add(quotient);
        polList.add(remainder);
        return polList;

    }
}
