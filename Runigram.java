import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		Color[][] image2 = flippedVertically(tinypic);
		System.out.println();
		print(image2);
		Color[][] grey = grayScaled(tinypic);
		System.out.println();
		print(grey);
		Color[][] scaled = scaled(tinypic, 3, 5);
		System.out.println();
		print(scaled);

		// Test morph:
		Color[][] cake = read("cake.ppm");
		Color[][] ironman = read("ironman.ppm");
		int steps = 5;
		morph(ironman, cake, steps);
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		// First line = P3
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		// Third line = 255
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				int r = in.readInt();
				int g = in.readInt();
				int b = in.readInt();
				image[i][j] = new Color(r, g, b);
			}
		}
		
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		int count = 1;
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				print(image[i][j]);
				if (count % image[0].length == 0 && count != 0) { 
					System.out.println();
				}
				count++;
			}
		}
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
	}
	


	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		// Improper input handling:
		if (image.length < 1) {
			System.out.println("Improper input entered to <flippedHorizontally(Color[][] image)>");
			return null;
		} else if (image[0].length < 1) {
			System.out.println("Improper input entered to <flippedHorizontally(Color[][] image)>");
			return null;
		}

		Color[][] result = new Color[image.length][image[0].length];
		for (int row = 0; row < image.length; row++) {
			for (int col = 0; col < image[row].length; col++) {
				result[row][image[row].length - col - 1] = image[row][col];
			}
		}
		return result;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		// Improper input handling:
		if (image.length < 1) {
			System.out.println("Improper input entered to <flippedHorizontally(Color[][] image)>");
			return null;
		} else if (image[0].length < 1) {
			System.out.println("Improper input entered to <flippedHorizontally(Color[][] image)>");
			return null;
		}
		Color[][] result = new Color[image.length][image[0].length];
		for (int row = 0; row < image.length; row++) {
			for (int col = 0; col < image[row].length; col++) {
				result[image.length - row - 1][col] = image[row][col];
			}
		}
		return result;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		int lum = (int) (0.299 * pixel.getRed() + 0.587 * pixel.getGreen() + 0.114 * pixel.getBlue());
		Color result = new Color(lum, lum, lum);
		return result;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		// Improper input handling:
		if (image.length < 1) {
			System.out.println("Improper input entered to <flippedHorizontally(Color[][] image)>");
			return null;
		} else if (image[0].length < 1) {
			System.out.println("Improper input entered to <flippedHorizontally(Color[][] image)>");
			return null;
		}
		Color[][] result = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				result[i][j] = luminance(image[i][j]);
			}
		}
		return result;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		// Improper input handling:
		if (image.length < 1) {
			System.out.println("Improper input entered to <flippedHorizontally(Color[][] image)>");
			return null;
		} else if (image[0].length < 1) {
			System.out.println("Improper input entered to <flippedHorizontally(Color[][] image)>");
			return null;
		}

		// scaled needs fixing it seems
		// looks like the loop logic isn't right
		double hRatio = (double)image.length / height;
		double wRatio = (double)image[0].length / width;
		Color[][] result = new Color[height][width];
		for (int h = 0; h < result.length; h++) {
			int currHSrc = (int)(h * hRatio);
			if (currHSrc >= image.length) {
				currHSrc = image.length - 1;
			}
			for (int w = 0; w < result[h].length; w++) {
				int currWSrc = (int)(w * wRatio);
				if (currWSrc >= image[0].length) {
					currWSrc = image[0].length - 1;
				}
				result[h][w] = image[currHSrc][currWSrc];
			}
		}
		return result;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static int blend(int c1Val, int c2Val, double alpha) {
		// Blends each rgb val at a time alone
		return (int)(c1Val * alpha + c2Val * (1 - alpha));
	}

	public static Color blend(Color c1, Color c2, double alpha) {
		// Will it blend?
		int rVal = blend(c1.getRed(), c2.getRed(), alpha);
		int gVal = blend(c1.getGreen(), c2.getGreen(), alpha);
		int bVal = blend(c1.getBlue(), c2.getBlue(), alpha);
		Color itWillBlend = new Color(rVal, gVal, bVal);
		return itWillBlend;
	}
	
	

	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		boolean safetyCheck1 = (image1.length == image2.length || image1.length != 0);
		boolean safetyCheck2 = (image1[0].length == image2[0].length || image2[0].length != 0);
		// First, make sure the 2d array has rows at all
		if (!safetyCheck1) {
			System.out.println("Improper input, unequal row numbers between images to blend");
			return null;
		}
		// Then, make sure there are 1d arrays in the 2d arrays of length >= 1
		// This could have been further "safet-ified" but i think this is better then nothing
		if (!safetyCheck2) {
			System.out.println("Improper input, unequal row numbers between images to blend");
			return null;
		}
		// Herein we have proper inputs of same length
		Color[][] result = new Color[image1.length][image1[0].length];
		int rowIdx = 0;
		while (rowIdx < image1.length) {
			int colIdx = 0;
			while (colIdx < image1[rowIdx].length) {
				Color pixel1 = image1[rowIdx][colIdx];
				Color pixel2 = image2[rowIdx][colIdx];
				result[rowIdx][colIdx] = blend(pixel1, pixel2, alpha);
				colIdx++;
			}
			rowIdx++;
		}
		return result;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		if (target.length == 0 || source.length == 0) {
			System.out.println("No image provided, no morphing doing");
			return;
		} 
		if (target[0].length == 0 || source[0].length == 0) {
			System.out.println("No 1d arrays in images");
			return;
		}
		// Scale images to same size:
		if (source.length != target.length || source[0].length != target[0].length) {
			target = scaled(target, source[0].length, source.length);
		}
		Color[][] imageOut = new Color[source.length][source[0].length];
		for (int i = 0; i < n; i++) {
			double alpha = 1.0 - ((double)i/n);
			imageOut = blend(source, target, alpha);
			setCanvas(imageOut);
			display(imageOut);
			StdDraw.pause(800);
		}

	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

