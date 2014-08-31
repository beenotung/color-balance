package myutils;

public class Colors {
	public static class RGB {
		public int r, g, b;

		public RGB() {
			r = g = b = 0;
		}

		public RGB(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}

		public RGB(double r, double g, double b) {
			this.r = (int) Math.round(r * 255);
			this.g = (int) Math.round(g * 255);
			this.b = (int) Math.round(b * 255);
		}

		public void add(RGB rgb) {
			r += rgb.r;
			g += rgb.g;
			b += rgb.b;
		}

		public void subtract(RGB rgb) {
			r -= rgb.r;
			g -= rgb.g;
			b -= rgb.b;
		}

		public void multiple(RGB rgb) {
			r *= rgb.r;
			g *= rgb.g;
			b *= rgb.b;
		}

		public void multiple(double rate) {
			r *= rate;
			g *= rate;
			b *= rate;
		}

		public void divide(RGB rgb) {
			r /= rgb.r;
			g /= rgb.g;
			b /= rgb.b;
		}

		public void divide(double rate) {
			r /= rate;
			g /= rate;
			b /= rate;
		}

		public int toInt() {
			return get(r * 255, g * 255, b * 255);
		}

	}

	private static int get(double d) {
		return (int) Math.round(d * 255) & 255;
	}

	public static int get(double r, double g, double b) {
		// System.out.println("r:"+r+", g:"+g+", b:"+b+", res:"+(int)(get(r) <<
		// 16 | get(g) << 8 | get(b)));
		return get(r) << 16 | get(g) << 8 | get(b);
	}

	public static void decode(int rawCode, RGB rgbCode) {
		rgbCode.r = (rawCode >> 16) & 0xFF;
		rgbCode.g = (rawCode >> 8) & 0xFF;
		rgbCode.b = rawCode & 0xFF;
	}

	public static RGB decode(int rawCode) {
		RGB rgbCode = new RGB();
		rgbCode.r = (rawCode >> 16) & 0xFF;
		rgbCode.g = (rawCode >> 8) & 0xFF;
		rgbCode.b = rawCode & 0xFF;
		return rgbCode;
	}
}
