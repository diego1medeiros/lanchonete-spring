package br.com.lanchonete.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.primefaces.model.file.UploadedFile;

public class UploadImagem {

			public static UploadedFile uploadImagem(UploadedFile file) throws IOException {
				
				Path arquivo = Files.createTempFile(null, null);
				Files.copy(file.getInputStream(), arquivo, StandardCopyOption.REPLACE_EXISTING);
				Path origem = Paths.get(arquivo.toString());
				Path destino = Paths.get("C:/Uploads/" + file.getFileName());
				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

				return file;
	
			}
				
}
