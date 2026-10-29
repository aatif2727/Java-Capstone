import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

class Employee {
    int id;
    String name, department;
    double salary;

    Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    double netSalary() {
        double allowance = salary * 0.10;
        double deduction = salary * 0.05;
        return salary + allowance - deduction;
    }

    public String toString() {
        return "Employee ID: " + id +
               "\nName: " + name +
               "\nDepartment: " + department +
               "\nBasic Salary: Rs. " + salary +
               "\nNet Salary: Rs. " + netSalary() +
               "\n==============================\n";
    }
}

public class EmployeePayrollSystem 
{
    public static void main(String args[]) 
	{

        JFrame frame = new JFrame("Employee Payroll System");
        frame.setSize(600, 550);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.getContentPane().setBackground(
            new Color(128, 203, 196));

        JLabel heading = new JLabel(
            "EMPLOYEE PAYROLL SYSTEM", SwingConstants.CENTER);
        heading.setBounds(50, 5, 500, 35);
        heading.setFont(new Font("Verdana", Font.BOLD, 21));
        heading.setOpaque(true);
        heading.setBackground(new Color(109, 90, 145)); 
        heading.setForeground(new Color(255,255,255));
        frame.add(heading);


        JLabel l1 = new JLabel("Employee ID:");
        l1.setBounds(50, 55, 120, 30);
        l1.setFont(new Font("Arial", Font.BOLD, 13));
        l1.setForeground(new Color(49, 46, 86));
        frame.add(l1);

        JTextField txtId = new JTextField();
        txtId.setBounds(200, 55, 200, 30);
        txtId.setBackground(new Color(213, 232, 212));
        txtId.setForeground(new Color(49, 46, 86));
        txtId.setBorder(BorderFactory.createLineBorder(
            new Color(37, 71, 42), 2));
        frame.add(txtId);


        JLabel l2 = new JLabel("Employee Name:");
        l2.setBounds(50, 105, 130, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 13));
        l2.setForeground(new Color(49, 46, 86));
        frame.add(l2);

        JTextField txtName = new JTextField();
        txtName.setBounds(200, 105, 200, 30);
        txtName.setBackground(new Color(213, 232, 212));
        txtName.setForeground(new Color(49, 46, 86));
        txtName.setBorder(BorderFactory.createLineBorder(
            new Color(37, 71, 42), 2));
        frame.add(txtName);


        JLabel l3 = new JLabel("Basic Salary:");
        l3.setBounds(50, 155, 120, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 13));
        l3.setForeground(new Color(49, 46, 86));
        frame.add(l3);

        JTextField txtSalary = new JTextField();
        txtSalary.setBounds(200, 155, 200, 30);
        txtSalary.setBackground(new Color(213, 232, 212));
        txtSalary.setForeground(new Color(49, 46, 86));
        txtSalary.setBorder(BorderFactory.createLineBorder(
            new Color(37, 71, 42), 2));
        frame.add(txtSalary);


        JLabel l4 = new JLabel("Department:");
        l4.setBounds(50, 205, 120, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 13));
        l4.setForeground(new Color(49, 46, 86));
        frame.add(l4);

        String departments[] = {"IT", "HR", "Sales", "Finance"};

        JComboBox<String> dept =
            new JComboBox<String>(departments);
        dept.setBounds(200, 205, 200, 30);
        dept.setBackground(new Color(213, 232, 212));
        dept.setForeground(new Color(49, 46, 86));
        frame.add(dept);


        JButton save = new JButton("Save");
        save.setBounds(100, 255, 120, 35);
        save.setBackground(new Color(38, 116, 91));
        save.setForeground(new Color(255,255,255));
        save.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(save);


        JButton view = new JButton("View All");
        view.setBounds(350, 255, 120, 35);
        view.setBackground(new Color(23, 37, 84));
        view.setForeground(new Color(255,255,255));
        view.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(view);


        JLabel l5 = new JLabel("Employee Records:");
        l5.setBounds(50, 295, 200, 25);
        l5.setFont(new Font("Arial", Font.BOLD, 14));
        l5.setForeground(new Color(49, 46, 86));
        frame.add(l5);


        JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setBackground(new Color(247, 217, 196));
        output.setForeground(new Color(99, 59, 38));
        output.setFont(new Font("Arial", Font.PLAIN, 14));
        output.setMargin(new Insets(8, 8, 8, 8));

        JScrollPane scroll = new JScrollPane(output);
        scroll.setBounds(50, 320, 500, 170);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(
            new Color(153, 170, 200), 2));
        frame.add(scroll);

        
        ArrayList<Employee> employees =
            new ArrayList<Employee>();

       
        save.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String name = txtName.getText().trim();
                double salary = Double.parseDouble(
                    txtSalary.getText().trim());
                String department =
                    dept.getSelectedItem().toString();

                if (id <= 0 || name.isEmpty() || salary <= 0) {
                    JOptionPane.showMessageDialog(
                        frame, "Enter valid employee details!");
                    return;
                }

               
                Employee emp =
                    new Employee(id, name, salary, department);
                employees.add(emp);

                FileWriter fw = new FileWriter("employees.txt");
                BufferedWriter bw = new BufferedWriter(fw);

                for (Employee employee : employees) {
                    bw.write(employee.id + "  |  " + employee.name + "  |  " +
                             employee.salary + "  |  " +
                             employee.department);
                    bw.newLine();
                }

                bw.close();

                JOptionPane.showMessageDialog(
                    frame, "Employee saved successfully!");

                txtId.setText("");
                txtName.setText("");
                txtSalary.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    frame, "Enter a valid ID and salary!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    frame, "Error while saving records!");
            }
        });

       
        view.addActionListener(e -> {
            output.setText("");

            if (employees.isEmpty()) {
                output.setText("No employee records found.");
            }
			else {
                for (Employee emp : employees) {
                    output.append(emp.toString());
                }
            }
        });

        frame.setVisible(true);
    }
}
