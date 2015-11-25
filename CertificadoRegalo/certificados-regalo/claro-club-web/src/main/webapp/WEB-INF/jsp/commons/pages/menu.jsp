
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
    <head>
        <title>Puntos Telcel</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
        <script src='<c:url value="/commons/js/main.js"/>'  language="JavaScript" ></script>
        
    </head>
    <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" class="menu">
        <table width="70%" height="20" border="0" cellspacing="0" cellpadding="0" align ="center">
            <tr align ="center" valign ="middle"> 
            <td width="5%" >
                <a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                id="Link1" href="../web-content/pts_consultas/pts_puntos.html" target="mainFrame" onClick='actualiza()'>
                                   &nbsp;&nbsp;Puntos&nbsp;&nbsp;<HR color ="white" ></a></td> 
            <td width="1%"><font size="+1">&nbsp;</font></td>     
            <td   align ="justify" width="9%"><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                                                 id="Link2" href="pts_TransferenciaInicial.jsp" target="mainFrame" onClick='actualiza()'>&nbsp;&nbsp;Transferencia&nbsp;&nbsp;<HR color ="white"></a></td>
            <td width="1%">&nbsp;</td>
            <td   align ="justify" width="6%" ><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                                                  id="Link3" href="../web-content/pts_consultas/pts_imprimeinicial.html"  target="mainFrame" onClick='actualiza()'>&nbsp;&nbsp;Imprimir&nbsp;&nbsp;<HR color="white"></a></td>
            
            
            <td width="1%">&nbsp;</td>      
            <td   align ="justify" width="8%" ><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                                                      id="Link4" href="pts_administra.jsp"  target="mainFrame" onClick='actualiza()'>&nbsp;&nbsp;Administraci&oacute;n&nbsp;&nbsp; 
            <HR color ="white"></a></td>
            
            <td width="1%">&nbsp;</td>
            <td   align ="justify" width="7%" ><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                                                  id="Link5" href="pts_actualizapasswd.jsp" target="mainFrame" onClick='actualiza()'>&nbsp;&nbsp;Password&nbsp;&nbsp;<HR color ="white"></a></td>
            
            <td width="1%">&nbsp;</td>
            <td   align ="justify" width="15%"> 
                <p><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                      id="Link6" href="pts_ayuda.jsp" target="mainFrame" onClick='actualiza()'>&nbsp;&nbsp;Manuales&nbsp;y&nbsp;promociones&nbsp;&nbsp;<HR color="white">
            </a></td>
            
            <td width="1%">&nbsp;</td>
            <td  align ="justify" width="7%" > 
                <p><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                      id="Link7" href="pts_reposicioninicial.jsp" target="mainFrame" onClick='actualiza()'>&nbsp;&nbsp;Membresía&nbsp;&nbsp;<HR color="white"></a>
            </td>
            <td width="1%">&nbsp;</td>
            <td  align ="justify" width="7%" > 
                <p><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                      id="Link15" href="pts_renunciainicial.jsp" target="mainFrame" onClick='actualiza()'>&nbsp;&nbsp;Renuncia&nbsp;&nbsp;<HR color="white"></a>
            </td>
            
            <td width="1%">&nbsp;</td>
            <td   align ="justify" width="5%" ><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                                                      
                                                  id="Link12" href="#none" onClick='actualiza(); openWin();'>&nbsp;&nbsp;Buscar&nbsp;&nbsp;<HR color ="white"></a></td> 	
            
            <td width="1%">&nbsp;</td>
            <td align ="justify" width="5%" ><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                                                id="Link13" href="pts_subastainicial.jsp" target="mainFrame" onClick='actualiza()'>&nbsp;&nbsp;Subastas&nbsp;&nbsp;<HR color ="white"></a></td>	  
            <td width="1%">&nbsp;</td>
            <td align ="justify" width="5%" ><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                                                id="Link14" href="pts_retencioninicial.jsp" target="mainFrame" onClick='actualiza()'>&nbsp;&nbsp;Retención&nbsp;&nbsp;<HR color ="white"></a></td>
            
            <td width="1%">&nbsp;</td>
            <td   align ="justify" width="5%"><a style="width:100%" class="LinkOut" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                                                     id="Link11" href="pts_terminasesion.jsp" target="_parent">&nbsp;&nbsp;Salir&nbsp;&nbsp;&nbsp;
                <HR color ="white"></a>
            </td>
        </tr>
        </table>
    </body>
</html>
