package projpa.Iu.Graphic;

import javafx.stage.Screen;

//Colocar todas as imagens e medidas que são cruciais ao sistema
public interface Constants {

    int WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    int HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    String EXIT_IMAGE = "Images\\exit.png";
    String PLAY_IMAGE = "Images\\play-button-inside-a-circle.png";
    String SCORE_IMAGE = "Images\\score.png";
    String OPTIONS_IMAGE = "Images\\options.png";
    String LOAD_IMAGE = "Images\\load.png";
    String CANCEL_IMAGE = "Images\\cancel.png";
    String ACCEPT_IMAGE = "Images\\accept.png";
    String UNDO_IMAGE = "Images\\undo.png";
    String SAVE_IMAGE = "Images\\save.png";
    String GAME_PLAY_MUSIC = "Music\\gameplaymusicship.mp3";
    String MAIN_MUSIC = "Music\\space.mp3";

}
