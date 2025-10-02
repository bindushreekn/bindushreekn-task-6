import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoApp extends JFrame {

    private final DefaultListModel<String> taskListModel;
    private final JList<String> taskList;
    private final JTextField taskInputField;
    private final JButton addButton;
    private final JButton deleteButton;

    public ToDoApp() {

        setTitle("To-Do List Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        JScrollPane scrollPane = new JScrollPane(taskList);

        taskInputField = new JTextField();
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setupActionListeners();
    }

    private void setupActionListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInputField.getText().trim();
                if (!task.isEmpty()) {
                    taskListModel.addElement(task);
                    taskInputField.setText("");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoApp().setVisible(true);
            }
        });
    }
}