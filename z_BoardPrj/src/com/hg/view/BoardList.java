package com.hg.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hg.view.frame.BoardListFrame;

public class BoardList extends JPanel {
	
	BoardListFrame bf = new BoardListFrame();

	public BoardList(JFrame userFrame) {
		userFrame.setTitle("°Ô½ÃÆÇ");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconImg = toolkit.getImage("Images/clipboard2.png");
		userFrame.setIconImage(iconImg);
		
		setLayout(new BorderLayout());
		
		add(bf.getTitlePanel(),"North");
		add(bf.getTablePanel(), "Center");
		add(bf.getBtnPanel(),"South");
		
		setVisible(true);
	}
 
}
