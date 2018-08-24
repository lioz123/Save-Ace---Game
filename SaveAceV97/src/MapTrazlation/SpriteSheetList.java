package MapTrazlation;

import java.awt.image.BufferedImage;

import General.ImageLoader;
import General.SpriteSheet;

public class SpriteSheetList {
	public static SpriteSheet SaveTexture = new SpriteSheet(new ImageLoader().loadImage("/SaveButton.png"));
	public static SpriteSheet marinOffiserSprite = new SpriteSheet(new ImageLoader().loadImage("/MarineSoldierFull.png"));
	public static SpriteSheet Dungeon_Crawler_Sheet = new SpriteSheet(new ImageLoader().loadImage("/Dungeon_Crawler_Sheet.png"));
	public static SpriteSheet tiles= new SpriteSheet(new ImageLoader().loadImage("/tiles.jpg"));
	public static SpriteSheet singleBlockTexture = new SpriteSheet(new ImageLoader().loadImage("/SingleFloorTxture.jpg"));
	public static BufferedImage singleBlockImg = singleBlockTexture.grabImage(0, 0,400,400);
	public static SpriteSheet luffy =  new SpriteSheet(new ImageLoader().loadImage("/luffyCutPropFinish3.png"));
	public static SpriteSheet OpenButton = new SpriteSheet(new ImageLoader().loadImage("/Open-Button-300x150.png"));
	public static SpriteSheet lockSprite = new SpriteSheet(new ImageLoader().loadImage("/Ei-lock.svg.png"));
	public static SpriteSheet DownArraw = new SpriteSheet(new ImageLoader().loadImage("/downArraw.png"));
	public static SpriteSheet upDownArras = new SpriteSheet(new ImageLoader().loadImage("/upAndDownArrows.png"));
	public static SpriteSheet rightLeftArraws = new SpriteSheet(new ImageLoader().loadImage("/leftAndRight.png"));
	public static SpriteSheet runIcon = new SpriteSheet(new ImageLoader().loadImage("/Actions-system-run-icon.png"));
	public static SpriteSheet warcraft = new SpriteSheet(new ImageLoader().loadImage("/warcraft2.png"));
	public static SpriteSheet loading= new SpriteSheet(new ImageLoader().loadImage("/Loading.jpg"));
	public static SpriteSheet Simbols = new  SpriteSheet(new ImageLoader().loadImage("/simbolsSpriteSheet2.png"));
	public static SpriteSheet backGround = new SpriteSheet(new ImageLoader().loadImage("/BackGroundSprite.png"));
	public static SpriteSheet glowBlueBackground = new SpriteSheet(new ImageLoader().loadImage("/blue-circular-glow-wave-scifi-game-background-lighting-effect-abstract-88831363.jpg"));
	
	
}
