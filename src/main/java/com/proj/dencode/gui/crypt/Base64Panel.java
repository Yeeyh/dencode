package com.proj.dencode.gui.crypt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.springframework.stereotype.Component;

import com.proj.dencode.gui.DencodeView;
import com.proj.gui.framework.AppContext;
import com.proj.gui.framework.component.BpPanel;
import com.tools.bp.crypt.Base32;
import com.tools.bp.crypt.Base64;

@Component
public class Base64Panel extends BpPanel {
	private static final long serialVersionUID = 1L;
	public JTextArea area;
	public JCheckBox decode,b32,gbk;
	public String code = "UTF-8";
	public void init() {
		area = new JTextArea(5,1);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		b32 = new JCheckBox("32");
		gbk = new JCheckBox("gbk");
		decode = new JCheckBox("decode");
		decode.setSelected(true);
		
		area.setLineWrap(true);
		
		layout.line(0).column(0).add(new JLabel("Base64/32"), this);
		layout.line(0).column(1).alignRight().marginRight(120).add(b32, this);
		layout.line(0).column(1).alignRight().marginRight(70).add(gbk, this);
		layout.line(0).column(1).alignRight().add(decode, this);
		layout.line(1).column(0).colspan(2).both().add(scroll, this);
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setText();
			}
		};
		decode.addActionListener(listener);
		b32.addActionListener(listener);
		gbk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code = gbk.isSelected()?"GBK":"UTF-8";
				setText();
			}

		});
	}
	
	private void setText() {
		DencodeView view = (DencodeView) AppContext.getPanel(DencodeView.class);
		String text = view.text.getText();
		if(text.trim().isEmpty()) return;
		dencode(text);
	}
	public void dencode(String text) {
		try {
			if(b32.isSelected()) {
				base32dencode(text);
			}else {
				base64dencode(text);
			}
		} catch (Exception e) {
		}
	}
	private void base64dencode(String text) throws IOException {
		if(decode.isSelected()) {
			String decode = Base64.decode(text,code);
			area.setText(new String(decode));
		}else {
			String encode = Base64.encode(text,code);
			area.setText(encode);
		}
	}
	private void base32dencode(String text) throws IOException {
		if(decode.isSelected()) {
			String decode = Base32.decode(text,code);
			area.setText(decode);
		}else {
			String encode = Base32.encode(text,code);
			area.setText(encode);
		}
	}
}
