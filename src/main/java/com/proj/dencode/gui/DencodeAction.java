package com.proj.dencode.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.proj.dencode.gui.crypt.AsciiPanel;
import com.proj.dencode.gui.crypt.BaconPanel;
import com.proj.dencode.gui.crypt.Base64Panel;
import com.proj.dencode.gui.crypt.CaesarPanel;
import com.proj.dencode.gui.crypt.FencePanel;
import com.proj.dencode.gui.crypt.HexPanel;
import com.proj.dencode.gui.crypt.MorsePanel;
import com.proj.dencode.gui.crypt.QWEPanel;
import com.proj.dencode.gui.crypt.HashPanel;
import com.proj.dencode.gui.crypt.URLPanel;
import com.proj.dencode.gui.crypt.VigenerePanel;
import com.proj.gui.framework.component.BpController;

@Controller
public class DencodeAction implements BpController{
	@Autowired
	private DencodeView view;
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
	
	public void init() {
		view.text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = view.text.getText();
				base64panel.dencode(text);
				urlpanel.dencode(text);
				md5panel.dencode(text);
				hexpanel.dencode(text);
				asciipanel.dencode(text);
				caesarpanel.dencode(text);
				fencepanel.dencode(text);
				morsepanel.dencode(text);
				qwepanel.dencode(text);
				vigenerepanel.dencode(text);
				baconpanel.dencode(text);
			}
		});
	}

}
