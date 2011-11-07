function processAjaxUpdate(msgId) {
    function processEvent(data) {
        var msg = document.getElementById(msgId);
        if (!data.source.mgPoll) {
            if (data.status == "begin") {
                msg.style.display = '';
            } else if (data.status == "success") {
                msg.style.display = 'none';
            }
        }
    }
    return processEvent;
}

function registerAjaxStatus(msgId) {
    jsf.ajax.addOnEvent(processAjaxUpdate(msgId));
}
