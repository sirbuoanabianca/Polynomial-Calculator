package DataModels;
import java.text.DecimalFormat;

public class Monomial implements Comparable<Monomial> {

    private double coefficient;
    private int exponent;

    public Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    @Override
    public int compareTo(Monomial m)
    {   //sorteaza crescator dupa exponent
        if(exponent<m.getExponent())
            return -1;
        else
        if(exponent>m.getExponent())
            return 1;
        else
        return 0;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public static Monomial divide2Monomials(Monomial a,Monomial b)
    {

       double coef=a.getCoefficient()/b.getCoefficient();
       int exp=a.getExponent()-b.getExponent();
       Monomial c=new Monomial(coef,exp);
       return c;
    }

    public String toString()
    {
            String s="";
            String sign="";
            double x = coefficient;
            DecimalFormat dx = new DecimalFormat("#.##");
            //utilizat la afisarea cu 2 zecimale/fara zecimale daca toate sunt 0-uri

            if(x>0)
             sign="+";
                if (exponent>1)
                {       if(coefficient==1)
                        s = "+x^" + getExponent();
                    else
                        if(coefficient==-1)
                        s = "-x^" + getExponent();
                    else
                        s = sign + dx.format(x) + "x^" + getExponent();
                }

            else
                if(exponent==1)
                {
                        if(coefficient==1)
                        s="+x";
                    else
                        if(coefficient==-1)
                        s="-x";
                     else
                        s=sign+dx.format(x) + "x";
                }
            else {      //termenul liber va fi afisat primul si nu trebuie sa inceapa cu semn decat daca e negativ
                if(sign=="+")
                    s = dx.format(x);
                else
                    s = sign + dx.format(x);
                }
    return s;

    }
}
