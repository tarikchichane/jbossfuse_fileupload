package org.bam.services;


import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/fileset")
public class FileService {
	static final String directory="c:\\temp\\inbox\\";
	@GET
	@Path("/listFile")
	public String listFile() {
		File file=new File(directory);
		File[] listFileArray=file.listFiles();
		String listFileInDirectory="";
		for(File filetemp : listFileArray) {
			if(filetemp.isDirectory()==false) {
				listFileInDirectory=listFileInDirectory+filetemp.getName()+",";
			}
		}
		listFileInDirectory+="";
		return listFileInDirectory;
	}
	@GET
	@Path("/cksumfile/{fileName}")
	public String cksumFile(@PathParam("fileName") String fileName) {
		String fileckSum="";
		try {
			byte[] data = Files.readAllBytes(Paths.get(directory+fileName));
			byte[] hash = MessageDigest.getInstance("MD5").digest(data);
			fileckSum = new BigInteger(1, hash).toString(16);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileckSum;
	}
	
	@GET
	@Path("/download/{fileName}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	
	public Response downloadFileWithGet(@PathParam("fileName") String fileName) {
		File fileDownload = new File(directory+fileName);
		ResponseBuilder response= Response.ok((Object) fileDownload);
		response.header("Content-Disposition", "attachment;filename=" + fileName);
		return response.build();
		
	}
	
}
