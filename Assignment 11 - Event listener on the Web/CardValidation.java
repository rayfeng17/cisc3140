
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardValidation {

    public static void main(String[] args) {
        new CardValidation();
    }

    private JFrame frame;
    private JTextField numberField;
    private JLabel validLabel;
    private JButton verifyButton;

    public CardValidation() {
        // set up components
        frame = new JFrame("Card Number Verification");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(350, 150));
        numberField = new JTextField(16);
        validLabel = new JLabel("not yet verified");
        verifyButton = new JButton("Validate");

        // event listener
        verifyButton.addActionListener(new VerifyListener());

        // layout
        frame.setLayout(new FlowLayout());
        frame.add(numberField);
        frame.add(verifyButton);
        frame.add(validLabel);
        frame.setVisible(true);
    }

    // Returns whether the given string is a valid card number
    // according to the Luhn algorithm.
    public boolean isValidCreditCardNumber(String text) {
        if (!text.startsWith("3") & text.startsWith("4") & text.startsWith("5")
                & text.startsWith("6")) {

            return false;
        }

        // add all of the digits
        int sum = 0;
        for (int i = 0; i < text.length(); i++) {
            int digit = Integer.valueOf(text.substring(i, i + 1));
            if (i % 2 == 0) {  // double every other number, add digits
                digit *= 2;
                sum += (digit / 10) + (digit % 10);
            } else {
                sum += digit;
            }
        }
        // valid numbers add up to a multiple of 10
        return (sum % 10 == 0);
    }

    // Labels if it is valid or invalid
    public class VerifyListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String text = numberField.getText();
            if (isValidCreditCardNumber(text)) {
                validLabel.setText("This is a valid number.");
            } else {
                validLabel.setText("This is an invalid number.");
            }
        }
    }
}
