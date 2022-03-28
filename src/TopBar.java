import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

public class TopBar {

    public TopBar() {

    }

    public MenuBar getMenus() {

        // Add MenuBar to root
        MenuBar menuBar = new MenuBar();

        ArrayList<Menu> menuArrayList = new ArrayList<>();
        menuArrayList.add(menuFile());
        menuArrayList.add(menuHelp());

        for (Menu menu : menuArrayList) {
            menuBar.getMenus().add(menu);
        }
        return menuBar;
    }

    public Menu menuFile() {

        // Add Menu "File" to MenuBar
        Menu file = new Menu("File");

        // Add Items to "File"
        MenuItem save = new MenuItem("Save");

        // Adding all the menu items to the File menu
        file.getItems().addAll(save);

        return file;
    }


    public Menu menuHelp() {

        Menu help = new Menu("Help");

        // Add Items to "Hjælp"
        MenuItem guide = new MenuItem("Vejledning");
        MenuItem contact = new MenuItem("Kontakt");

        //Adding all the menu items to the Help menu
        help.getItems().addAll(guide, contact);

        return help;
    }

}
