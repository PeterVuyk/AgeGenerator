package ageGenerator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;

@SuppressWarnings("serial")
public class AgeGeneratorFrame extends JFrame
{
    private static final String FRAME_TITLE = "Age generator";
    private static final String AGE_OUTPUT_TITLE = "Results";
    private static final String CALCULATOR_EXPLANATION = "Add a name and birth date to calculate the age.";
    private static final String GENERATE_BUTTON = "Generate!";
    private static final String EMPTY_INPUT_MESSAGE = "Please fill in the empty fields and try it again.";
    private static final String AGE_RESULT_MESSAGE = "%s is %d years old.";
    
    JTextField nameInputField;
    JSpinner birthDaySpinner;
    JButton submitButton;
    JLabel generatorExplanation;
    JPanel explanationPanel, formInput, formOutput, generatorPanel;
    AgeGeneratorMenuBar menuBar;
    
    public AgeGeneratorFrame()
    {
	this.createFrame();
	this.addMenuBar();
	this.explanationView();
	this.inputForm();
	this.AgeOverView();
    }
    
    private void createFrame()
    {
	this.setSize(500, 320);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setTitle(FRAME_TITLE);
	this.generatorPanel = new JPanel();
	this.add(generatorPanel);	
    }
    
    private void addMenuBar()
    {
	menuBar = new AgeGeneratorMenuBar();
	setJMenuBar(menuBar);
    }
    
    private void explanationView()
    {
        generatorExplanation = new JLabel(CALCULATOR_EXPLANATION);
        this.explanationPanel = new JPanel();
	explanationPanel.add(generatorExplanation);
	this.generatorPanel.add(this.explanationPanel);
    }
    
    private void inputForm()
    {
	nameInputField = new JTextField(null, 15);
	nameInputField.requestFocus();

	Date date = new Date();
	birthDaySpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH));
	JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthDaySpinner, "dd/MM/yyyy");
	birthDaySpinner.setEditor(dateEditor);
	
	Dimension day = birthDaySpinner.getPreferredSize();
	day.width += 20;
	birthDaySpinner.setPreferredSize(day);
	
	submitButton = new JButton(GENERATE_BUTTON);
	SubmitButtonListener SubmitButtonListener = new SubmitButtonListener();
	submitButton.addActionListener(SubmitButtonListener);
	
	this.formInput = new JPanel();
	formInput.add(nameInputField);
	formInput.add(birthDaySpinner);
	formInput.add(submitButton);
	this.generatorPanel.add(this.formInput);
    }
    
    private void AgeOverView()
    {
	this.formOutput = new JPanel();
        this.formOutput.setPreferredSize(new Dimension(450,200));
	this.formOutput.setLayout(new BoxLayout(formOutput, BoxLayout.PAGE_AXIS));
	this.formOutput.add(Box.createHorizontalGlue());

        Border outputBorder = BorderFactory.createTitledBorder(AGE_OUTPUT_TITLE);
        this.formOutput.setBorder(outputBorder);
	this.generatorPanel.add(this.formOutput);
    }

    private class SubmitButtonListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e) {
	    if (getName().isEmpty()) {
		JOptionPane.showMessageDialog(
			AgeGeneratorFrame.this,
			EMPTY_INPUT_MESSAGE
		);
		return;
	    }
	    
	    JLabel result = new JLabel(String.format(AGE_RESULT_MESSAGE, getName(), getAge()));

	    formOutput.add(result);
	    AgeGeneratorFrame.this.validate();
	}

	private int getAge()
	{
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(getDate());
	    Calendar today = Calendar.getInstance();
	    int age = today.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
	    
	    if (today.get(Calendar.DAY_OF_YEAR) < calendar.get(Calendar.DAY_OF_YEAR)) {
		age--;
	    }
	    
	    return age;
	}

	private Date getDate()
	{
	    Object birthDate = birthDaySpinner.getValue();
	    return (Date) birthDate;
	}
	
	private String getName()
	{
	    return nameInputField.getText();
	}
    }
}
