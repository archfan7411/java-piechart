import java.awt.*;
import javax.swing.*;

public class ChartExample extends JFrame
{
    public ChartExample()
    {
        super("Pie Chart Example");
        Container c = getContentPane();
        c.setBackground(Color.WHITE);
        PieChart chart = new PieChart(30, 50, 100);
        chart.AddElement("Amount of red in this chart", 134, Color.RED);
        chart.AddElement("Amount of green in this chart", 483, Color.GREEN);
        chart.AddElement("Amount of yellow in this chart", 47, Color.YELLOW);
        c.add(chart);
    }

    public static void main()
    {
        ChartExample w = new ChartExample();
        w.setBounds(300, 300, 600, 330);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setVisible(true);
    }
}
