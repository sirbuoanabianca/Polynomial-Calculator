import Interface.Controller;
import Interface.Model;
import Interface.View;

public class Main {

    public static void main(String[] args) {

        Model model=new Model();
        View view=new View(model);
        Controller controller=new Controller(model,view);
        view.setVisible(true);

    }

}
