package br.com.lanchonete.utils;

import java.io.InputStream;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.swing.JDialog;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.swing.JRViewer;

public class ImprimirPedidos {

	public static void imprimirPedido(List<?> listaRelatorio) {

		try {

			ServletContext context1 = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			InputStream jasperStream = context1.getResourceAsStream("/relatorio/relatorio-vendas.jrxml");

			JasperReport relatorio = JasperCompileManager.compileReport(jasperStream);
			JasperPrint relatorioPreenchido = JasperFillManager.fillReport(relatorio, null,
					new JRBeanCollectionDataSource(listaRelatorio));

			
			
			JDialog tela = new JDialog();
			tela.setSize(1000, 600);
			JRViewer painel = new JRViewer(relatorioPreenchido);
			tela.getContentPane().add(painel);
			tela.setVisible(true);
		} catch (JRException e) {

			System.out.println("não");

			e.printStackTrace();
//		}
		}
	}

//	
}
