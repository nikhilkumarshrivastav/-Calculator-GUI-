import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {

    private JFrame frame;
    private JTextField textField;
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CalculatorGUI window = new CalculatorGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CalculatorGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        textField.setColumns(10);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Create number buttons and add to panel
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 24));
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText() + e.getActionCommand());
                }
            });
        }

        // Create operator buttons and add to panel
        JButton addButton = createOperatorButton("+");
        JButton subButton = createOperatorButton("-");
        JButton mulButton = createOperatorButton("*");
        JButton divButton = createOperatorButton("/");

        // Create other buttons
        JButton equalButton = new JButton("=");
        equalButton.setFont(new Font("Arial", Font.PLAIN, 24));
        equalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num2 = Double.parseDouble(textField.getText());
                calculate();
                textField.setText(String.valueOf(result));
            }
        });

        JButton clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 24));
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                num1 = num2 = result = 0;
                operator = "";
            }
        });

        // Add buttons to panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(clearButton);
        panel.add(numberButtons[0]);
        panel.add(equalButton);
        panel.add(divButton);
    }

    private JButton createOperatorButton(String operatorText) {
        JButton button = new JButton(operatorText);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
                operator = operatorText;
            }
        });
        return button;
    }

    private void calculate() {
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    result = 0;
                    JOptionPane.showMessageDialog(frame, "Error! Division by zero.");
                }
                break;
            default:
                break;
        }
    }
}
