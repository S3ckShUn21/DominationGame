package Input;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {

    List<Button> buttonList;

    public InputHandler() {
        buttonList = new ArrayList<>();
    }

    public void addButton(Button b) {
        buttonList.add(b);
    }

    public void run() {
        for (Button b : buttonList) {
            if (b.clicked()) {
                b.onClick();
            }
        }
    }

    public void show() {
        buttonList.forEach(Button::show);
    }
}
