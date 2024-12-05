import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoList {
    private JFrame frame;
    private JPanel taskPanel;
    private JTextField taskInput;
    private ArrayList<JCheckBox> tasks;

    public ToDoList() {
        // Initialize main frame
        frame = new JFrame("Pink To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Top panel for input
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(255, 182, 193)); // Light pink
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("To-Do List");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        inputPanel.add(title, BorderLayout.NORTH);

        taskInput = new JTextField();
        taskInput.setFont(new Font("Arial", Font.PLAIN, 16));
        taskInput.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        inputPanel.add(taskInput, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Task");
        addButton.setBackground(new Color(255, 105, 180)); // Hot pink
        addButton.setForeground(Color.PINK);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask(taskInput.getText());
                taskInput.setText("");
            }
        });
        inputPanel.add(addButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Main panel for tasks
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBackground(new Color(255, 240, 245)); // Lavender blush
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBorder(null);
        frame.add(scrollPane, BorderLayout.CENTER);

        tasks = new ArrayList<>();

        frame.setVisible(true);
    }

    private void addTask(String taskText) {
        if (taskText == null || taskText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Task cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel taskContainer = new JPanel(new BorderLayout());
        taskContainer.setBackground(new Color(255, 240, 245));

        JCheckBox task = new JCheckBox(taskText);
        task.setFont(new Font("Arial", Font.PLAIN, 16));
        task.setBackground(new Color(255, 240, 245));
        task.setForeground(Color.DARK_GRAY);

        // Add action listener to toggle strikethrough on completion
        task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (task.isSelected()) {
                    task.setFont(task.getFont().deriveFont(Font.ITALIC | Font.BOLD));
                } else {
                    task.setFont(task.getFont().deriveFont(Font.PLAIN));
                }
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(255, 69, 158)); // Deep pink
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskContainer.setVisible(false); // Hide the task and delete it from panel
                taskPanel.remove(taskContainer);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 240, 245));
        buttonPanel.add(deleteButton);

        taskContainer.add(task, BorderLayout.CENTER);
        taskContainer.add(buttonPanel, BorderLayout.EAST);

        tasks.add(task);
        taskPanel.add(taskContainer);
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoList::new);
    }
}
