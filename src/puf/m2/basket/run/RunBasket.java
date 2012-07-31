/**
 * This is a Basket ball meeting project, it was build for Advanced Database project at PUF - MINF2010
 * Supervisor: Professor Charton Jérôme
 * Students: NGUYEN Huu Phat, LE Xuan Hoan
 * July 2012 
 */

package puf.m2.basket.run;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import puf.m2.basket.exception.DbException;
import puf.m2.basket.view.LoginView;
import puf.m2.basket.view.Utils;

public class RunBasket {

	public static void main(String[] args) throws DbException {
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Login");
				LoginView loginPanel = new LoginView(frame);
				Utils.createAndShowGUI(frame, loginPanel);
			}
		});
	}
}
