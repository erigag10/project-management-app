package listview_components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import resources.Activities;
import resources.TaskProgress;
import resources.Users;
import saver_loader.DataResource;

import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;

import domain.ActivityController;

@SuppressWarnings("serial")
public class Activity_edit extends JFrame {

	private JPanel contentPane;
	private JTextField descriptionField;
	private JTextField startField;
	private JTextField endField;
	private JTextField activityLabelField;
	private JComboBox<String> progressField;
	private JSpinner budgetField;
	private JSpinner mostLikelyTimeField;
	private JSpinner optimisticTimeField;
	private JSpinner pessimisticTimeField;
	private JSpinner targetDateField;

	private ArrayList<String> dependencies = new ArrayList<String>();
	private ArrayList<String> members = new ArrayList<String>();

	public Activity_edit() {

		// Initialize JFrame Settings
		setTitle("EDITING");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 433, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		activityLabelField = new JTextField(DataResource.selectedActivity.getLabel());
		activityLabelField.setBounds(226, 23, 124, 20);
		contentPane.add(activityLabelField);
		activityLabelField.setColumns(10);
		
		// Create and add Description Field
		descriptionField = new JTextField(DataResource.selectedActivity.getDescription());
		descriptionField.setBounds(226, 120, 124, 20);
		descriptionField.setColumns(10);
		contentPane.add(descriptionField);
		
		// Create and add Progress Field
		progressField = new JComboBox<String>();
		for(TaskProgress state : TaskProgress.values())
			progressField.addItem(state.name());
		progressField.setSelectedItem(DataResource.selectedActivity.getProgress().name());
		progressField.setBounds(226, 145, 124, 20);
		contentPane.add(progressField);
		
		SpinnerModel spinModel = new SpinnerNumberModel(DataResource.selectedActivity.getBudget(), 0, 9999, 1);  
		budgetField = new JSpinner(spinModel);
		budgetField.setBounds(216, 380, 124, 20);
		contentPane.add(budgetField);
		
		SpinnerModel mSpinModel = new SpinnerNumberModel(DataResource.selectedActivity.getMostLikelyTime(), 0, 9999, 1);  
		mostLikelyTimeField = new JSpinner(mSpinModel);
		mostLikelyTimeField.setBounds(216, 400, 124, 20);
		contentPane.add(mostLikelyTimeField);
		
		SpinnerModel oSpinModel = new SpinnerNumberModel(DataResource.selectedActivity.getOptimisticTime(), 0, 9999, 1);  
		optimisticTimeField = new JSpinner(oSpinModel);
		optimisticTimeField.setBounds(216, 420, 124, 20);
		contentPane.add(optimisticTimeField);
		
		SpinnerModel pSpinModel = new SpinnerNumberModel(DataResource.selectedActivity.getPessimisticTime(), 0, 9999, 1);  
		pessimisticTimeField = new JSpinner(pSpinModel);
		pessimisticTimeField.setBounds(216, 440, 124, 20);
		contentPane.add(pessimisticTimeField);
		
		SpinnerModel tdSpinModel = new SpinnerNumberModel(DataResource.selectedActivity.getTargetDate(), 0, 9999, 1);  
		targetDateField = new JSpinner(tdSpinModel);
		targetDateField.setBounds(216, 460, 124, 20);
		contentPane.add(targetDateField);

		// Create and add all Labels
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(21, 123, 160, 14);
		contentPane.add(lblDescription);
		
		JLabel lblProgress = new JLabel("Progress");
		lblProgress.setBounds(21, 140, 160, 14);
		contentPane.add(lblProgress);
		
		JLabel lblBudget = new JLabel("Budget");
		lblBudget.setBounds(21, 380, 160, 14);
		contentPane.add(lblBudget);
		
		JLabel lblMostLikelyTime = new JLabel("Most Likely Time");
		lblMostLikelyTime.setBounds(21, 400, 160, 14);
		contentPane.add(lblMostLikelyTime);
		
		JLabel lblOptimisticTime = new JLabel("Optimistic Time");
		lblOptimisticTime.setBounds(21, 420, 160, 14);
		contentPane.add(lblOptimisticTime);
		
		JLabel lblPessimisticTime = new JLabel("Pessimistic Time");
		lblPessimisticTime.setBounds(21, 440, 160, 14);
		contentPane.add(lblPessimisticTime);
		
		JLabel lblTargetDate = new JLabel("Target Date");
		lblTargetDate.setBounds(21, 460, 160, 14);
		contentPane.add(lblTargetDate);

		JLabel lblStart = new JLabel("Start Date (DD-MM-YYYY)");
		lblStart.setBounds(21, 64, 170, 14);
		contentPane.add(lblStart);

		JLabel lblEnd = new JLabel("End Date (DD-MM-YYYY)");
		lblEnd.setBounds(21, 94, 170, 14);
		contentPane.add(lblEnd);

		JLabel lblLabel = new JLabel("Name");
		lblLabel.setBounds(21, 26, 160, 14);
		contentPane.add(lblLabel);

		JLabel lblDependencies = new JLabel("Dependencies");
		lblDependencies.setBounds(21, 157, 160, 14);
		contentPane.add(lblDependencies);

		JLabel lblResources = new JLabel("Resources");
		lblResources.setBounds(21, 267, 160, 14);
		contentPane.add(lblResources);

		// Create and add all text Fields
		String initialStart = DataResource.dateFormatter.format(DataResource.selectedActivity.getStartDate());
		startField = new JTextField(initialStart.toString());
		startField.setBounds(226, 61, 124, 20);
		contentPane.add(startField);
		startField.setColumns(10);

		String initialEnd = DataResource.dateFormatter.format(DataResource.selectedActivity.getEndDate());
		endField = new JTextField(initialEnd.toString());
		endField.setBounds(226, 91, 124, 20);
		contentPane.add(endField);
		endField.setColumns(10);

		// Create an array of the current Activities
		int activityCount = DataResource.selectedProject.getActivityList().size();
		Activities[] activityList = new Activities[activityCount];
		DataResource.selectedProject.getActivityList().toArray(activityList);

		// Create Selections from the list of Activities and their labels
		String[] selections = new String[activityCount];

		// Get the currently selected dependencies for this activity
		ArrayList<Activities> currentSelections = DataResource.selectedProject
				.getSetofDependencyActivities(DataResource.selectedActivity);

		for (int i = 0; i < activityCount; i++) {
			if (!DataResource.selectedActivity.getLabel().equals(activityList[i].getLabel()))
				selections[i] = activityList[i].getLabel();
		}

		// Create ScrollPane with list inside and add to Frame
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(226, 175, 101, 88);

		contentPane.add(scrollPane_1);

		// Create list with selections
		final JList<String> selectionList = new JList<String>(selections);
		selectionList.setBounds(232, 192, 95, 82);

		// Set the default selections to current dependent activities
		int[] selectedIndices = new int[currentSelections.size()];
		for (int i = 0; i < currentSelections.size(); i++) {
			for (int j = 0; j < activityList.length; j++) {
				if (currentSelections.get(i).getId() == activityList[j].getId())
					selectedIndices[i] = j;
			}
		}
		selectionList.setSelectedIndices(selectedIndices);
		contentPane.add(selectionList);

		// Add to viewport
		scrollPane_1.setViewportView(selectionList);

		ArrayList<Users> currentMembers = DataResource.selectedActivity.getMemberList();
		String[] memberNames = new String[DataResource.projectMembers.size()];

		for (int i = 0; i < DataResource.projectMembers.size(); i++) {
			memberNames[i] = DataResource.projectMembers.get(i).getName();
		}

		// Create ScrollPane with list inside and add to Frame
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_2.setBounds(226, 275, 101, 88);

		contentPane.add(scrollPane_2);

		final JList<String> memberList = new JList<String>(memberNames);
		memberList.setBounds(232, 192, 95, 82);

		int[] memberIndices = new int[currentMembers.size()];
		for (int i = 0; i < currentMembers.size(); i++) {
			for (int j = 0; j < DataResource.projectMembers.size(); j++) {
				if (currentMembers.get(i).getID() == DataResource.projectMembers.get(j).getID())
					memberIndices[i] = j;
			}
		}

		memberList.setSelectedIndices(memberIndices);
		contentPane.add(memberList);

		scrollPane_2.setViewportView(memberList);

		// Initialize and set Buttons
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(64, 480, 89, 23);
		contentPane.add(btnCancel);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(238, 480, 89, 23);
		contentPane.add(btnSave);

		JLabel lblDoYouWant = new JLabel("Do you want to delete?");
		lblDoYouWant.setBounds(64, 500, 200, 23);
		contentPane.add(lblDoYouWant);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(238, 500, 89, 23);
		contentPane.add(btnDelete);

		// Create the listListener for dependency choices
		selectionList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (e.getValueIsAdjusting()) {// This line prevents double
												// events

					dependencies = (ArrayList<String>) selectionList.getSelectedValuesList();

				}
			}
		});

		// Create the listListener for member choices
		memberList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {// This line prevents double
												// events
					members = (ArrayList<String>) memberList.getSelectedValuesList();
				}
			}
		});

		// Add and define ActionListeners to the buttons

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ActivityController.deleteActivity();
				disposeWindow();
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disposeWindow();
			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
					Date start = dateFormatter.parse(startField.getText());
					Date end = dateFormatter.parse(endField.getText());
					
					if (start.before(end) && !descriptionField.getText().isEmpty() && !startField.getText().isEmpty() && !endField.getText().isEmpty() && !activityLabelField.getText().isEmpty())
					{
						ActivityController.editActivity(descriptionField.getText(), startField.getText(), endField.getText(), activityLabelField.getText(), dependencies, members, progressField.getSelectedItem().toString(), (int)budgetField.getModel().getValue(), (int)mostLikelyTimeField.getModel().getValue(), (int)optimisticTimeField.getModel().getValue(), (int)pessimisticTimeField.getModel().getValue(), (int)targetDateField.getModel().getValue());
						disposeWindow();
					}
					else
					{
						if (start.after(end))
		                {
		                    JOptionPane.showMessageDialog(new JFrame(),
		                            "End date must be AFTER start date",
		                            "Incorrect Dates",
		                            JOptionPane.WARNING_MESSAGE);
		                }
		                else
		                {
		                    JOptionPane.showMessageDialog(new JFrame(),
		                            "Please Fill in all values correctly",
		                            "Values are incorrect format or missing",
		                            JOptionPane.WARNING_MESSAGE);
		                }
					}
					
				}
				catch (Exception exception)
				{
					JOptionPane.showMessageDialog(new JFrame(),
                            "Please Fill in all values correctly",
                            "Values are incorrect format or missing",
                            JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});

	}

	private void disposeWindow() {
		this.dispose();
	}

}