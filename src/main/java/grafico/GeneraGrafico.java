package grafico;
import java.awt.Color;
import java.util.ArrayList;

import consumos.ConsumosHorarios;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GeneraGrafico {
    public static void Creagrafico(ArrayList<ConsumosHorarios> lista, String nombreGrafico){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < lista.size(); i++) {
            dataset.addValue(lista.get(i).getConsumo(), "Values", Integer.toString(i + 1));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                nombreGrafico, // Título del gráfico
                "Horas", // Etiqueta del eje X
                "Consumo", // Etiqueta del eje Y
                dataset, // Datos del gráfico
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true, // Leyenda
                true, // Tooltips
                false // URLs
        );

        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        domainAxis.setLabel("Consumos");
        rangeAxis.setLabel("Horas");

        ChartFrame frame = new ChartFrame("Chart", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
