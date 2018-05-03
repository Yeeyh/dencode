package com.proj.dencode.gui.crypt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.springframework.stereotype.Component;

import com.proj.dencode.gui.DencodeView;
import com.proj.gui.framework.AppContext;
import com.proj.gui.framework.component.BpPanel;
import com.tools.bp.crypt.QWE;

@Component
public class QWEPanel extends BpPanel {
	private static final long serialVersionUID = 1L;
	public JTextArea area;
	public JCheckBox decode,reverse;
	public void init() {
		JLabel lurl = new JLabel("QWE");
		area = new JTextArea(5,1);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		reverse = new JCheckBox("reverse");
		decode = new JCheckBox("decode");
		area.setLineWrap(true);
		decode.setSelected(true);
		
		layout.line(0).column(0).add(lurl, this);
		layout.line(0).column(1).alignRight().marginRight(70).add(reverse, this);
		layout.line(0).column(1).alignRight().add(decode, this);
		layout.line(1).column(0).colspan(2).both().add(scroll, this);
		
		decode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setText();
			}
		});
		reverse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setText();
			}
		});
	}
	
	public void dencode(String text) {
		String result = "";
		if (reverse.isSelected()) {
			if(decode.isSelected()) {
				result = QWE.reverseDecode(text);
			}else {
				result = QWE.reverseEncode(text);
			}
		} else {
			if(decode.isSelected()) {
				result = QWE.decode(text);
			}else {
				result = QWE.encode(text);
			}
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
