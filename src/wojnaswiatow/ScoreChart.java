/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;

/**
 *
 * @author Dawidziu
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart.
 *
 */
public class ScoreChart extends MainFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
        final JPanel content = new JPanel();  
        final CategoryDataset dataset = createDataset();    
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);       
        final JButton returnButton = new JButton("Return");
        
    public ScoreChart() {

        //super(title);
        initComponents();
        content.add(chartPanel);
        chartPanel.setPreferredSize(new Dimension(500, 270));
                 
    } 

    private void initComponents()
    {
         content.add(returnButton);

        
        returnButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            dispose();
        }
        });

        setContentPane(content);
        
    }
    
    private CategoryDataset createDataset() {
        
        // row keys...
        final String series1 = "Points";


        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        Transaction tx = null;
        Session sesja = HibernateUtil.getSessionFactory().openSession();
        tx = sesja.beginTransaction();
        
        List odczyt = sesja.createQuery("FROM Players ORDER BY score DESC").list();
        for (Iterator it = odczyt.iterator(); it.hasNext();)
        {
            Players gracz = (Players) it.next();
            dataset.addValue(gracz.getScore(), series1, gracz.getName());
        }

 
        return dataset;
        
    }
    
  
  
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Best scores",         // chart title
            "",               // domain axis label
            "",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.yellow
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }   


}
