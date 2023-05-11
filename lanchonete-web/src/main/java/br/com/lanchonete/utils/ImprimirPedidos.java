package br.com.lanchonete.utils;

import java.awt.GraphicsEnvironment;
import java.awt.print.PrinterJob;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.text.View;

import org.primefaces.PrimeFaces;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class ImprimirPedidos {

	public static void imprimirPedido(String pathJasper, List<?> listaRelatorio) {

		try {
			JasperReport relatorio = JasperCompileManager.compileReport(pathJasper);
			JasperPrint relatorioPreenchido;
			relatorioPreenchido = JasperFillManager.fillReport(relatorio, null,
					new JRBeanCollectionDataSource(listaRelatorio));
		
			

			 JasperViewer.viewReport(relatorioPreenchido, false);
			// JasperPrintManager.printReport(relatorioPreenchido, false);
			
//			JDialog tela = new JDialog();
//			tela.setSize(1000, 600);
//			JRViewer painel = new JRViewer(relatorioPreenchido);
//			tela.getContentPane().add(painel);
//			tela.setVisible(true);
	       	} catch (JRException e) {
			e.printStackTrace();
//		}
	}
		}
}
