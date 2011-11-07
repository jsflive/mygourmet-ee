function processPollEvent(interval) {
    return function(data) {
        if (data.status == 'success') {
            startAjaxPoll(data.source.id, interval);
        }
    };
}

function poll(clientId, interval) {
    var element = document.getElementById(clientId);
    element.mgPoll = true;
    jsf.ajax.request(element, null, {render: clientId, onevent: processPollEvent(interval)});
}

function startAjaxPoll(clientId, interval) {
    setTimeout("poll('" + clientId + "', " + interval + ")", interval);
}
