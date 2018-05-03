package com.proj.dencode.gui.crypt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.springframework.stereotype.Component;

import com.proj.dencode.gui.DencodeView;
import com.proj.gui.framework.AppContext;
import com.proj.gui.framework.component.BpPanel;
import com.tools.bp.crypt.Caesar;

@Component
public class CaesarPanel extends BpPanel {
	private static final long serialVersionUID = 1L;
	public JTextArea area;
	public JCheckBox decode;
	public JComboBox<String> select;
	public void init() {
		JLabel lurl = new JLabel("Caesar");
		area = new JTextArea(5,1);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		select=new JComboBox<String>();
		select.addItem("All");
		decode = new JCheckBox("decode");
		for (int i = 1; i < 26; i++) {
			select.addItem(i+"");
		}
		area.setLineWrap(true);
		decode.setSelected(true);
		
		layout.line(0).column(0).add(lurl, this);
		layout.line(0).column(1).alignRight().marginRight(75).add(select, this);
		layout.line(0).column(1).alignRight().add(decode, this);
		layout.line(1).column(0).colspan(2).both().add(scroll, this);
		
		decode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setText();
			}
		});
		select.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setText();
			}
		});
	}
	
	public void dencode(String text) {
		String result = "";
		String ofs = select.getSelectedItem().toString();
		switch (ofs) {
		case "All":
			for (int i = 1; i < 26; i++) {
				if(decode.isSelected()) {
					result += Caesar.decode(text,i);
				}else {
					result += Caesar.encode(text,i);
				}
				result+="\t\t offset: "+i+"\n";
			}
			break;
		default:
			if(decode.isSelected()) {
				result = Caesar.decode(text,Integer.parseInt(ofs));
			}else {
				result = Caesar.encode(text,Integer.parseInt(ofs));
			}
			break;
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
