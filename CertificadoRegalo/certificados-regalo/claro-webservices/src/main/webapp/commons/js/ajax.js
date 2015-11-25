
	var SimpleRequest = function(targetElementId, url, method, parameters, loader) {
        var oTarget = $(targetElementId);
        var oLoader = $(loader);
        
        if (oTarget == null) throw 'Target element is null!';
        if (url == null) throw 'URL has to be provided';

        if (method == null) {
            method = 'get';
        } else if (method != 'get' && method != 'post') {
            throw 'Method should be get or post'; 
        }
        var myAjax = new Ajax.Updater(
            { success: oTarget }, 
            url, 
            { 
                method: method,
                parameters: parameters, 
                onFailure: errFunc,
                evalScripts: true,
                onLoading: function() { oTarget.innerHTML = oLoader.innerHTML; },
				onComplete: function() {  }
            });
    };
    
    
    function formRequest(formElementId) {    
		Event.observe(formElementId, 'submit', handleSubmitEvent, true);
    }
    
    function handleSubmitEvent(event) {
    	var loader = 'divLoading';
    	var formElement = Event.element(event);
        if (formElement.tagName.toLowerCase() != 'form') {
            throw 'Element ' + formElement + ' is not a FORM element!';
        }
        var method = formElement.method;
        if (method == null) {
            method = 'get';
        }
        var url = formElement.action;
        if (url == null) {
        	throw 'No action defined on ' + formElement;
        }
        var targetElement = formElement.targetElement;
        if(targetElement == null){
			targetElement = formElement.parentNode;
        }
        try {
        	Event.stop(event);
			var myRequest = new Ajax.Updater(
				{ success: targetElement },
				url, 
				{ 
					method: method, 
					parameters: Form.serialize(formElement),
					evalScripts: true,
					onFailure: errFunc,
					onLoading: function() { showLoader(loader, targetElement); },
					onComplete: function() { hideLoader(loader, targetElement); }
				});
		} finally {
			return false;
		}
    }

    var handlerFunc = function(t) {
    	alert(t.responseText);
	}

	var errFunc = function(t) {
    	alert('Error ' + t.status + ' -- ' + t.statusText + '--' + t.responseText);
	}
	