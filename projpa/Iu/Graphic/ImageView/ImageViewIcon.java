package projpa.Iu.Graphic.ImageView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewIcon extends ImageView {

    public ImageViewIcon(Image img, double height, double width) {
        super(img);
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

}
