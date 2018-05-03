package com.proj.dencode.gui.crypt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.springframework.stereotype.Component;

import com.proj.dencode.gui.DencodeView;
import com.proj.gui.framework.AppContext;
import com.proj.gui.framework.component.BpPanel;
import com.tools.bp.crypt.Vigenere;

@Component
public class VigenerePanel extends BpPanel {
	private static final long serialVersionUID = 1L;
	public JTextArea area;
	public JCheckBox decode;
	public JTextField pass;
	public void init() {
		JLabel lurl = new JLabel("Vigenere");
		area = new JTextArea(5,1);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pass = new JTextField(8);
		pass.setToolTipText("password");
		decode = new JCheckBox("decode");
		area.setLineWrap(true);
		decode.setSelected(true);
		
		layout.line(0).column(0).add(lurl, this);
		layout.line(0).column(1).alignRight().marginRight(70).add(pass, this);
		layout.line(0).column(1).alignRight().add(decode, this);
		layout.line(1).column(0).colspan(2).both().add(scroll, this);
		
		decode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setText();
			}
		});
		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				setText();
			}
		});
	}
	
	public void dencode(String text) {
		String result = "";
		String pwd = pass.getText();
		if(decode.isSelected()) {
			result = Vigenere.decode(text,pwd);
		}else {
			result = Vigenere.encode(text,pwd);
		}
		area.setText(result);
	}
	private void setText() {
		DencodeView view = (DencodeView) AppContext.getPanel(DencodeView.class);
		String text = view.text.getText();
		if(text.trim().isEmpty()) return;
		dencode(text);
	}
}
