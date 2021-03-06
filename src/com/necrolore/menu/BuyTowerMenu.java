package com.necrolore.menu;



import com.necrolore.Level;
import com.necrolore.actor.entity.Attribute;
import com.necrolore.actor.entity.tower.Tower;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class BuyTowerMenu extends TowerMenu {
	
	private Tower towers[]; // The towers in this menu
	
	/**
	 * Creates a menu for buying new towers
	 * 
	 * @param towers   The towers buyable in this menu
	 */
	public BuyTowerMenu(Tower... towers) {
		
		/* Get all the images and prices of the towers given */
			GreenfootImage images[] = new GreenfootImage[towers.length];
			int prices[] = new int[towers.length];
			for(int i=0;i<images.length;i++){
				
				images[i] = towers[i].getImage();
				prices[i] = (Integer) towers[i].getAttribute(Attribute.PRICE);
				
			}// End for(int i=0;i<images.length;i++)
			
			
		/* Create the menu from the images of towers */
			createMenu(images, Tower.WIDTH, Tower.HEIGHT, prices);
			
			
		/* Store given towers in class variable */
			this.towers = towers;
			
	}// End two-argument constructor for BuyTowerMenu

	public void act() {
		
		/* Check to see if the menu was clicked */
			if(Greenfoot.mousePressed(this)){
				
				/* Find the selected type of tower */
					int postion = Greenfoot.getMouseInfo().getY()-getY()+getImage().getHeight()/2;
					int selected = postion/Tower.HEIGHT-1;
				
				/* make sure a tower was selected */
					if(selected>=0){

						/* Check to see if there is enought gold to buy it */
							if(((Level)getWorld()).getGold()>=prices[selected]){
								
							/* Spend the gold */
								((Level)getWorld()).spendGold(prices[selected]);
							
								
							/* Create and place a tower of the selected type at the tower location position */
								((Level)getWorld()).addTower((Tower)towers[selected].clone(), getX(), getY());
								
							}
							
					}// End if(selected>=0)
					
				/* Remove the tower menu */
					getWorld().removeObject(this);
				
			}// End if(Greenfoot.mouseClicked(this))
		
	}// End method act

}// End class BuyTowerMenu
