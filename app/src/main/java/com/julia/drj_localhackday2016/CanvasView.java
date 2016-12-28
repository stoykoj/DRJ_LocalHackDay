package com.julia.drj_localhackday2016;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.*;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class CanvasView extends View {
	private Canvas canvas;
	private Paint paint; private Paint white;
	private int textSize = 50;
	private ArrayList<Rect> button;
	private boolean buttonClicked;
	private int index;
	
	public CanvasView( Context context ) {
		super( context );
		
		this.paint = new Paint();
		this.white = new Paint();
		white.setColor( Color.WHITE );
		white.setTextAlign( Paint.Align.CENTER );
		
		button = new ArrayList<Rect>();
		index = -1;
	}
	
	@Override
	protected void onDraw( Canvas canvas ) {
		this.canvas = canvas;
		
		canvas.drawColor( Color.GRAY );
		
		drawBubble( 200, 200, "Andy", -12 );
		drawBubble( 400, 800, "Andy", 350 );
	}
	
	@Override
	public boolean onTouchEvent( MotionEvent event ) {
		// use array to store buttons
		if ( inBubble( (int) event.getX(), (int) event.getY() ) != -1 ) {
			buttonClicked = true;
			Log.d( "Button Clicked: ", "True" );
		} else {
			buttonClicked = false;
			Log.d( "Button Clicked: ", "False" );
		}
		
		return super.onTouchEvent( event );
	}
	
	private int inBubble( int x, int y ) {
		int bubbleIndex = -1;
		
		for ( int i = 0; i <= index; i++ ) {
			if ( button.get( i ).contains( x, y ) ) {
				bubbleIndex = i;
			}
		}
		
		return bubbleIndex;
	}
	
	private int getBubbleSize( int amount ) {
		int bubbleSize = 550;
		
		if ( amount >= 1 && amount <= 351 ) {
			bubbleSize = amount + 199;
		}
		
		return bubbleSize;
	}
	
	private int getTextSize( int radius ) {
		int textSize = 50;
		
		if ( radius >= 200 && radius <= 350 ) {
			textSize = 35;
		}
		
		return textSize;
	}
	
	private int getColour( int amount ) {
		int colour;
		
		if ( amount > 0 ) {
			colour = Color.RED;
		} else {
			colour = Color.BLACK;
		}
		
		return colour;
	}
	
	private void drawBubble( int x, int y, String name, int amount ) {
		Bitmap img = BitmapFactory.decodeResource( getResources(), R.drawable.profile_picture );
		int radius = getBubbleSize( Math.abs( amount ) ) / 2;
		button.add( new Rect( x - radius, y - radius, x + radius, y + radius ) );
		index++;
		
		paint.setColor( getColour( amount ) );
		
		canvas.drawCircle( x, y, radius + 5, paint );
		textSize = getTextSize( radius * 2 );
		white.setTextSize( textSize );
		canvas.drawText( name + " $" + amount, x, y + radius + textSize + 5, white );
		canvas.drawBitmap( img, null, button.get( index ), null );
	}
}
