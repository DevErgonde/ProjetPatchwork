package src.fr.umlv.patchwork;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class Graphical {
	
	private Color couleur_fond = Color.BLACK;
	private ScreenInfo screenInfo;
	private float width;
	private float height;
	private Event event;
	private static int SCALE = 50;
    
  /**
	 * METHODES GRAPHIQUES - BASIQUES
	 **/
	void getInfos(ApplicationContext context){
		// On récupère les dimensions de l'écran
		screenInfo = context.getScreenInfo();
		width = screenInfo.getWidth();
		height = screenInfo.getHeight();
	}
    
  void drawRectangleByCenter(Graphics2D graphics, Color color, float x, float y, float width, float height) {

		var rectangle = new Rectangle2D.Float();
		graphics.setColor(color);
		rectangle.setFrame(x - width/2, y - height/2, width, height);
		graphics.fill(rectangle);
        
  }

	void drawRectangleByNW(Graphics2D graphics, Color color, float x, float y, float width, float height) {

		var rectangle = new Rectangle2D.Float();
		graphics.setColor(color);
		rectangle.setFrame(x , y, width, height);
		graphics.fill(rectangle);
        
  }
    
  void drawPoint(Graphics2D graphics, Color color, float x, float y, float diametre) {
        
        graphics.setColor(Color.MAGENTA);
        var ellipse = new Ellipse2D.Float(x - diametre/2, y - diametre/2, diametre, diametre);
        graphics.fill(ellipse);
  }

	void drawMessage(Graphics2D graphics, String text, Color color, float x, float y, int size_text) throws Exception{
		/*Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(fontPath));
		font.deriveFont(size);*/
		graphics.setColor(color);
		graphics.setFont(new Font("Arial", Font.PLAIN, size_text));
		graphics.drawString(text, x, y);
	}
	
	void drawMessageBox(Graphics2D graphics, String text, Color text_color, Color border_color, Color back_color, float x, float y, float width_box, float height_box, int size_text) throws Exception{
		Font font = new Font("Arial", Font.PLAIN, size_text);
		int textWidth = graphics.getFontMetrics(font).stringWidth(text);
		float textX = x + (width_box - (float) textWidth) / 2;
		float textY = y + (height_box + (float) size_text) / 2;
		drawRectangleByNW(graphics, border_color, x, y, width_box, height_box);
		drawRectangleByNW(graphics, back_color, x + 1, y + 1, width_box - 2, height_box - 2);
		drawMessage(graphics, text, text_color, textX, textY, size_text);
	}

	void clearWindowOutFrame(ApplicationContext context){
		context.renderFrame(graphics -> {
			graphics.setColor(couleur_fond);
			graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
		});
	}

	void clearWindowInFrame(Graphics2D graphics){
		graphics.setColor(couleur_fond);
		graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
	}
	
	/* Méthodes pour le jeu */
	
	public void drawMenu(ApplicationContext context) {
		context.renderFrame(graphics -> {
			try {
				drawMessageBox(graphics, "Patchwork", Color.WHITE, Color.GRAY, Color.GRAY, 0, 0, width, height/3, (int) (width/15));
				drawMessageBox(graphics, "Nouvelle partie", Color.WHITE, Color.WHITE, Color.GRAY, width/4, height/3, width/2, height/4, (int) (width/50));
				drawMessageBox(graphics, "Continuer", Color.WHITE, Color.WHITE, Color.GRAY, width/4, 2*height/3, width/2, height/4, (int) (width/50));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public void drawBoard(ApplicationContext context, Game game) {
		context.renderFrame(graphics -> {
			try {
				int i = 0, j = 0, n = 0;
				for (var elem : game.chronoBoard().boardElements()) {
					drawRectangleByNW(graphics, Color.WHITE, i * SCALE, j * SCALE, SCALE, SCALE);
					drawRectangleByNW(graphics, Color.BLACK, i * SCALE + 1, j * SCALE + 1, SCALE - 2, SCALE-  2);
					switch(elem) {
						case BUTTON:
							drawButton(graphics, i * SCALE, j * SCALE);
							break;
						case LEATHER:
							drawLeather(graphics, i * SCALE, j * SCALE);
							break;
						case VOID:
							break;
					}
					if (game.p1().position == n && game.p2().position == n) {
						drawPlayer(graphics, i * SCALE, j * SCALE, 3);
					}
					else {
						if (game.p1().position == n)
							drawPlayer(graphics, i * SCALE, j * SCALE, 1);
						if (game.p2().position == n)
							drawPlayer(graphics, i * SCALE, j * SCALE, 2); 
					}
					i++;
					n++;
					if (i%7 == 0) {
						j++;
						i = 0;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	void drawButton(Graphics2D graphics, float x, float y) {
		BufferedImage image;
		String path = "../../../../assets/image/button.png";
		try{
			image = ImageIO.read(getClass().getResourceAsStream(path));
			graphics.drawImage(image, (int)x + 1, (int)y + 1, SCALE - 2, SCALE - 2, null);
		}catch(IOException ioe) {
			System.err.println("Image non trouvée : " + ioe);
		}
	}
	
	void drawLeather(Graphics2D graphics, float x, float y) {
		BufferedImage image;
		String path = "../../../../assets/image/leather.png";
		try{
			image = ImageIO.read(getClass().getResourceAsStream(path));
			graphics.drawImage(image, (int)x + 6, (int)y + 6, SCALE - 12, SCALE - 12, null);
		}catch(IOException ioe) {
			System.err.println("Image non trouvée : " + ioe);
		}
	}
	
	void drawPlayer(Graphics2D graphics, float x, float y, int pawn_type) {
		try{
			switch(pawn_type) {
				case 1:
					graphics.drawImage(ImageIO.read(getClass().getResourceAsStream("../../../../assets/image/redpawn.png")), (int)x + 1, (int)y + 1, SCALE - 2, SCALE - 2, null);
					break;
				case 2:
					graphics.drawImage(ImageIO.read(getClass().getResourceAsStream("../../../../assets/image/greenpawn.png")), (int)x + 1, (int)y + 1, SCALE - 2, SCALE - 2, null);
					break;
				default:
					graphics.drawImage(ImageIO.read(getClass().getResourceAsStream("../../../../assets/image/redpawn.png")), (int)x + 1, (int)y + 1, (SCALE - 2)/2, (SCALE - 2)/2, null);
					graphics.drawImage(ImageIO.read(getClass().getResourceAsStream("../../../../assets/image/greenpawn.png")), (int)(x + 1 + (SCALE - 2)/2), (int)y + 1, (SCALE - 2)/2, (SCALE - 2)/2, null);
					break;
			}
		}catch(IOException ioe) {
			System.err.println("Image non trouvée : " + ioe);
		}
	}
	
  public static void main(String[] args) throws Exception{
  	Application.run(Color.GRAY, context -> {
  		try{
  			Game game = new Game("Martin", "Mathis", 2, false);
  			var graph = new Graphical();
    		graph.getInfos(context);
    		graph.drawBoard(context, game);
  		}catch(Exception e) {
  			System.err.println(e.getMessage());
  		}
  	});
  }
}