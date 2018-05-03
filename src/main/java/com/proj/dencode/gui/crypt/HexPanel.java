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
import com.tools.bp.crypt.HexConver;

@Component
public class HexPanel extends BpPanel {
	private static final long serialVersionUID = 1L;
	public JTextArea area;
	public JCheckBox decode;
	public JComboBox<String> select;
	public void init() {
		JLabel lurl = new JLabel("Hex");
		area = new JTextArea(5,1);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		decode = new JCheckBox("decode");
		area.setLineWrap(true);
		decode.setSelected(true);
		
		select=new JComboBox<String>(new String[] {"Hex","Binary","Octal","H2B"});
		
		layout.line(0).column(0).add(lurl, this);
		layout.line(0).column(1).alignRight().marginRight(70).add(select, this);
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
	private void setText() {
		DencodeView view = (DencodeView) AppContext.getPanel(DencodeView.class);
		String text = view.text.getText();
		if(text.trim().isEmpty()) return;
		dencode(text);
	}
	public void dencode(String text) {
		try {
			String result = "";
			if(decode.isSelected()) {
				switch (select.getSelectedItem().toString()) {
				case "Binary":
					result = HexConver.binaryTo(text)+"";
					break;
				case "Octal":
					result = HexConver.octalTo(text)+"";
					break;
				case "H2B":
					result = HexConver.binaryToHex(text);
					break;
				default:
					result = HexConver.hexTo(text);
					break;
				}
			}else {
				switch (select.getSelectedItem().toString()) {
				case "Binary":
					result = HexConver.toBinary(text);
					break;
				case "Octal":
					result = HexConver.toOctal(text);
					break;
				case "H2B":
					result = HexConver.hexToBinary(text);
					break;
				default:
					result = HexConver.toHex(text);
					break;
				}
			}
			area.setText(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
