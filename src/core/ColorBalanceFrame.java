package core;

import java.awt.Color;

import myutils.CanvasShell;
import myutils.Colors.RGB;
import myutils.Utils;

public class ColorBalanceFrame extends CanvasShell {
	private static final long serialVersionUID = 1L;

	private RGB[][] imageNew, imageOld;
	private int X, Y;

	public ColorBalanceFrame(int width, int height, int scale, String title, double nsPerTick,
			double nsPerRender) {
		super(width, height, scale, title, nsPerTick, nsPerRender);

	}

	private void balance(RGB[][] imageNew, RGB[][] imageOld) {
		imageOld = imageNew.clone();
		for (int y = 0; y < Y; y++)
			for (int x = 0; x < X; x++) {
				addRGB(imageNew[x][y], getRGB(imageOld, x - 1, y - 1));
				addRGB(imageNew[x][y], getRGB(imageOld, x, y - 1));
				addRGB(imageNew[x][y], getRGB(imageOld, x + 1, y - 1));

				addRGB(imageNew[x][y], getRGB(imageOld, x - 1, y));
				addRGB(imageNew[x][y], getRGB(imageOld, x + 1, y));

				addRGB(imageNew[x][y], getRGB(imageOld, x - 1, y + 1));
				addRGB(imageNew[x][y], getRGB(imageOld, x, y + 1));
				addRGB(imageNew[x][y], getRGB(imageOld, x + 1, y + 1));

				imageNew[x][y].r /= 9;
				imageNew[x][y].g /= 9;
				imageNew[x][y].b /= 9;
			}
	}

	private void addRGB(RGB dest, RGB source) {
		dest.r += source.r;
		dest.g += source.g;
		dest.b += source.b;
	}

	private RGB getRGB(RGB[][] image, int x, int y) {
		if ((x = x % X) < 0) {
			x += X;
		}
		if ((y = y % Y) < 0) {
			y += Y;
		}
		return image[x][y];
	}

	@Override
	protected void init() {
		setBackground(Color.BLACK);
		// pixels[WIDTH/2+HEIGHT/2*WIDTH]=Colors.get(1,0,0);
		X = WIDTH;
		Y = HEIGHT;
		imageNew = new RGB[X][Y];
		for (int y = 0; y < Y; y++)
			for (int x = 0; x < X; x++) {
				int r = Utils.random.nextInt(255);
				int g = Utils.random.nextInt(255);
				int b = Utils.random.nextInt(255);
				imageNew[x][y] = new RGB(r, g, b);
			}
	}

	@Override
	protected void myTick() {
		balance(imageNew, imageOld);
	}

	@Override
	protected void myRender() {		
		for (int y = 0; y < Y; y++)
			for (int x = 0; x < X; x++) {
				screen.add(x - cx, y - cy, imageNew[x][y].toInt());
			}

	}

	@Override
	protected void myDebugInfo() {

	}

	@Override
	protected void myKeyHandling() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void myMouseHandling() {
		// TODO Auto-generated method stub

	}

}
