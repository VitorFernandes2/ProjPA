package projpa.Iu.Graphic;

import javafx.scene.layout.Pane;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.Panes.PaneRoot;

public class PaneOrganizer {

    private final Pane root;

    public PaneOrganizer(GameLogic game) {
        this.root = new PaneRoot(game);
    }

    public Pane getRoot(){
        return root;
    }

}
