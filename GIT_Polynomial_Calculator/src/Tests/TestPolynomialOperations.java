package Tests;

import BusinessLogic.PolynomialOperations;
import DataModels.Polynomial;
import Exceptions.WrongPolynomial;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPolynomialOperations {

    @Test
    public void testAddition() {
        Polynomial p1=null,p2=null,p3=null,p4=null,p5=null;
        try {
             p1 = new Polynomial("3x+2");
             p2=new Polynomial("-5x");
             p3=new Polynomial("10x^5-4x^3+2x");
             p4=new Polynomial("-x^6+x^4+8x^3-7");
             p5=new Polynomial("10");

        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }
        System.out.println("Inside testAddition()");
        Assert.assertEquals("2-2x", PolynomialOperations.addPolynomials(p1,p2).toString());
        Assert.assertEquals("-7+2x+4x^3+x^4+10x^5-x^6", PolynomialOperations.addPolynomials(p3,p4).toString());
    }

    @Test
    public void testSubstraction() {
        Polynomial p1=null,p2=null,p3=null,p4=null,p5=null;
        try {
            p1 = new Polynomial("3x+2");
            p2=new Polynomial("-5x");
            p3=new Polynomial("10x^5-4x^3+2x");
            p4=new Polynomial("-x^6+x^4+8x^3-7");
            p5=new Polynomial("10");

        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }
        System.out.println("Inside testSubstraction()");
        Assert.assertEquals("-10-5x", PolynomialOperations.subPolynomials(p2,p5).toString());
        Assert.assertEquals("-2-x-4x^3+10x^5", PolynomialOperations.subPolynomials(p3,p1).toString());
    }

    @Test
    public void testMultiplication() {
        Polynomial p1=null,p2=null,p3=null,p4=null,p5=null;
        try {
            p1 = new Polynomial("3x+2");
            p2=new Polynomial("-5x");
            p3=new Polynomial("10x^5-4x^3+2x");
            p4=new Polynomial("-x^6+x^4+8x^3-7");
            p5=new Polynomial("10");

        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }
        System.out.println("Inside testMultiplication()");
        Assert.assertEquals("-10x-15x^2", PolynomialOperations.multiplyPolynomials(p1,p2).toString());
        Assert.assertEquals("-50x", PolynomialOperations.multiplyPolynomials(p2,p5).toString());
        Assert.assertEquals("-14-21x+16x^3+26x^4+3x^5-2x^6-3x^7", PolynomialOperations.multiplyPolynomials(p1,p4).toString());
    }

    @Test
    public void testDevision() {
        Polynomial p1=null,p2=null,p3=null,p4=null,p5=null,p6=null;
        try {
            p1 = new Polynomial("3x+2");
            p2=new Polynomial("-5x");
            p3=new Polynomial("10x^5-4x^3+2x");
            p4=new Polynomial("-x^6+x^4+8x^3-7");
            p5=new Polynomial("10");
            p6=new Polynomial("-x^5+12x^3");

        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }
        System.out.println("Inside testDevision()");
        Assert.assertEquals("-0.6", PolynomialOperations.dividePolynomials(p1,p2).get(0).toString());
        Assert.assertEquals("2", PolynomialOperations.dividePolynomials(p1,p2).get(1).toString());

        Assert.assertEquals("-0.1x", PolynomialOperations.dividePolynomials(p4,p3).get(0).toString());
        Assert.assertEquals("-7+0.2x^2+8x^3+0.6x^4", PolynomialOperations.dividePolynomials(p4,p3).get(1).toString());

        Assert.assertEquals("-0.5x", PolynomialOperations.dividePolynomials(p2,p5).get(0).toString());
        Assert.assertEquals("0", PolynomialOperations.dividePolynomials(p2,p5).get(1).toString());

        Assert.assertEquals("-2.57x+3.85x^2+0.22x^3-0.33x^4", PolynomialOperations.dividePolynomials(p6,p1).get(0).toString());
        Assert.assertEquals("5.14x", PolynomialOperations.dividePolynomials(p6,p1).get(1).toString());
    }

    @Test
    public void testDerivation() {
        Polynomial p1=null,p2=null,p3=null,p4=null,p5=null;
        try {
            p1 = new Polynomial("3x+2");
            p2=new Polynomial("-5x");
            p3=new Polynomial("10x^5-4x^3+2x");
            p4=new Polynomial("-x^6+x^4+8x^3-7");
            p5=new Polynomial("10");

        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }
        System.out.println("Inside testDerivation()");
        Assert.assertEquals("3", PolynomialOperations.derivatePolynomial(p1).toString());
        Assert.assertEquals("-5", PolynomialOperations.derivatePolynomial(p2).toString());
        Assert.assertEquals("2-12x^2+50x^4", PolynomialOperations.derivatePolynomial(p3).toString());
        Assert.assertEquals("24x^2+4x^3-6x^5", PolynomialOperations.derivatePolynomial(p4).toString());
        Assert.assertEquals("0", PolynomialOperations.derivatePolynomial(p5).toString());
    }

    @Test
    public void testIntegration() {
        Polynomial p1=null,p2=null,p3=null,p4=null,p5=null;
        try {
            p1 = new Polynomial("3x+2");
            p2=new Polynomial("-5x");
            p3=new Polynomial("10x^5-4x^3+2x");
            p4=new Polynomial("-x^6+x^4+8x^3-7");
            p5=new Polynomial("10");

        } catch (WrongPolynomial wrongPolynomial) {
            wrongPolynomial.printStackTrace();
        }
        System.out.println("Inside testIntegration()");
        Assert.assertEquals("2x+1.5x^2", PolynomialOperations.integratePolynomial(p1).toString());
        Assert.assertEquals("-2.5x^2", PolynomialOperations.integratePolynomial(p2).toString());
        Assert.assertEquals("x^2-x^4+1.67x^6", PolynomialOperations.integratePolynomial(p3).toString());
        Assert.assertEquals("-7x+2x^4+0.2x^5-0.14x^7", PolynomialOperations.integratePolynomial(p4).toString());
        Assert.assertEquals("10x", PolynomialOperations.integratePolynomial(p5).toString());
    }

}