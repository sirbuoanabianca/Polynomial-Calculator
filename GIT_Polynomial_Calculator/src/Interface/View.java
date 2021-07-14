package Interface;
import Interface.Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Model model;
    private JTextField p1;
    private JTextField p2;
    private JTextField output;
    private JTextField quotient,remainder;
    private JButton add, sub, mul, div, in, der;

    public View(Model model) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        this.model = model;
        p1 = new JTextField(35);
        p2 = new JTextField(35);
        output = new JTextField(35);
        quotient=new JTextField(35);
        remainder=new JTextField(35);
        add = new JButton("+");
        add.setFont(new Font("Serif", Font.BOLD, 18));
        sub = new JButton("-");
        sub.setFont(new Font("Serif", Font.BOLD, 18));
        mul = new JButton("*");
        mul.setFont(new Font("Serif", Font.BOLD, 18));
        div = new JButton("/");
        div.setFont(new Font("Serif", Font.BOLD, 18));
        in = new JButton("∫");
        in.setFont(new Font("Serif", Font.BOLD, 18));
        der = new JButton("’");
        der.setFont(new Font("Serif", Font.BOLD, 18));

        this.setTitle("Polynomial Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 340);
        p1.setFont(new Font("Serif", Font.BOLD, 15));
        p2.setFont(new Font("Serif", Font.BOLD, 15));
        output.setFont(new Font("Serif", Font.BOLD, 15));
        quotient.setFont(new Font("Serif", Font.BOLD, 15));
        remainder.setFont(new Font("Serif", Font.BOLD, 15));
        add.setBackground(Color.WHITE);
        sub.setBackground(Color.WHITE);
        mul.setBackground(Color.WHITE);
        div.setBackground(Color.WHITE);
        in.setBackground(Color.WHITE);
        der.setBackground(Color.WHITE);


        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4, 2));
        FlowLayout layout = new FlowLayout();
        JPanel panel2 = new JPanel();
        panel2.setLayout(layout);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2, 2));

        JLabel pol1 = new JLabel("First polynomial : ");
        pol1.setFont(new Font("Serif", Font.BOLD, 18));
        JLabel pol2 = new JLabel("Second polynomial : ");
        pol2.setFont(new Font("Serif", Font.BOLD, 18));
        JLabel polRes = new JLabel("Result : ");
        polRes.setFont(new Font("Serif", Font.BOLD, 18));
        JLabel quo = new JLabel("Quotient : ");
        quo.setFont(new Font("Serif", Font.BOLD, 18));
        JLabel rem = new JLabel("Remainder : ");
        rem.setFont(new Font("Serif", Font.BOLD, 18));

        panel1.add(pol1);
        panel1.add(p1);
        panel1.add(pol2);
        panel1.add(p2);
        panel1.add(polRes);
        panel1.add(output);
        panel2.add(add);
        panel2.add(sub);
        panel2.add(mul);
        panel2.add(div);
        panel2.add(der);
        panel2.add(in);
        panel3.add(quo);
        panel3.add(quotient);
        panel3.add(rem);
        panel3.add(remainder);

        add(panel1, "North");
        add(panel2, "Center");
        add(panel3,"South");
        setVisible(true);

        setResizable(false);
        this.setLocationRelativeTo(null);

    }

    public JTextField getP1() {
        return p1;
    }

    public JTextField getP2() {
        return p2;
    }

    public JTextField getOutput() {
        return output;
    }

    public void setOutput(String s) {
        output.setText(s);
    }

    public void setQuotient(String s) {
        quotient.setText(s);
    }

    public void setRemainder(String s) {
        remainder.setText(s);
    }

    public void clearOutput() {
        output.setText("");
    }

    public void clearRemainder() {
        remainder.setText("");
    }

    public void clearQuotient() {
        quotient.setText("");
    }

    void addButtonListener(ActionListener b)
    {
        add.addActionListener(b);
        sub.addActionListener(b);
        mul.addActionListener(b);
        div.addActionListener(b);
        in.addActionListener(b);
        der.addActionListener(b);
    }


}
