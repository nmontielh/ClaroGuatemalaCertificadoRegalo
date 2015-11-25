<%  
Object archivo=request.getAttribute("bytesRepor");
if(archivo!=null){
	byte[] bytes=(byte[]) archivo;
	response.setContentType(request.getAttribute("tipo").toString());
	response.setContentLength(bytes.length);
	ServletOutputStream ouputStream = null;
	try{
		ouputStream = response.getOutputStream();
	}catch(Exception e){
		
	}
	
	ouputStream.write(bytes, 0, bytes.length);
	ouputStream.flush();
	ouputStream.close();
}%>