package gui.panels;

import gui.gui.GUIGameObject;
import gui.types.InfoType;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ObjectInfoPanel extends JPanel
{

	private static GUIGameObject uiObject;
	private static JTextArea infoTextArea;

	public ObjectInfoPanel(int x, int y, int width, int height)
	{
		this.setBounds(x, y, width, height);
		this.setLayout(null);

		infoTextArea = new JTextArea();
		infoTextArea.setBounds(0, 0, width, height);
		infoTextArea.setOpaque(true);
		infoTextArea.setBackground(new Color(0, 11, 38));

		infoTextArea.setForeground(Color.WHITE);

		this.add(infoTextArea);
		infoTextArea.setEditable(false);
		infoTextArea.setAutoscrolls(true);

		infoTextArea
				.setBorder(BorderFactory.createTitledBorder("Unit Details"));

		this.setVisible(true);
	}

	public static void setUIObject(GUIGameObject uiObject)
	{
		ObjectInfoPanel.uiObject = uiObject;
		showInformation();
	}

	public void showError(String text)
	{
		infoTextArea.setText("");
		infoTextArea.setForeground(Color.red);
		infoTextArea.setText(text);
	}

	private static void showInformation()
	{
		infoTextArea.setText("");
		infoTextArea.setForeground(Color.WHITE);
		for (InfoType infoType : uiObject.getInfoTypeToObjectData().keySet())
		{
			infoTextArea.setText(infoTextArea.getText()
					+ infoType
					+ ": "
					+ uiObject.getInfoTypeToObjectData().get(infoType)
							.getView() + "\n");

		}
	}

	public void showDoneActions(ArrayList<String> doneActions)
	{
		infoTextArea.setText("");
		for (int i = 0; i < doneActions.size(); i++)
		{
			infoTextArea.setText(infoTextArea.getText() + "\n" + doneActions.get(i));
		}
		
	}

}
