import java.awt.Color;
// Greyscale morphing creation
public class Editor4 {

	public static void main (String[] args) {
		String source = args[0];
		int steps = Integer.parseInt(args[1]);
		Color[][] sourceImage = Runigram.read(source);
        Color[][] greyScaled = Runigram.grayScaled(sourceImage);
		Runigram.setCanvas(sourceImage);
        Runigram.morph(sourceImage, greyScaled, steps);
	}
}
