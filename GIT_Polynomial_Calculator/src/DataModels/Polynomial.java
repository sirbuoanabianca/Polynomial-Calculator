package DataModels;
import DataModels.Monomial;
import Exceptions.WrongPolynomial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class Polynomial {

   private ArrayList<Monomial> polynomial;

   public Polynomial()
   {
       polynomial = new ArrayList<>();
   }

    public Polynomial(String s)  throws WrongPolynomial
    {
        polynomial = new ArrayList<>();
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(s);
        int x = 0;
        if(s.equals("0"))
            addToThisPolynomial(new Monomial(0,0));
        else {
            while (matcher.find()) {
                String string = matcher.group(1);
                String[] parts = string.split("x\\^|x"); //parts va contine pe poz 0 coeficientul si pe poz 1 exponentul
                double coeff = 1;
                int ex = 0;

                if (matcher.group(1).contains("x^")) //caz pentru putere diferita de 1
                {
                    if (parts[0].equals(""))    //nu avem coeficient,deci e 1 implicit
                        parts[0] = "1";

                    ex = parseInt(parts[1]);
                } else if (matcher.group(1).contains("x")) //caz pentru putere egala cu 1
                    ex = 1;

                if (parts.length != 0) //caz in care gasim elemente inainte sau dupa delimitatoare,coeficient sau exponent
                {
                    if (parts[0].equals("+")) //nu avem coeficient=> coeficientul e +1
                        parts[0] = "1";
                    else if (parts[0].equals("-")) //nu avem coeficient=> coeficientul e -1
                        parts[0] = "-1";

                    try {
                        coeff = Double.parseDouble(parts[0]);
                    } catch (NumberFormatException w) {
                        throw new WrongPolynomial("Polinomul nu este introdus corect!");
                    }

                }

                if (coeff != 0) {
                    Monomial m = new Monomial(coeff, ex);
                    this.addToThisPolynomial(m);
                    x = x + 1;
                    System.out.println("Group " + x + ": " + matcher.group(1));
                }
            }
        }
    }

    public void addToThisPolynomial(Monomial m)
    {
        polynomial.add(m);
    }

    public void sortAscByExponent()
    {
        Collections.sort(polynomial);
    }

    public void addZeros()
    {   //metoda e utilizata la adaugarea tuturor termenilor lipsa din polinom,cei cu coeficient 0
        //are rolul de a face sa coincida exponentul monomului cu pozitia lui din polinom
        for(int i=0;i<polynomial.size();i++)
        {
            if(polynomial.get(i).getExponent() != i)
            {
                Monomial o=new Monomial(0,i);
                polynomial.add(i,o);
                i--;

            }
        }
    }

    public ArrayList<Monomial> getPolynomial() {
        return polynomial;
    }

    public Monomial getLastTermDiffFromZero()
    {

        for(int i=polynomial.size()-1;i>=0;i--)
        {
            if(polynomial.get(i).getCoefficient()!=0)
                return polynomial.get(i);
        }
        return polynomial.get(0);

    }

    public void removeLastZeros()
    {int i=0;
        if(polynomial.size()!=0)
         i=polynomial.size()-1;
        while(polynomial.size()!=1 && polynomial.get(i).getCoefficient()==0) //ca sa nu stearga daca ramane rest=0
        {
            polynomial.remove(i);
            i--;
        }
    }

    @Override
    public String toString()
    {
        String result="";
        boolean allTermsAreZero=true;
        for(Monomial m:polynomial)
            if(m.getCoefficient()!=0)
                allTermsAreZero=false;

        if(allTermsAreZero)
            result=""+0;
        else
            {
                for (Monomial m : polynomial)
                {
                    if (m.getCoefficient() != 0)
                        result = result  + m.toString();
                }
            }
        if(result.charAt(0)=='+')
            result=result.substring(1);
        return result;
    }
}
