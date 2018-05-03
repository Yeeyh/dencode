package com.proj.dencode.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proj.dencode.gui.crypt.AsciiPanel;
import com.proj.dencode.gui.crypt.BaconPanel;
import com.proj.dencode.gui.crypt.Base64Panel;
import com.proj.dencode.gui.crypt.CaesarPanel;
import com.proj.dencode.gui.crypt.FencePanel;
import com.proj.dencode.gui.crypt.HashPanel;
import com.proj.dencode.gui.crypt.HexPanel;
import com.proj.dencode.gui.crypt.MorsePanel;
import com.proj.dencode.gui.crypt.QWEPanel;
import com.proj.dencode.gui.crypt.URLPanel;
import com.proj.dencode.gui.crypt.VigenerePanel;
import com.proj.gui.framework.annotation.Table;
import com.proj.gui.framework.component.BpPanel;

@Component("dencode")
@Table
public class DencodeView extends BpPanel {
	private static final long serialVersionUID = 1L;
	@Autowired
	private URLPanel urlpanel;
	@Autowired
	private Base64Panel base64panel;
	@Autowired
	private AsciiPanel asciipanel;
	@Autowired
	private HexPanel hexpanel;
	@Autowired
	private HashPanel md5panel;
	@Autowired
	private CaesarPanel caesarpanel;
	@Autowired
	private FencePanel fencepanel;
	@Autowired
	private MorsePanel morsepanel;
	@Autowired
	private QWEPanel qwepanel;
	@Autowired
	private VigenerePanel vigenerepanel;
	@Autowired
	private BaconPanel baconpanel;
	
	public JTextArea text;
	public void init() {
		text = new JTextArea();
		layout.line(0).column(0).alignLeft().marginLeft(5).add(new JLabel("Text"), this);
		layout.line(0).column(0).colspan(2).horizontal().marginLeft(45).alignLeft().add(text, this);
		
		JPanel p = new JPanel(new GridLayout(0, 2, 5, 5));
		JScrollPane s = new JScrollPane(p);
		s.getVerticalScrollBar().setUnitIncrement(20);//设置滚轮速度
		p.add(urlpanel);
		p.add(base64panel);
		p.add(md5panel);
		p.add(hexpanel);
		p.add(asciipanel);
		p.add(morsepanel);
		p.add(caesarpanel);
		p.add(fencepanel);
		p.add(qwepanel);
		p.add(vigenerepanel);
		p.add(baconpanel);
		
		layout.line(1).both().add(s,this);
	}
}
