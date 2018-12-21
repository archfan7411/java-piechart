
/**
 * This class can be used to create quick, effective pie charts.
 *
 * @author archfan7411
 * @version 0.2
 */
import java.awt.*;
import javax.swing.*;

public class PieChart extends JPanel
{
    final int MAX_ELEMENTS = 360;
    
    final double LINE_EXT = 1.2;
    
    private String[] sectors = new String[MAX_ELEMENTS];
    
    private int[] sectorLengths = new int[MAX_ELEMENTS];
    
    private Color[] sectorColors = new Color[MAX_ELEMENTS];
    
    private int numElements, pieX, pieY, pieWidth, pieHeight, sIndex=0, slIndex=0, scIndex=0, radius;
    

    /**
     * Constructor for objects of class PieChart
     * 
     * @param x The x position of the pie chart.
     * @param y The y position of the pie chart.
     * @param width The width of the pie chart.
     * @param height The height of the pie chart.
     */
    public PieChart(int x, int y, int radi)
    {
        pieX = x;
        pieY = y;
        pieWidth = radi * 2;
        pieHeight = radi * 2;
        radius = radi;
    }
    
    /**
     * Constructor for objects of class PieChart
     * 
     * @param x The x position of the pie chart.
     * @param y The y position of the pie chart.
     * @param width The width of the pie chart.
     * @param height The height of the pie chart.
     * @param initialElements Array of initial elements.
     * @param initialValues Array of initial values (sizes) of the elements.
     * @param initialColors Array of initial colors of the elements.
     * 
     */
    public PieChart(int x, int y, int radi, String[] initialElements, int[] initialValues, Color[] initialColors)
    {
        numElements = initialElements.length;
        sectors = initialElements;
        sectorLengths = initialValues;
        sectorColors = initialColors;
        pieX = x;
        pieY = y;
        pieWidth = radi * 2;
        pieHeight = radi * 2;
        radius = radi;
        
    }
    /**
     * Adds an element to the pie chart.
     * 
     * @param element The name of the new pie chart element.
     * @param value The value (size) of the new pie chart element.
     * @param color The color of the new pie chart element.
     */
    public void AddElement(String element, int value, Color color)
    {
        numElements++;
        sectors[sIndex] = element;
        sIndex++;
        sectorLengths[slIndex] = value;
        slIndex++;
        sectorColors[scIndex] = color;
        scIndex++;
    }
    /**
     * Returns array of pie chart elements.
     * @return String[] of pie chart elements by name
     */
    public String[] GetElementArray()
    {
        return sectors;
    }
    /**
     * Returns array of pie chart values.
     * @return int[] of pie chart values
     */
    public int[] GetValueArray()
    {
        return sectorLengths;
    }
    /**
     * Returns array of pie chart element colors.
     * @return Color[] of pie chart element colors
     */
    public Color[] GetColorArray()
    {
        return sectorColors;
    }
    /**
     * Sets the specified pie chart value to the specified value.
     * Fails upon ArrayIndexOutOfBounds.
     * @param index - The pie chart element to change.
     * @param value - The amount to set the element to.
     */
    public void SetValue(int index, int value)
    {
        try {
            sectorLengths[index] = value;
        }
        catch(ArrayIndexOutOfBoundsException e) {
            
            System.out.println("PieChart: ERROR: Element incrementation failed (ArrayIndexOutOfBounds).");
            System.out.println("PieChart: Are you trying to access an element that does not exist?");
            
        }
    }
    
    public void paintComponent(Graphics g)
    {
        
        super.paintComponent(g);
        paintComponent((Graphics2D)g);
    }
    
    public void paintComponent(Graphics2D g)
    {
        /*Antialiasing/"Smoother Borders"*/
        RenderingHints r=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.addRenderingHints(r);
        /*Text Antialiasing*/
        RenderingHints r=new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.addRenderingHints(r);
        
        double currentDegree = 0;
        
        if (getTotalValues() != 0) {
        
        	for (int i = 0; i < sIndex; i++)
        	{
            	g.setColor(sectorColors[i]);
            	double arcLength = valueToDegrees(sectorLengths[i]);
            	g.fillArc(pieX, pieY, pieWidth, pieHeight, (int)currentDegree, (int)arcLength + 1);
            	currentDegree += arcLength;
            
            	AddLegend(g);
        	}
        	
        }
        
        if (getTotalValues() == 0) {
        	g.setColor(Color.LIGHT_GRAY);
        	g.fillOval(pieX, pieY, pieWidth, pieHeight);
        	AddLegend(g);
        }
    }
    
    private int getTotalValues()
    {
        int totalValue = 0;
        
        for (int i = 0; i < sectorLengths.length; i++)
        {
            totalValue += sectorLengths[i];
        }
        
        return totalValue;
    }
    
    private double valueToDegrees(int value)
    {
        double total = getTotalValues();
        double newValue = value;
        double result = (value / total) * 360;
        return result;
    }
    
    private void AddLegend(Graphics g)
    {
    	for(int i = 0; i < sIndex; i++)
    	{
    		double posX = (pieX + 1.2 * radius) + radius * 1.2;
    		double posY = pieY + 30 * i;
    		
    		g.setColor(sectorColors[i]);
    		g.fillRect((int)posX, (int)posY, 20, 20);
    		g.setColor(Color.BLACK);
    		g.drawString(sectors[i] + ": " + sectorLengths[i], (int)posX + 30, (int)posY + 15);
    	}
    }
}
