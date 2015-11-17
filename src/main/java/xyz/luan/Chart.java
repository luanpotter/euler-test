package xyz.luan;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Chart extends ApplicationFrame {

    private static final long serialVersionUID = -1892064345301903930L;

    public Chart(String applicationTitle, String chartTitle, List<Vector> ys, double[] ts) {
        super(applicationTitle);
        DefaultCategoryDataset ds = createDataset(ys, ts);
        JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "t", "Estudantes", ds, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(List<Vector> ys, double[] ts) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < ys.size(); i++) {
            dataset.addValue((Number) ys.get(i).get(Main.S), "S", ts[i]);
            dataset.addValue((Number) ys.get(i).get(Main.I), "I", ts[i]);
            dataset.addValue((Number) ys.get(i).get(Main.R), "R", ts[i]);
        }
        return dataset;
    }

    public static void plot(List<Vector> ys, double[] ts) {
        Chart chart = new Chart("R, S and I Vs t", "R, S and I Vs t", ys, ts);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}