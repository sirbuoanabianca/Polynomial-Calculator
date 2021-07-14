package Tests;

import DataModels.Polynomial;
import Exceptions.WrongPolynomial;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestParsing {

    @Test
    public void testParsing1()
    {
        Polynomial p=null;
        try {
            p = new Polynomial("3x^5-12x^3+8");
        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }

        Assert.assertEquals("+3x^5",p.getPolynomial().get(0).toString());
        Assert.assertEquals("-12x^3",p.getPolynomial().get(1).toString());
        Assert.assertEquals("8",p.getPolynomial().get(2).toString());
    }

    @Test
    public void testParsing2()
    {
        Polynomial p=null;
        try {
            p = new Polynomial("-x^7+10x+2x");
        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }

        Assert.assertEquals("-x^7",p.getPolynomial().get(0).toString());
        Assert.assertEquals("+10x",p.getPolynomial().get(1).toString());
        Assert.assertEquals("+2x",p.getPolynomial().get(2).toString());
    }

    @Test
    public void testParsing3()
    {
        Polynomial p=null;
        try {
            p = new Polynomial("-100x^4+12x-1");
        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }

        Assert.assertEquals("-100x^4",p.getPolynomial().get(0).toString());
        Assert.assertEquals("+12x",p.getPolynomial().get(1).toString());
        Assert.assertEquals("-1",p.getPolynomial().get(2).toString());

    }

    @Test
    public void testParsing4()
    {
        Polynomial p=null,p2=null;
        try {
            p = new Polynomial("-100");
            p2=new Polynomial("-x^5+7x^2-x");
        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }

        Assert.assertEquals("-100",p.getPolynomial().get(0).toString());

        Assert.assertEquals("-x^5",p2.getPolynomial().get(0).toString());
        Assert.assertEquals("+7x^2",p2.getPolynomial().get(1).toString());
        Assert.assertEquals("-x",p2.getPolynomial().get(2).toString());

    }
}
