package app;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * RecoloredImage.java - a class that recolors an image
 */
class RecoloredImage {
    /**
     * image - the stored recolored image
     */
    private Image image;

    /**
     * Constructor which recolors and stores an image to the image instance variable
     * @param inputImage - an Image which is the original to recolor
     * @param oldColor - a Color which is the old color to replace
     * @param newColor - a Color which is the replacement color for the old color
     */
    RecoloredImage(Image inputImage, Color oldColor, Color newColor) {
        // Stores the original image dimensions
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();

        // Initializes image reading and writing
        WritableImage outputImage = new WritableImage(width, height);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();

        // Breaks down oldColor and newColor by rgb components
        int ob = (int) (oldColor.getBlue() * 255);
        int or = (int) (oldColor.getRed() * 255);
        int og = (int) (oldColor.getGreen() * 255);
        int nb = (int) (newColor.getBlue() * 255);
        int nr = (int) (newColor.getRed() * 255);
        int ng = (int) (newColor.getGreen() * 255);

        // Iterates throughout image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Bitwise operations to read and replace pixels
                int argb = reader.getArgb(x, y);
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >>  8) & 0xFF;
                int b =  argb        & 0xFF;
                if (g == og && r == or && b == ob) {
                    r = nr;
                    g = ng;
                    b = nb;
                }
                argb = (a << 24) | (r << 16) | (g << 8) | b;
                writer.setArgb(x, y, argb);
            }
        }

        // Stores final image to image instance variable
        image = outputImage;
    }

    /**
     * Getter for the recolored image
     * @return The recolored image
     */
    Image getImage() {
        return image;
    }
}
