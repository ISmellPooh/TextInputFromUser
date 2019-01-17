package sla;

import javafx.scene.control.*;
//import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
//import java.util.ArrayList;

public class Controller {

    // these fields identify each GUI control drawn in View
    public ChoiceBox<String> choiceBox;

    //public TextField textField;

    //public Label correctLabel;
    //public Label incorrectLabel;

    public Label feedbackLabel;

    public Label firstLabel;

    public RadioButton firstRadioButton;
    public RadioButton secondRadioButton;
    public RadioButton thirdRadioButton;

    /*boolean isFirstRBSelected;
    boolean isSecondRBSelected;
    boolean isThirdRBSelected;*/

    String correctAnswer;

    String[][] lacrosseQuestions;
    String[][] footballQuestions;
    int currentQuestion;
    String[][] questionsBeingAsked;
    int numberOfCorrectAnswers;

    //public Label firstFLabel;
    /*public RadioButton firstFRadioButton;
    public RadioButton secondFRadioButton;
    public RadioButton thirdFRadioButton;

    boolean isFirstFRBSelected;
    boolean isSecondFRBSelected;
    boolean isThirdFRBSelected;

    //public Label firstLLabel;
    public RadioButton firstLRadioButton;
    public RadioButton secondLRadioButton;
    public RadioButton thirdLRadioButton;

    boolean isFirstLRBSelected;
    boolean isSecondLRBSelected;
    boolean isThirdLRBSelected;*/

//    public Label topLabel;
//    public TextField topTextField;
//    public ListView<Label> sideListView;
//    public TextField bottomTextField;

    // the Model field keeps track of previously-saved and updated text in GUI that needs to be saved
    private Model model;

    public void initialize() {
        System.out.println("Controller initialize");

        footballQuestions = new String[3][5];
        footballQuestions[0][0] = "A football is commonly called a pig skin, but what animal leather is a football really made of?";
        footballQuestions[0][1] = "Cow";
        footballQuestions[0][2] = "Horse";
        footballQuestions[0][3] = "Sheep";
        footballQuestions[0][4] = "1";
        footballQuestions[1][0] = "What number did the Eagles quarterback with the best stats wear?";
        footballQuestions[1][1] = "11";
        footballQuestions[1][2] = "32";
        footballQuestions[1][3] = "7";
        footballQuestions[1][4] = "3";
        footballQuestions[2][0] = "Which state has the most NFL teams?";
        footballQuestions[2][1] = "Pennsylvania";
        footballQuestions[2][2] = "California";
        footballQuestions[2][3] = "Texas";
        footballQuestions[2][4] = "2";
        currentQuestion = 0;
        numberOfCorrectAnswers = 0;

        lacrosseQuestions = new String[3][5];
        lacrosseQuestions[0][0] = "Lacrosse was originally played by which group of people?";
        lacrosseQuestions[0][1] = "British Nobles";
        lacrosseQuestions[0][2] = "Native Americans";
        lacrosseQuestions[0][3] = "Children in Spain";
        lacrosseQuestions[0][4] = "2";
        lacrosseQuestions[1][0] = "A lacrosse ball is about the same size and weight as what piece of hockey equipment?";
        lacrosseQuestions[1][1] = "Puck";
        lacrosseQuestions[1][2] = "Helmet";
        lacrosseQuestions[1][3] = "Stick";
        lacrosseQuestions[1][4] = "1";
        lacrosseQuestions[2][0] = "Lacrosse was named after the French term meaning what?";
        lacrosseQuestions[2][1] = "Their ball";
        lacrosseQuestions[2][2] = "A helmet";
        lacrosseQuestions[2][3] = "The stick";
        lacrosseQuestions[2][4] = "3";
        currentQuestion = 0;
        numberOfCorrectAnswers = 0;

        choiceBox.setOnAction(e -> sportChoiceBoxPressed());

        ToggleGroup radioGroup = new ToggleGroup();

        firstRadioButton.setToggleGroup(radioGroup);
        secondRadioButton.setToggleGroup(radioGroup);
        thirdRadioButton.setToggleGroup(radioGroup);

        //firstFLabel.setText("");

        /*firstFRadioButton.setText("");
        secondFRadioButton.setText("");
        thirdFRadioButton.setText("");*/

        //firstLLabel.setText("");

        /*firstLRadioButton.setText("");
        secondLRadioButton.setText("");
        thirdLRadioButton.setText("");*/

//        isFirstLRBSelected = firstLRadioButton.isSelected();
//        isSecondLRBSelected = secondLRadioButton.isSelected();
//        isThirdLRBSelected = thirdLRadioButton.isSelected();

        /*textField.setOnAction(e -> nameTextFieldClear());

        textField.setOnAction(e -> nameTextFieldUpdated());*/

        choiceBox.getItems().add("Football");
        choiceBox.getItems().add("Lacrosse");

        /*isFirstFRBSelected = false;
        isSecondFRBSelected = false;
        isThirdFRBSelected = false;

        isFirstLRBSelected = false;
        isSecondLRBSelected = false;
        isThirdLRBSelected = false;*/

        // Try restoring saved text from file
        try {
            File savedText = new File(getClass().getResource("SavedText").toURI());
            if (savedText.exists()) {
                BufferedReader input = new BufferedReader(new FileReader(savedText));
                model = new Model(input);
                input.close();
            } else {
                model = new Model();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Controller initialize EXCEPTION");
            model = new Model();
        }

        //textField.setText(model.getTextFieldText());

        // Now that model has been initialized from a file, update View with saved values from Model
 //       topLabel.setText(model.getTopLabelText());
 //       topTextField.setText(model.getTopTextFieldText());
 //       bottomTextField.setText(model.getBottomTextFieldText());
 //       ArrayList sideListViewTexts = model.getSideListViewTexts();
 //       for (int i = 0; i < sideListViewTexts.size(); i++) {
 //           sideListView.getItems().add(new Label((String)sideListViewTexts.get(i)));
 //       }
    }

    public void save() {
        System.out.println("Controller save");

        // Update the model with final text typed in View
        //model.setTopLabelText(topLabel.getText());
        //model.setTextFieldText(textField.getText());
        /*model.setBottomTextFieldText(bottomTextField.getText());
        int length = sideListView.getItems().size();
        model.getSideListViewTexts().clear();
        for (int i = 0; i < length; i++) {
            model.addToSideListViewTexts(sideListView.getItems().get(i).getText());
        }*/

        // Write the final model to a saved file
        try {
            File savedText = new File(getClass().getResource("SavedText").toURI());
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedText));
            if (writer != null) {
                model.save(writer);
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Controller save EXCEPTION");
        }

    }

    // these methods are event handler methods that are called when each GUI control is used

    /*public void nameTextFieldClear() {
        System.out.println("NameTextFieldClear: " + textField.getText());
        textField.setText("");
    }

    public void nameTextFieldUpdated() {
        System.out.println("nameTextFieldUpdated: " + textField.getText());

        if (textField.getText().length() > 0) {
            textField.setText(textField.getText());
        }
    }*/

    /*public void topTextFieldClear() {
        System.out.println("topTextFieldClear: " + textField.getText());

        // Keep the top label intact but clear the text field
        topLabel.setText(textField.getText());
        topTextField.setText("");
    }

    public void textFieldUpdated() {
        System.out.println("topTextFieldUpdated: " + textField.getText());

        // Update the top label when the top text field is updated.
        if (textField.getText().length() > 0) {
            textField.setText(textField.getText());
        }
    }

    public void textFieldReady() {
        System.out.println("bottomTextFieldReady: " + textField.getText());

        // Update the list view with the text from the bottom text field
        sideListView.getItems().add(new Label(textField.getText()));
        // Clear the bottom text field because it has been used.
        textField.setText("");
    }*/

    public void sportChoiceBoxPressed() {
        System.out.println("HI");

        String value = choiceBox.getValue();

        if (value == "Football") {
            questionsBeingAsked = footballQuestions;
        } else if (value == "Lacrosse") {
            questionsBeingAsked = lacrosseQuestions;
        }
        firstLabel.setText(questionsBeingAsked[currentQuestion][0]);

        firstRadioButton.setText(questionsBeingAsked[currentQuestion][1]);
        secondRadioButton.setText(questionsBeingAsked[currentQuestion][2]);
        thirdRadioButton.setText(questionsBeingAsked[currentQuestion][3]);
        correctAnswer = questionsBeingAsked[currentQuestion][Integer.parseInt(questionsBeingAsked[currentQuestion][4])];

            /*if (isFirstFRBSelected) {
                feedbackLabel.setText("Correct");
            }
            if (isSecondFRBSelected) {
                feedbackLabel.setText("Incorrect");
            }
            if (isThirdFRBSelected) {
                feedbackLabel.setText("Incorrect");
            }*/
        //}

        /*if (value == "Lacrosse") {
            firstLabel.setText("Lacrosse was first played by which group of people?");

            firstRadioButton.setText("British nobles");
            secondRadioButton.setText("Native Americans");
            thirdRadioButton.setText("Children in Spain");
            correctAnswer = "Native Americans";

            if (isFirstLRBSelected) {
                feedbackLabel.setText("Incorrect");
            }
            if (isSecondLRBSelected) {
                feedbackLabel.setText("Correct");
            }
            if (isThirdLRBSelected) {
                feedbackLabel.setText("Incorrect");
            }
        }*/
    }

    public void radiobutton1Selected() {
        System.out.println("HI 1");

        /*if (currentQuestion >= 2) {
            firstLabel.setText("You got " + numberOfCorrectAnswers + " out of 3 correct");
        }*/
        if (currentQuestion < 2) {
            if (firstRadioButton.getText().equals(correctAnswer)) {
                System.out.println(firstRadioButton.getText());
            /*if (firstRadioButton.getText() == "Cow") {
                feedbackLabel.setText("Correct");
            }*/
                feedbackLabel.setText("Correct");

                numberOfCorrectAnswers = numberOfCorrectAnswers + 1;

                // Display next question
                currentQuestion = currentQuestion + 1;
                firstLabel.setText(questionsBeingAsked[currentQuestion][0]);

                firstRadioButton.setText(questionsBeingAsked[currentQuestion][1]);
                secondRadioButton.setText(questionsBeingAsked[currentQuestion][2]);
                thirdRadioButton.setText(questionsBeingAsked[currentQuestion][3]);
                correctAnswer = questionsBeingAsked[currentQuestion][Integer.parseInt(questionsBeingAsked[currentQuestion][4])];
            } else {
                feedbackLabel.setText("Incorrect");

                currentQuestion = currentQuestion + 1;
                firstLabel.setText(questionsBeingAsked[currentQuestion][0]);

                firstRadioButton.setText(questionsBeingAsked[currentQuestion][1]);
                secondRadioButton.setText(questionsBeingAsked[currentQuestion][2]);
                thirdRadioButton.setText(questionsBeingAsked[currentQuestion][3]);
                correctAnswer = questionsBeingAsked[currentQuestion][Integer.parseInt(questionsBeingAsked[currentQuestion][4])];
            }
        }
        if(currentQuestion >= 2) {
            firstLabel.setText("You got " + numberOfCorrectAnswers + " out of 3 correct");
        }
        System.out.println("# of correct answers = " + numberOfCorrectAnswers);
        System.out.println("current question = " + currentQuestion);
    }

    public void radiobutton2Selected() {
        System.out.println("HI 2");

        /*if (currentQuestion >= 2) {
            firstLabel.setText("You got " + numberOfCorrectAnswers + " out of 3 correct");
        }*/
        if (currentQuestion < 2) {
            if (secondRadioButton.getText().equals(correctAnswer)) {
                System.out.println(secondRadioButton.getText());
            /*if (firstRadioButton.getText() == "Cow") {
                feedbackLabel.setText("Correct");
            }*/
                feedbackLabel.setText("Correct");

                numberOfCorrectAnswers = numberOfCorrectAnswers + 1;

                // Display next question
                currentQuestion = currentQuestion + 1;
                firstLabel.setText(questionsBeingAsked[currentQuestion][0]);

                firstRadioButton.setText(questionsBeingAsked[currentQuestion][1]);
                secondRadioButton.setText(questionsBeingAsked[currentQuestion][2]);
                thirdRadioButton.setText(questionsBeingAsked[currentQuestion][3]);
                correctAnswer = questionsBeingAsked[currentQuestion][Integer.parseInt(questionsBeingAsked[currentQuestion][4])];
            } else {
                feedbackLabel.setText("Incorrect");

                currentQuestion = currentQuestion + 1;
                firstLabel.setText(questionsBeingAsked[currentQuestion][0]);

                firstRadioButton.setText(questionsBeingAsked[currentQuestion][1]);
                secondRadioButton.setText(questionsBeingAsked[currentQuestion][2]);
                thirdRadioButton.setText(questionsBeingAsked[currentQuestion][3]);
                correctAnswer = questionsBeingAsked[currentQuestion][Integer.parseInt(questionsBeingAsked[currentQuestion][4])];
            }
        }
        if (currentQuestion >= 2) {
            firstLabel.setText("You got " + numberOfCorrectAnswers + " out of 3 correct");
        }
        System.out.println("# of correct answers = " + numberOfCorrectAnswers);
        System.out.println("current question = " + currentQuestion);
    }

    public void radiobutton3Selected() {
        System.out.println("HI 3");

        /*if (currentQuestion >= 2) {
            firstLabel.setText("You got " + numberOfCorrectAnswers + " out of 3 correct");
        }*/
        if (currentQuestion < 2) {
            if (thirdRadioButton.getText().equals(correctAnswer)) {
                System.out.println(thirdRadioButton.getText());
            /*if (firstRadioButton.getText() == "Cow") {
                feedbackLabel.setText("Correct");
            }*/
                feedbackLabel.setText("Correct");

                numberOfCorrectAnswers = numberOfCorrectAnswers + 1;

                // Display next question
                currentQuestion = currentQuestion + 1;
                firstLabel.setText(questionsBeingAsked[currentQuestion][0]);

                firstRadioButton.setText(questionsBeingAsked[currentQuestion][1]);
                secondRadioButton.setText(questionsBeingAsked[currentQuestion][2]);
                thirdRadioButton.setText(questionsBeingAsked[currentQuestion][3]);
                correctAnswer = questionsBeingAsked[currentQuestion][Integer.parseInt(questionsBeingAsked[currentQuestion][4])];
            } else {
                feedbackLabel.setText("Incorrect");

                currentQuestion = currentQuestion + 1;
                firstLabel.setText(questionsBeingAsked[currentQuestion][0]);

                firstRadioButton.setText(questionsBeingAsked[currentQuestion][1]);
                secondRadioButton.setText(questionsBeingAsked[currentQuestion][2]);
                thirdRadioButton.setText(questionsBeingAsked[currentQuestion][3]);
                correctAnswer = questionsBeingAsked[currentQuestion][Integer.parseInt(questionsBeingAsked[currentQuestion][4])];
            }
        }
        if (currentQuestion >= 2) {
            firstLabel.setText("You got " + numberOfCorrectAnswers + " out of 3 correct");
        }
        System.out.println("# of correct answers = " + numberOfCorrectAnswers);
        System.out.println("current question = " + currentQuestion);
    }
}
