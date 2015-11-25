/**
*VIB3CC6 06/09/2005 
*Estas funciones permiten realizar el resaltado de palabras de acuerdo con la variable word
*/
//Variable que contendrá la palabra buscada.
var word ="";

/*Esta función obtiene de la URL la palabra a buscar*/
function getPalabra(){
	// Revisamos que haya un ? en la URL
	//alert(window.location.href);
	var is_input = window.location.href+"";
	res=is_input.indexOf('?');
	//alert("Posicion del ? > "+res);
	//Revisamos la posición del ? en la url
	if (res != -1)
		{ 
		// En addr_str ponemos lo que hay a partir del ?
		addr_str = is_input.substring(res+1, is_input.length);
		var wsearch=addr_str.split("=");
		word =wsearch[1];
		if(word.indexOf('+')==-1)
			highlightSearchTerms(word);
		else{
			//Si la cadena trae + los remplazamos x espacios.
			while (word.indexOf('+')>-1) {
				pos= word.indexOf('+');
				word = "" + (word.substring(0, pos) + " " + 
				word.substring((pos + '+'.length), word.length));
				}
			//alert(word);				//No es tratada como frase
			//highlightSearchTerms(word, false, true,"","");
										//Si es tratada como frase
			highlightSearchTerms(word, true, true,"","");
			}
	}//if
}//End getPalabra

/*
 * This is the function that actually highlights a text string by
 * adding HTML tags before and after all occurrences of the search
 * term. You can pass your own tags if you'd like, or if the
 * highlightStartTag or highlightEndTag parameters are omitted or
 * are empty strings then the default <font> tags will be used.
 */
function doHighlight(bodyText, searchTerm, highlightStartTag, highlightEndTag) 
{
  // the highlightStartTag and highlightEndTag parameters are optional
  if ((!highlightStartTag) || (!highlightEndTag)) {
    highlightStartTag = "<font style='color:blue; background-color:#00B2EE;'>";
    highlightEndTag = "</font>";
  }
  
  // find all occurences of the search term in the given text,
  // and add some "highlight" tags to them (we're not using a
  // regular expression search, because we want to filter out
  // matches that occur within HTML tags and script blocks, so
  // we have to do a little extra validation)
  var newText = "";
  var i = -1;
  var lcSearchTerm = searchTerm.toLowerCase();
  var lcBodyText = bodyText.toLowerCase();
    
  while (bodyText.length > 0) {
    i = lcBodyText.indexOf(lcSearchTerm, i+1);
    if (i < 0) {
      newText += bodyText;
      bodyText = "";
    } else {
      // skip anything inside an HTML tag
      if (bodyText.lastIndexOf(">", i) >= bodyText.lastIndexOf("<", i)) {
        // skip anything inside a <script> block
        if (lcBodyText.lastIndexOf("/script>", i) >= lcBodyText.lastIndexOf("<script", i)) {
          newText += bodyText.substring(0, i) + highlightStartTag + bodyText.substr(i, searchTerm.length) + highlightEndTag;
          bodyText = bodyText.substr(i + searchTerm.length);
          lcBodyText = bodyText.toLowerCase();
          i = -1;
        }
      }
    }
  }
  
  return newText;
}


/*
 * This is sort of a wrapper function to the doHighlight function.
 * It takes the searchText that you pass, optionally splits it into
 * separate words, and transforms the text on the current web page.
 * Only the "searchText" parameter is required; all other parameters
 * are optional and can be omitted.
 */
function highlightSearchTerms(searchText, treatAsPhrase, warnOnFailure, highlightStartTag, highlightEndTag)
{
  // if the treatAsPhrase parameter is true, then we should search for 
  // the entire phrase that was entered; otherwise, we will split the
  // search string so that each word is searched for and highlighted
  // individually
  if (treatAsPhrase) {
    searchArray = [searchText];
  } else {
    searchArray = searchText.split(" ");
  }
  
  if (!document.body || typeof(document.body.innerHTML) == "undefined") {
    if (warnOnFailure) {
      alert("Por alguna razon el texto de esta pagina no se encuentra disponible, por lo que la busqueda no servira.");
    }
    return false;
  }
  
  var bodyText = document.body.innerHTML;
  for (var i = 0; i < searchArray.length; i++) {
    bodyText = doHighlight(bodyText, searchArray[i], highlightStartTag, highlightEndTag);
  }
  
  document.body.innerHTML = bodyText;
  return true;
}




/*
 * This function takes a referer/referrer string and parses it
 * to determine if it contains any search terms. If it does, the
 * search terms are passed to the highlightSearchTerms function
 * so they can be highlighted on the current page.
 */
function highlightGoogleSearchTerms(referrer)
{
  // This function has only been very lightly tested against
  // typical Google search URLs. If you wanted the Google search
  // terms to be automatically highlighted on a page, you could
  // call the function in the onload event of your <body> tag, 
  // like this:
  //   <body onload='highlightGoogleSearchTerms(document.referrer);'>
  
  //var referrer = document.referrer;
  if (!referrer) {
    return false;
  }
  
  var queryPrefix = "q=";
  var startPos = referrer.toLowerCase().indexOf(queryPrefix);
  if ((startPos < 0) || (startPos + queryPrefix.length == referrer.length)) {
    return false;
  }
  
  var endPos = referrer.indexOf("&", startPos);
  if (endPos < 0) {
    endPos = referrer.length;
  }
  
  var queryString = referrer.substring(startPos + queryPrefix.length, endPos);
  // fix the space characters
  queryString = queryString.replace(/%20/gi, " ");
  queryString = queryString.replace(/\+/gi, " ");
  // remove the quotes (if you're really creative, you could search for the
  // terms within the quotes as phrases, and everything else as single terms)
  queryString = queryString.replace(/%22/gi, "");
  queryString = queryString.replace(/\"/gi, "");
  
  return highlightSearchTerms(queryString, false);
}


/*
 * This function is just an easy way to test the highlightGoogleSearchTerms
 * function.
 */
function testHighlightGoogleSearchTerms()
{
  var referrerString = "http://www.google.com/search?q=javascript%20highlight&start=0";
  referrerString = prompt("Test the following referrer string:", referrerString);
  return highlightGoogleSearchTerms(referrerString);
}