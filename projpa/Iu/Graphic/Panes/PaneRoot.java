package projpa.Iu.Graphic.Panes;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.Constants;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PaneRoot extends VBox implements Constants, PropertyChangeListener {

    private GameLogic game;
    private Pane mainPane;

    public PaneRoot(GameLogic game) {
        this.game = game;
        this.game.addPropertyChangeListener(this);

        this.mainPane = new MainPane(this.game);
        this.getChildren().addAll(mainPane);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
