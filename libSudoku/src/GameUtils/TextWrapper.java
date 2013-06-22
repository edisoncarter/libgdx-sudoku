package GameUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TextWrapper {
	public String content;
	public Vector2 position;
	int width;
	int height;
	
	public TextWrapper(String msg,Vector2 pos) {
		// TODO Auto-generated constructor stub
		content = msg;
		position = pos;
	}
	
	public TextWrapper() {
		// TODO Auto-generated constructor stub
	}

	public void Draw(SpriteBatch sp,BitmapFont fnt, Color c){
	       width=(int)fnt.getBounds(content).width; //Get the width of the text we draw using the current font
	       height=(int)fnt.getBounds(content).height; //Get the height of the text we draw using the current font
	       fnt.setColor(Color.WHITE);
	       fnt.draw(sp,content,position.x-width/2, // Get center value in x direction
	    		   position.y-height/2 //Get center value in y direction
	                );
	       
	       
	       
	   }
	
	public void Draw(SpriteBatch sp,BitmapFont fnt, Color c, float scale){
	       width=(int)fnt.getBounds(content).width; //Get the width of the text we draw using the current font
	       height=(int)fnt.getBounds(content).height; //Get the height of the text we draw using the current font
	       fnt.setColor(c);
	       fnt.setScale(scale);
	       fnt.draw(sp,content,position.x-width/2, // Get center value in x direction
	    		   position.y-height/2 //Get center value in y direction
	                );
	}
	
	public void Draw(SpriteBatch sp,BitmapFont fnt, Color c, float scaleX, float scaleY){
	       width=(int)fnt.getBounds(content).width; //Get the width of the text we draw using the current font
	       height=(int)fnt.getBounds(content).height; //Get the height of the text we draw using the current font
	       fnt.setColor(c);
	       fnt.setScale(scaleX, scaleY);
	       fnt.draw(sp,content,position.x-width/2, // Get center value in x direction
	    		   position.y-height/2 //Get center value in y direction
	                );
	}
	
	public void drawWithColor(SpriteBatch sp,BitmapFont fnt, int r, int g, int b) {
		
	}
}
