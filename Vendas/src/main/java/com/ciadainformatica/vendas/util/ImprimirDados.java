package com.ciadainformatica.vendas.util;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
//Inicio do código
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;



public class ImprimirDados {
    private static PrintService impressora;
    
    public ImprimirDados() {
        detectaImpressoras();
    }


	// Essa classe é a responsavel pela impressao. Ela detecta a impressora
	// instalada, recebe o texto e o imprime.

	    // O metodo verifica se existe impressora conectada e a
	    // define como padrao.
	    public void detectaImpressoras() {
	        try {
	            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
	            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
	            for (PrintService p: ps) {
	                System.out.println("Impressora encontrada: " + p.getName());
	                if (p.getName().contains("ImpressoraJava") || p.getName().contains("generic"))  {
		                System.out.println("Impressora Selecionada: " + p.getName());
	                    impressora = p;
	                    impressora = PrintServiceLookup.lookupDefaultPrintService(); // pega a //impressora padrao  
	                    break;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    
	    public synchronized boolean imprime(String texto)  {

	                
	                try {  
	                    FileOutputStream os = new FileOutputStream("PDF995PORT");  
	                    PrintStream ps = new PrintStream(os);  
	                    ps.print(texto);  
	                    os.close();  
	                      
	                } catch(Exception e) {
	                	System.out.println("impressora não configurada, porta no programa deve estar errada");
	                }  
	                
	            	System.out.println("impressão com sucesso!");
	                return true;

	    }
}
