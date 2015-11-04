/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  org.apache.commons.net.ftp.FTPClient
 *  org.apache.commons.net.ftp.FTPConnectionClosedException
 *  org.apache.commons.net.ftp.FTPReply
 */
package com.claro.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;

import com.claro.catalogo.Catalogo;
import com.claro.transfer.service.FileDataTO;

public class ComunicaFTP {
	public FTPClient conectaFTP() throws Exception {
		FTPClient ftp;
		ftp = null;
		try {
			String username;
			String password;
			block13: {
				Catalogo properties = new Catalogo();
				properties.setTabla("propiedades");
				properties.cargaCatalogo();
				String server = properties.getPropiedad("ftp.hostname");
				int puerto = Integer.parseInt(properties
						.getPropiedad("ftp.puerto"));
				username = properties.getPropiedad("ftp.usuario");
				password = properties.getPropiedad("ftp.password");
				ftp = new FTPClient();
				try {
					ftp.connect(server, puerto);
					int reply = ftp.getReplyCode();
					if (!FTPReply.isPositiveCompletion((int) reply)) {
						ftp.disconnect();
					}
				} catch (IOException e) {
					if (!ftp.isConnected())
						break block13;
					try {
						ftp.disconnect();
					} catch (IOException f) {
						throw new Exception(f.getMessage());
					}
				}
			}
			try {
				if (!ftp.login(username, password)) {
					ftp.logout();
				}
			} catch (FTPConnectionClosedException e) {
				throw new Exception(e.getMessage());
			} catch (IOException e) {
				throw new Exception(e.getMessage());
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return ftp;
	}

	public boolean eliminaFTP(FTPClient ftp, String remote) throws Exception {
		boolean elimina;
		elimina = false;
		try {
			try {
				ftp.deleteFile(remote);
				elimina = true;
			} catch (FTPConnectionClosedException e) {
				throw new Exception(e.getMessage());
			} catch (IOException e) {
				throw new Exception(e.getMessage());
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (Throwable ex) {
			throw new Exception(ex.toString());
		}
		return elimina;
	}

	public boolean eliminaenFTP(String remote) throws Exception {
		boolean borra;
		borra = false;
		FTPClient ftp = null;
		try {
			try {
				Catalogo properties = new Catalogo();
				properties.setTabla("propiedades");
				properties.cargaCatalogo();
				String root = properties.getPropiedad("ftp.documentRoot");
				ComunicaFTP libera = new ComunicaFTP();
				ftp = libera.conectaFTP();
				ftp.changeWorkingDirectory(String.valueOf(root) + "/");
				borra = libera.eliminaFTP(ftp, remote);
			} catch (Exception ex) {
				throw new Exception(ex.toString());
			} catch (Throwable ex) {
				throw new Exception(ex.toString());
			}
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.logout();
					ftp.disconnect();
				} catch (IOException f) {
					throw new Exception(f.toString());
				}
			}
		}
		return borra;
	}

	public FileDataTO leeArchivo(String directorio, String archivo)
			throws Exception {
		try {
			Catalogo properties = new Catalogo();
			properties.setTabla("propiedades");
			properties.cargaCatalogo();
			URL url = new URL(String.valueOf(properties
					.getPropiedad("archivos.rutaWeb")) + directorio + archivo);
			URLConnection conn = url.openConnection();
			return new FileDataTO(conn.getInputStream(),
					conn.getContentLength());
		} catch (Exception e) {
			throw new Exception(
					"Servicios de Archivos: Error al obtener el archivo: " + e);
		}
	}

	public BufferedReader leeArchivoFTP(String archivo) throws Exception {
		BufferedReader reader;
		reader = null;
		InputStream inputStream = null;
		Catalogo properties = new Catalogo();
		properties.setTabla("propiedades");
		properties.cargaCatalogo();
		String root = properties.getPropiedad("ftp.documentRoot");
		FTPClient client = null;
		try {
			try {
				client = this.conectaFTP();
				if (!client.changeWorkingDirectory(String.valueOf(root) + "/")) {
					this.creaDirectorio(root, client);
				}
				inputStream = client.retrieveFileStream(archivo);
				InetAddress local = client.getLocalAddress();
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				reader = new BufferedReader(inputStreamReader);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					client.logout();
				} catch (Exception var10_10) {
					// empty catch block
				}
				try {
					client.disconnect();
				} catch (Exception var10_11) {
				}
			}
		} finally {
			try {
				client.logout();
			} catch (Exception var10_14) {
			}
			try {
				client.disconnect();
			} catch (Exception var10_15) {
			}
		}
		return reader;
	}

	public void guardaArchivo(String archivo, InputStream inputStream)
			throws Exception {
		Catalogo properties = new Catalogo();
		properties.setTabla("propiedades");
		properties.cargaCatalogo();
		String root = properties.getPropiedad("ftp.documentRoot");
		FTPClient client = null;
		try {
			boolean saveFile;
			client = this.conectaFTP();
			client.setFileType(2);
			if (!client.changeWorkingDirectory(String.valueOf(root) + "/")) {
				this.creaDirectorio(root, client);
			}
			if (!(saveFile = client.storeFile(archivo, inputStream))) {
				throw new Exception(
						"Servicio de Archivos: Error al guardar archivo: "
								+ archivo + ", por fallas en FTP");
			}
		} finally {
			try {
				inputStream.close();
			} catch (Exception var8_12) {
			}
			try {
				client.logout();
			} catch (Exception var8_13) {
			}
			try {
				client.disconnect();
			} catch (Exception var8_14) {
			}
		}
	}

	private void creaDirectorio(String directorio, FTPClient client)
			throws Exception {
		String[] directorios;
		for (String directory : directorios = directorio.split("/")) {
			if (directory.equals("")
					|| client.changeWorkingDirectory(directory))
				continue;
			if (client.makeDirectory(directory)
					&& client.changeWorkingDirectory(directory))
				continue;
			throw new Exception("FTP: No se puede crear estructura: "
					+ directorio);
		}
	}
}
