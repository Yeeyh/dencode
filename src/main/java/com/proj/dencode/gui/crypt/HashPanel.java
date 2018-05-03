package com.proj.dencode.gui.crypt;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.springframework.stereotype.Component;

import com.proj.dencode.gui.DencodeView;
import com.proj.gui.framework.AppContext;
import com.proj.gui.framework.component.BpPanel;
import com.tools.bp.crypt.MD5;
import com.tools.bp.crypt.SHA1;

@Component
public class HashPanel extends BpPanel {
	private static final long serialVersionUID = 1L;
	public JTextArea area;
	public JComboBox<String> select;
	public void init() {
		JLabel lurl = new JLabel("Hash");
		area = new JTextArea(5,1);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		area.setLineWrap(true);
		
		select=new JComboBox<String>(new String[] {"MD5-32","MD5-16","SHA1"});
		
		layout.line(0).column(0).add(lurl, this);
		layout.line(0).column(1).alignRight().add(select, this);
		layout.line(1).column(0).colspan(2).both().add(scroll, this);
		
		select.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
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
		String result = "";
		try {
			switch (select.getSelectedItem().toString()) {
			case "MD5-16":
				result = MD5.bit16(text);
				break;
			case "SHA1":
				result = SHA1.sha1(text);
				break;
			default:
				result = MD5.bit32(text);
				break;
			}
		} catch (Exception e) {
		}
		area.setText(result);
	}
}
