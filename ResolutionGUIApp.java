package TeamProjectWithDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main GUI app for the LLC resolution generator
 *
 * @author Daniel Musser and Brittany Pruneau
 */
public class ResolutionGUIApp
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setTitle("Resolution Maker");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(925, 600);

		JPanel upperPanel = new JPanel(new BorderLayout());
		JPanel inputsPanel = new JPanel(new GridLayout(5, 1, 0, 0));

		JPanel titlePanel = new JPanel(new BorderLayout());
		JButton loadSavedResButton = new JButton("Load Saved Resolution");
		loadSavedResButton.setFocusPainted(false);
		JButton aboutButton = new JButton("About This App");

		JLabel titleLabel = new JLabel("LLC Resolution Maker");
		titleLabel.setFont(new Font("Helvetica", Font.PLAIN, 32));
		titleLabel.setPreferredSize(new Dimension(580, 40));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);

		titlePanel.add(loadSavedResButton, BorderLayout.WEST);
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		titlePanel.add(aboutButton, BorderLayout.EAST);
		titlePanel.setBorder(BorderFactory.createRaisedBevelBorder());

		JPanel firstInputPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 2));
		JTextField llcNameField = new JTextField(50);
		llcNameField.setText("Enter LLC Name Here");
		JLabel managementQuestionLabel = new JLabel("Is your LLC managed by its members or managers?");
		String[] managementChoices = new String[]
		{ ManagedBy.Members.toString(), ManagedBy.Managers.toString() };
		JComboBox managementSelectionBox = new JComboBox(managementChoices);

		firstInputPanel.add(llcNameField);
		firstInputPanel.add(managementQuestionLabel);
		firstInputPanel.add(managementSelectionBox);

		JPanel secondInputPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 0));
		JTextField memberField1 = new JTextField("Enter 1st LLC Member or Manager Here", 50);
		JCheckBox isManagerCheckBox1 = new JCheckBox("Manager?");
		JCheckBox isOwnerCheckBox1 = new JCheckBox("Owner?");
		JTextField percentageOfOwnership1 = new JTextField("Enter Percentage of Ownership", 20);

		secondInputPanel.add(memberField1);
		secondInputPanel.add(isManagerCheckBox1);
		secondInputPanel.add(isOwnerCheckBox1);
		secondInputPanel.add(percentageOfOwnership1);

		JPanel thirdInputPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 0));
		JTextField memberField2 = new JTextField("Enter 2nd LLC Member or Manager Here", 50);
		JCheckBox isManagerCheckBox2 = new JCheckBox("Manager?");
		JCheckBox isOwnerCheckBox2 = new JCheckBox("Owner?");
		JTextField percentageOfOwnership2 = new JTextField("Enter Percentage of Ownership", 20);

		thirdInputPanel.add(memberField2);
		thirdInputPanel.add(isManagerCheckBox2);
		thirdInputPanel.add(isOwnerCheckBox2);
		thirdInputPanel.add(percentageOfOwnership2);

		JPanel fourthInputPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 0));
		JTextField memberField3 = new JTextField("Enter 3rd LLC Member or Manager Here", 50);
		JCheckBox isManagerCheckBox3 = new JCheckBox("Manager?");
		JCheckBox isOwnerCheckBox3 = new JCheckBox("Owner?");
		JTextField percentageOfOwnership3 = new JTextField("Enter Percentage of Ownership", 20);

		fourthInputPanel.add(memberField3);
		fourthInputPanel.add(isManagerCheckBox3);
		fourthInputPanel.add(isOwnerCheckBox3);
		fourthInputPanel.add(percentageOfOwnership3);

		JPanel fifthInputPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 0));
		JTextField memberField4 = new JTextField("Enter 4th LLC Member or Manager Here", 50);
		JCheckBox isManagerCheckBox4 = new JCheckBox("Manager?");
		JCheckBox isOwnerCheckBox4 = new JCheckBox("Owner?");
		JTextField percentageOfOwnership4 = new JTextField("Enter Percentage of Ownership", 20);

		fifthInputPanel.add(memberField4);
		fifthInputPanel.add(isManagerCheckBox4);
		fifthInputPanel.add(isOwnerCheckBox4);
		fifthInputPanel.add(percentageOfOwnership4);

		JTextArea resolutionDisplayArea = new JTextArea("Resolution will appear here when generated");
		JScrollPane scroller = new JScrollPane(resolutionDisplayArea);
		scroller.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));

		JPanel bottomButtonPanel = new JPanel(new BorderLayout());
		JButton saveButton = new JButton("Save Resolution as PDF");
		saveButton.setPreferredSize(new Dimension(360, 25));
		JButton generateButton = new JButton("Generate Resolution and Save");
		generateButton.setPreferredSize(new Dimension(550, 25));

		bottomButtonPanel.add(saveButton, BorderLayout.WEST);
		bottomButtonPanel.add(generateButton, BorderLayout.EAST);

		upperPanel.add(titlePanel, BorderLayout.NORTH);
		inputsPanel.add(firstInputPanel);
		inputsPanel.add(secondInputPanel);
		inputsPanel.add(thirdInputPanel);
		inputsPanel.add(fourthInputPanel);
		inputsPanel.add(fifthInputPanel);
		upperPanel.add(inputsPanel, BorderLayout.SOUTH);
		frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(scroller, BorderLayout.CENTER);
		frame.add(bottomButtonPanel, BorderLayout.SOUTH);

		frame.setVisible(true);

		JTextField[] memberNameFields = new JTextField[]
		{ memberField1, memberField2, memberField3, memberField4 };
		JCheckBox[] ownerCheckBoxes = new JCheckBox[]
		{ isOwnerCheckBox1, isOwnerCheckBox2, isOwnerCheckBox3, isOwnerCheckBox4 };
		JCheckBox[] managerCheckBoxes = new JCheckBox[]
		{ isManagerCheckBox1, isManagerCheckBox2, isManagerCheckBox3, isManagerCheckBox4 };
		JTextField[] ownershipPercentages = new JTextField[]
		{ percentageOfOwnership1, percentageOfOwnership2, percentageOfOwnership3, percentageOfOwnership4 };

		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String resolutionString = resolutionDisplayArea.getText();
				JFileChooser fileChooser = new JFileChooser();
				File fileToCreate = null;
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int response = fileChooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION)
				{
					fileToCreate = fileChooser.getSelectedFile();
					try (FileWriter writer = new FileWriter(fileToCreate.getAbsolutePath() + ".txt"))
					{
						FileHandler.saveAsPDF(resolutionString, llcNameField.getText(),
								fileToCreate.getAbsolutePath() + ".pdf");
						writer.write(resolutionString);
					} catch (IOException ioException)
					{
						ioException.printStackTrace();
					}
				}
			}
		});

		generateButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if (llcNameField.getText().equals("") || llcNameField.getText().equals("Enter LLC Name Here"))
				{
					JOptionPane.showMessageDialog(new JFrame(), "You must enter a name for the LLC");
					return;
				}

				String nameOfLLC = llcNameField.getText();
				List<Member> membersAndManagers = new ArrayList<>();
				ManagedBy selectedManagementStyle;
				boolean managerRequired = false;

				if (managementSelectionBox.getSelectedIndex() == 1)
				{
					selectedManagementStyle = ManagedBy.Managers;
					managerRequired = true;
				} else
				{
					selectedManagementStyle = ManagedBy.Members;
				}

				for (int i = 0; i < 3; i++)
				{
					String memberName = memberNameFields[i].getText();

					if (memberName.isEmpty() || memberName.contains("LLC Member or Manager Here"))
					{
						if (i == 0)
						{
							JOptionPane.showMessageDialog(new JFrame(), "You must enter at least one member's name");
							return;
						}
						continue;
					}

					boolean isOwnerOfLLC = ownerCheckBoxes[i].isSelected();
					boolean isManagerOfLLC = managerCheckBoxes[i].isSelected();
					int ownership = 0;
					if (isOwnerOfLLC)
					{
						try
						{
							ownership = Integer.parseInt(ownershipPercentages[i].getText());
						} catch (NumberFormatException e)
						{
							JOptionPane.showMessageDialog(new JFrame(),
									"You must enter the ownership percentage for each member");
							return;
						}

					}
					if (!isOwnerOfLLC && !isManagerOfLLC)
					{
						JOptionPane.showMessageDialog(new JFrame(), "One of your members does not have\n"
								+ "either of the check boxes selected,\n" + "which means that they are not a member");
						return;
					}
					if (isManagerOfLLC)
					{
						managerRequired = false;
					}

					membersAndManagers.add(new Member(memberName, isManagerOfLLC, isOwnerOfLLC, ownership));
				}
				
				if (managerRequired)
				{
					JOptionPane.showMessageDialog(new JFrame(), "You must select at least one member as a manager");
					return;
				}

				Company company = new Company(nameOfLLC, membersAndManagers, selectedManagementStyle);
				resolutionDisplayArea.setText(new ResolutionGenerator(company).generateString());

				FileHandler.checkForSaveDirectory();
				FileHandler.serializeCompany("savedCompanies/" + nameOfLLC + ".ser", company);
			}
		});

		loadSavedResButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				FileHandler.checkForSaveDirectory();
				JFileChooser fileChooser = new JFileChooser("savedCompanies");
				File chosenFile = null;

				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int response = fileChooser.showOpenDialog(null);

				if (response == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().exists())
				{
					chosenFile = fileChooser.getSelectedFile();

					Company savedCompany = FileHandler.deserializeCompany(chosenFile);

					llcNameField.setText(savedCompany.getCompanyName());

					List<Member> members = savedCompany.getMembers();

					if (savedCompany.getManagementStyle().equals(ManagedBy.Members))
						managementSelectionBox.setSelectedIndex(0);
					else
						managementSelectionBox.setSelectedIndex(1);

					int numberOfMembers = members.size();

					for (int i = 0; i < 4; i++)
					{
						if (i < numberOfMembers)
						{
							memberNameFields[i].setText(members.get(i).getName());
							ownerCheckBoxes[i].setSelected(members.get(i).isOwner());
							managerCheckBoxes[i].setSelected(members.get(i).isManager());
							ownershipPercentages[i].setText("" + members.get(i).getOwnershipPercentage());
						} else
						{
							memberNameFields[i].setText("");
							ownerCheckBoxes[i].setSelected(false);
							managerCheckBoxes[i].setSelected(false);
							ownershipPercentages[i].setText("");
						}
					}
				}

			}
		});
	}
}
